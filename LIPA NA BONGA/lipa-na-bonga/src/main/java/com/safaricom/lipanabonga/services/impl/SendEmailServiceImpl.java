package com.safaricom.lipanabonga.services.impl;

import com.safaricom.lipanabonga.config.ApplicationProperties;
import com.safaricom.lipanabonga.services.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService {

    private final JavaMailSender javaMailSender;
    private final ApplicationProperties applicationProperties;
    @Override
    public void sendMail(String recipientEmail, String payBillNumber, String payBillName, BigDecimal currentAmount) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@safaricom.co.ke"); // Set a sender address
        message.setTo(recipientEmail);
        message.setSubject(String.format("Paybill Balance for %s - %s Below threshhold",payBillNumber,payBillName));
        message.setText("""
                Dear Partner \n
                Kindly note Paybill %s {Lipa na bonga} is running below the set threshold. The current amount is amount %.2f. Please top up the account.
                Thank You. \n
                From
                Automation Team
                For more information, contact the automation team at email@safaricom.co.ke or the Manager on %s
                """.formatted(payBillNumber,currentAmount,
                applicationProperties.getSupportPhoneNumber()));

        javaMailSender.send(message);
    }
}
