package com.source.code.springAOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
            ICalculation calculation = context.getBean(ICalculation.class);
            System.out.println(calculation.add(5,6));
    }
}
