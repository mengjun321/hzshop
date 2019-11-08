package com.hz.sms.listener;

import com.aliyuncs.CommonResponse;
import com.hz.sms.bean.SMSProperties;
import com.hz.sms.util.SmsUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
//@EnableConfigurationProperties(SMSProperties.class)
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private SMSProperties prop;


    /**
     * @param msg :解码后的消息
     * @param deliveryTag :使用@Header接口获取messageProperties中的DELIVERY_TAG属性。
     * @param channel : 接受消息所使用的信道
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "hz.sms.queue", durable = "true"),
            exchange = @Exchange(value = "hz.sms.exchange",
                    ignoreDeclarationExceptions = "true"),
            key = {"sms.verify.code"}))
    public void listenSms(@Payload Map<String, String> msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws Exception {
        if (msg == null || msg.size() <= 0) {
            // 放弃处理
            return;
        }
        String phone = msg.get("phone");
        String code = msg.get("code");

        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            // 放弃处理
            return;
        }
        // 发送消息

        //手动确认消息
        channel.basicAck(deliveryTag,false);
        System.out.print("这里是接收者1答应消息： ");

        CommonResponse response = this.smsUtils.sendSms(phone, code);
        // 发送失败
        //throw new RuntimeException();
    }
}