package com.fff.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallbackRabbitConfig {
    //创建消息队列
    @Bean
    public Queue queueVideoSave(){
        return new Queue("video.save");
    }
    @Bean
    public Queue queueVideoDelete(){
        return new Queue("video.delete");
    }
    @Bean
    public Queue queueVideoUpdate(){
        return new Queue("video.update");
    }

    //创建topic交换机，名称是：topicExchange.video
    @Bean
    public TopicExchange callbackExchange(){
        return new TopicExchange("topicExchange.video");
    }
    @Bean
    public Binding bindingExchangeVideoSave(Queue queueVideoSave, TopicExchange callbackExchange){
        return BindingBuilder.bind(queueVideoSave).to(callbackExchange).with("topic.videoSave");
    }
    @Bean
    public Binding bindingExchangeVideoDelete(Queue queueVideoDelete, TopicExchange callbackExchange){
        return BindingBuilder.bind(queueVideoDelete).to(callbackExchange).with("topic.videoDelete");
    }
    @Bean
    public Binding bindingExchangeVideoList(Queue queueVideoUpdate, TopicExchange callbackExchange){
        return BindingBuilder.bind(queueVideoUpdate).to(callbackExchange).with("topic.videoUpdate");
    }

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private String port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    @Bean
    public ConnectionFactory getConnectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setAddresses(host+":" + port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setPublisherConfirms(publisherConfirms);
        return factory;
    }

    @Bean
    public RabbitTemplate myRabbitTemplate(@Qualifier("getConnectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }
}
