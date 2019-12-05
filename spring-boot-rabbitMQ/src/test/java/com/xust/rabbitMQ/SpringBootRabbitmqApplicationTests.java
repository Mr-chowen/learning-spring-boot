package com.xust.rabbitMQ;

import com.xust.rabbitMQ.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringBootRabbitmqApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;
    @Test
    public void createExchange(){
//        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
//        System.out.println("创建完成！");
//        amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));
//        创建绑定规则
//        amqpAdmin.declareBinding(new Binding("amqpAdmin.queue", Binding.DestinationType.QUEUE,"amqpAdmin.exchange","amqp.hello",null));
//        amqpAdmin.removeBinding();
        amqpAdmin.deleteExchange("amqpAdmin.exchange");
    }



    /**
     * 1、单播（点对点式）
     */
    @Test
    public void contextLoad(){
//        Message需要自己构造一个，定义消息体内容和消息头内容
//        rabbitTemplate.send(exchange,routeKey,message);

//        object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
//        rabbitTemplate.convertAndSend(exchange,routeKey,object);
        Map<String,Object> map=new HashMap<>();
        map.put("msg","this is first message");
        map.put("data", Arrays.asList("hello","world",123,true));
        //对象默认被序列化之后发送出去
        rabbitTemplate.convertAndSend("exchange.direct","xust.news",new Book("红楼梦","曹雪芹"));
    }
//    接收数据
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("xust.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 2、广播
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("西游记","吴承恩"));
    }


}
