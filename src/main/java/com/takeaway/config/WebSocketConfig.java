package com.takeaway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


/**
 * 配置WebSocket
 */
//@Configuration
//注解开启使用STOMP协议来传输基于代理(message broker)的消息,这时控制器支持使用@MessageMapping,就像使用@RequestMapping一样
//@EnableWebSocketMessageBroker
public class WebSocketConfig {

    public void registerStompEndpoints(StompEndpointRegistry registry) {//注册STOMP协议的节点(endpoint),并映射指定的url
        //注册一个STOMP的endpoint,并指定使用SockJS协议
        registry.addEndpoint("/endpointAric").withSockJS();

    }

    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理(Message Broker)
        //广播式应配置一个/topic消息代理
        registry.enableSimpleBroker("/topic");

    }
    
}
