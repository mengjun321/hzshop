package com.hz.pay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayConfig {

    @Value("${alipay.gatewayUrl}")
    private String gatewayUrl;

    @Value("${alipay.app_id}")
    private String app_id;

    @Value("${alipay.merchant_private_key}")
    private String merchant_private_key;

    @Value("${alipay.merchant_public_key}")
    private String merchant_public_key;

    @Value("${alipay.alipay_public_key}")
    private String alipay_public_key;

    @Value("${alipay.format}")
    private String format;

    @Value("${alipay.charset}")
    private String charset;

    @Value("${alipay.sign_type}")
    private String sign_type;

    @Bean
    public AlipayClient alipayClient(){
        System.out.println("gatewayUrl"+gatewayUrl);
        System.out.println("app_id:"+app_id);
        System.out.println("merchant_private_key:"+merchant_private_key);
        System.out.println("merchant_public_key:"+merchant_public_key);
        System.out.println("alipay_public_key:"+alipay_public_key);
        System.out.println("format:"+format);
        System.out.println("charset:"+charset);
        System.out.println("sign_type:"+sign_type);

        DefaultAlipayClient client = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key,format,  charset,  alipay_public_key,  sign_type);
        return client;
    }

}
