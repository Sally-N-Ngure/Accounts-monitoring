package com.safaricom.lipanabonga;

import com.safaricom.lipanabonga.config.ApplicationProperties;
import com.safaricom.lipanabonga.services.impl.SendEmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SendEmailServiceUnitTest {
    @Mock
    private JavaMailSender mailSender;
    @Mock
    private ApplicationProperties applicationProperties;

    @InjectMocks
    private SendEmailServiceImpl sendEmailService;

    @Test
    public void testSendMail(){
        //
        when(applicationProperties.getSupportPhoneNumber()).thenReturn("900000000");

        this.sendEmailService.sendMail(
                "test@gmail.com","78888","Test Paybill",
                BigDecimal.valueOf(89999));
        verify(applicationProperties).getSupportPhoneNumber();
        verify(mailSender).send(any(SimpleMailMessage.class));

    }

}