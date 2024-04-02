package com.safaricom.lipanabonga;


import com.safaricom.lipanabonga.config.ApplicationProperties;
import com.safaricom.lipanabonga.services.impl.SendSmsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class SendSmsServiceUnitTest {


    @Mock
    RestClient restClient;

    @Mock
    ApplicationProperties properties;

    @InjectMocks
    SendSmsServiceImpl sendSmsService;



//    @BeforeEach
//    public void setup() {
//        sendSmsService = new SendSmsServiceImpl(restClient,properties);
//    }

    @Test
    public void testSendSms() {
        //Arrange
        // execute
        // verify
        String phoneNumber = "+2547233333";
        String message = "Test message";
        when(restClient.post()
                .uri(anyString()).body(any())
                .header("x-source-system", "lipa-na-bonga")
                .header("x-correlation-conversationid", anyString())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .retrieve().body(eq(String.class))).thenReturn("Test result");


        sendSmsService.sendSms(phoneNumber, message);


    }

}
