package com.gavin.cloud.service.impl;

import com.gavin.cloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

//不用@Service是因为，此处的Service层是与消息相关
@EnableBinding(Source.class) //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息发送管道

    @Override
    public String send(){
        String serial = UUID.randomUUID().toString();
        //构建器，向消息中间件发送消息
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("******serial: " + serial);
        return null;
    }
}
