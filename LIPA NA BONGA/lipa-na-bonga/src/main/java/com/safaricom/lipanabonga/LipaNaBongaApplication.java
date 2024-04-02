package com.safaricom.lipanabonga;

import com.safaricom.lipanabonga.services.QueryAndNotifyService;
import com.safaricom.lipanabonga.services.QueryPayBillService;
import com.safaricom.lipanabonga.services.SendEmailService;
import com.safaricom.lipanabonga.services.SendSmsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;


@SpringBootApplication
@RequiredArgsConstructor
public class LipaNaBongaApplication implements CommandLineRunner {

    private final SendSmsService sendSmsService;
    private final SendEmailService sendEmailService;
    private final QueryPayBillService queryPayBillService;
    private final QueryAndNotifyService queryAndNotifyService;
    private static final Logger logger = LoggerFactory.getLogger(LipaNaBongaApplication.class);

    public static void main(String[] args){
        logger.info("STARTING THE APPLICATION");
        SpringApplication.run(LipaNaBongaApplication.class, args);
        logger.info("APPLICATION FINISHED");
    }
    @Override
    public void run(String... args) {
        this.sendEmailService.sendMail(
                "snngur",
                "778888",
                "Test",
                BigDecimal.valueOf(1000));
        this.sendSmsService.sendSms("+254721", "Hi G.Kairu, Its Sally. I have completed the task. Have a great weekend ahead");
        this.queryPayBillService.getAccountBalance("69313", "Homend");
       this.queryAndNotifyService.queryAndNotifyService();
    }
}
