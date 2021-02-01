package com.sphere.quickstart;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ShardingSphereDemo {

    public static void main(String[] args) throws SQLException {

        Map<String, DataSource> map = new HashMap<>(2);
        // 配置数据源
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://172.16.33.52:3306/shard_ds_1?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        map.put("ds0", dataSource1);

        // 配置数据源
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://172.16.33.217:3306/shard_ds_0?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        map.put("ds1", dataSource1);

        // *********** 分片规则 ************//
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(getOrderShardingRule());

        // 绑定表
        // shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");

        // 配置分库策略，根据user_id与2取模的结果
        shardingRuleConfiguration.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id",
                "ds${user_id % 2}"));
        // 配置分表策略
        shardingRuleConfiguration.setDefaultTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id",
                "order_${order_id % 2}"));

        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(map, shardingRuleConfiguration, properties);
        testInsert(dataSource);

    }


    /**
     * 配置分表规则
     * @return
     */
    private static TableRuleConfiguration getOrderShardingRule(){
        TableRuleConfiguration ruleConfiguration = new TableRuleConfiguration("order",
                "ds${0..1}.order_${[0, 1]}");
    //    ruleConfiguration.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE","order_id", getProps()));
        return ruleConfiguration;
    }

    //雪花算法，需要有机器序号，手动配置序号
    private static Properties getProps(){
        Properties props = new Properties();
        props.setProperty("worker.id", "123");
        return props;
    }

    private static void testInsert(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();


            String sql = "INSERT INTO order(order_id, user_id, status) values (1, 1, 'init')" ;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeUpdate();

    }

}
