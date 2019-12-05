package com.xust.rabbitMQ.service;

import com.xust.rabbitMQ.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = "xust.news")
    public void receive(Book book){
        System.out.println("收到消息："+book);
    }

    @RabbitListener(queues = "xust")
    public void receive2(Message message){
        //获得消息体和消息头
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
