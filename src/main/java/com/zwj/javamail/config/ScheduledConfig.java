package com.zwj.javamail.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

/**
 * 以 ScheduledCondtion 为条件，决定是否创建 bean。然后，启动项目，定时任务就会执行，如果我们将配置修改为 false，则不会执行。
 */

@Configuration
public class ScheduledConfig {


    @Conditional(ScheduledCondition.class)
    @Bean
    public ScheduledAnnotationBeanPostProcessor processor() {
        return new ScheduledAnnotationBeanPostProcessor();
    }


}
