package com.safaricom.lipanabonga.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "lipa-na-bonga")
@Getter
@Setter
public class ApplicationProperties {

    private String smsEndpoint;
    private String smsPassword;
    private String smsUsername;
    private String supportPhoneNumber;
    private String payBillEndPoint;
}
