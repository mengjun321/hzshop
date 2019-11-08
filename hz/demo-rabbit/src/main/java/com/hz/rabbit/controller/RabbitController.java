package com.hz.rabbit.controller;

import com.hz.rabbit.bean.Dept;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @RequestMapping("/send/{msg}")
    public Object sendMsg(@PathVariable("msg") String msg){

        Dept dept = new Dept().setDeptno(1).setDname("研发部").setLoc("深圳");

        rabbitTemplate.convertAndSend("hello",msg);
        rabbitTemplate.convertAndSend("bj1901","bj1901.team2",dept);

        return "ok";
    }



}
