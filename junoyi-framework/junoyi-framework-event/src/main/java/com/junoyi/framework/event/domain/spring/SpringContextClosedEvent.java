package com.junoyi.framework.event.domain.spring;

import com.junoyi.framework.event.domain.BaseEvent;
import org.springframework.context.ApplicationContext;

/**
 * Spring上下文关闭事件类
 * 该类用于表示Spring应用上下文被关闭时触发的事件
 *
 * @author Fan
 */
public class SpringContextClosedEvent extends BaseEvent {

    private final Object source;

    /**
     * 构造方法，创建一个新的Spring上下文关闭事件
     * @param source 触发事件的应用上下文对象，不能为空
     */
    public SpringContextClosedEvent(ApplicationContext source){
        this.source = source;
    }

    /**
     * 获取应用上下文对象
     * @return 返回触发此事件的ApplicationContext实例
     */
    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) source;
    }
}
