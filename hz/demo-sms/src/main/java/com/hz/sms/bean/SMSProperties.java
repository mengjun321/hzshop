package com.hz.sms.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hz.sms")
@Data
public class SMSProperties {

    private String acessKeyId; //账号id
    private String accessKeySecret; //账号场景值
    private String signName; //账户签名
    private String templateCode;//短信模板设计
}
