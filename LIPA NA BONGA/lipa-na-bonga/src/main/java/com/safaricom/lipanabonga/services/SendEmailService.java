package com.safaricom.lipanabonga.services;

import java.math.BigDecimal;

public interface SendEmailService {
    void sendMail(String recipientEmail, String payBillNumber, String payBillName, BigDecimal currentAmount);
}
