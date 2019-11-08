package com.hz.rabbit.controller;

import com.hz.rabbit.bean.Dept;
import lombok.experimental.Accessors;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RabbitListener(queues = "team2")
public class RabbitReceiverController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/rece")
   // @RabbitHandler
    @ResponseBody
    public Object receiver(){
        //手动取消息
        rabbitTemplate.setQueue("team2");
        Object andConvert = rabbitTemplate.receiveAndConvert();
        System.out.println(andConvert);
        return andConvert;
    }

}
