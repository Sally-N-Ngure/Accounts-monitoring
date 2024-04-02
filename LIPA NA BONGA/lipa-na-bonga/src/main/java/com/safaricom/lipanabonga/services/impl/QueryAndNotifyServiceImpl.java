package com.safaricom.lipanabonga.services.impl;

import com.safaricom.lipanabonga.data.entity.PayBillAccountEntity;
import com.safaricom.lipanabonga.data.repositories.PayBillAccountRepository;
import com.safaricom.lipanabonga.services.QueryAndNotifyService;
import com.safaricom.lipanabonga.services.QueryPayBillService;
import com.safaricom.lipanabonga.services.SendEmailService;
import com.safaricom.lipanabonga.services.SendSmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAndNotifyServiceImpl implements QueryAndNotifyService {

    private final SendEmailService sendEmailService;
    private final SendSmsService sendSmsService;
    private final PayBillAccountRepository payBillAccountRepository;

    private final QueryPayBillService queryPayBillService;


    public void queryAndNotifyService() {
        List<PayBillAccountEntity> accounts = payBillAccountRepository.findByMonitoringIsActiveTrue();
        for (PayBillAccountEntity account : accounts) {
            String homesend = queryPayBillService.getAccountBalance("69313", "Homesend");

            BigDecimal currentBalance = new BigDecimal(100);
            String threshHoldType = account.getThreshHoldType();
            String paybillNumber = account.getAccountNumber();
            String paybillName = account.getAccountName();
            BigDecimal accountThresholdAmount = account.getAccountThresholdAmount();


            if (threshHoldType.equalsIgnoreCase("min") && currentBalance.compareTo(accountThresholdAmount) < 0) {
                sendEmailService.sendMail(account.getEmailRecipients(), paybillNumber, paybillName, currentBalance);
                String message = "alexx!";
                sendSmsService.sendSms(account.getPhoneNumbers(), message);
            }
            if (threshHoldType.equalsIgnoreCase("max") && currentBalance.compareTo(accountThresholdAmount) > 0) {
                sendEmailService.sendMail(account.getEmailRecipients(), paybillNumber, paybillName, currentBalance);
            }
        }
    }
}



