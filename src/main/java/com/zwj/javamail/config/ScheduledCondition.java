package com.zwj.javamail.config;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 其实 @Scheduled 注解，是被一个叫做 ScheduledAnnotationBeanPostProcessor 的类所拦截的，
 * 所以我们可以根据配置，决定是否创建这个 bean，如果没有这个 bean，@Scheduled 就不会被拦截，
 * 那么定时任务肯定不会执行了，有了这个思路，实现起来就很简单了。需要注意的是：这种方式，
 * 启动类上面的 @EnableScheduling 需要去掉。
 */

public class ScheduledCondition implements Condition {


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //读取配置中的属性
        return Boolean.valueOf(context.getEnvironment().getProperty("enable.scheduled"));
    }
}
