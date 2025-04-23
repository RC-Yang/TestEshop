package com.example;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ecpay.payment.integration.AllInOne;

@Configuration
public class EcpayConfig {

    @Bean
    public AllInOne allInOne() throws IOException {
        return new AllInOne(""); // 會自動讀取 merchantInfo.properties
    }
}
