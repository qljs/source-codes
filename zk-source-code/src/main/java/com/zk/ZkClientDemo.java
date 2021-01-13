package com.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ZkClientDemo {

    ZkClient zkClient;

    @Before
    public void init(){
        zkClient = new ZkClient(new ZkConnection("172.16.33.52:2181"));
    }

    @Test
    public void testCreate(){
        zkClient.createPersistent("/zkdemo","persistent");
    }

    @Test
    public void testSet(){
        zkClient.writeData("/zkdemo","xiugai11");
    }

    @Test
    public void testGet(){
        String data = zkClient.readData("/zkdemo");
        System.out.println(data);
    }

    @Test
    public void testLs(){
        List<String> children = zkClient.getChildren("/zkdemo");
        System.out.println(children.toString());
    }


    @Test
    public void addWatch() throws InterruptedException {
        zkClient.subscribeDataChanges("/zkdemo", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("handleDataChange");
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("handleDataDeleted");
            }
        });


        Thread.sleep(Integer.MAX_VALUE);
    }
}
