package com.hz.rabbit.listener;

import com.hz.rabbit.bean.Dept;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "team2")
public class RabbitmqListener {


    /**
     * 自动监听队列
     * @param dept
     */
    @RabbitHandler
    public void rece(Dept dept){
        System.out.println(dept);
        //写业务......
    }



}
