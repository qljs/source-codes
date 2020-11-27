package com.source.code.springAOP;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    @Bean
    public ICalculation calculation() {
        return new CalculationImpl();
    }

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}
