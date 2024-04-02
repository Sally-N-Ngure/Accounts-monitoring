package com.safaricom.lipanabonga;

import com.safaricom.lipanabonga.data.entity.PayBillAccountEntity;
import com.safaricom.lipanabonga.data.repositories.PayBillAccountRepository;
import com.safaricom.lipanabonga.services.impl.QueryAndNotifyServiceImpl;
import com.safaricom.lipanabonga.services.impl.SendEmailServiceImpl;
import com.safaricom.lipanabonga.services.impl.SendSmsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QueryAndNotifyServiceUnitTest {

    @Mock
    private SendEmailServiceImpl sendEmailService;

    @Mock
    private SendSmsServiceImpl sendSmsService;
    @Mock
    private PayBillAccountRepository payBillAccountRepository;
    @InjectMocks
    private QueryAndNotifyServiceImpl queryAndNotifyService;

    @Test
    public void testqueryAndNotifyService() {
        //Arrange
        PayBillAccountEntity payBillAccountEntity = new PayBillAccountEntity();
        payBillAccountEntity.setAccountType("min");
        payBillAccountEntity.setCurrentBalance(BigDecimal.valueOf(1000));
        payBillAccountEntity.setMinimumThreshold(BigDecimal.valueOf(10000));
        payBillAccountEntity.setAccountNumber("9000");
        payBillAccountEntity.setAccountName("Test");
        payBillAccountEntity.setEmailRecipients("ogoma.emmmm@gmail");
        payBillAccountEntity.setPhoneNumbers("0897654321");
        when(payBillAccountRepository.findByMonitoringIsActiveTrue()).thenReturn(List.of(payBillAccountEntity));
        // execute
        queryAndNotifyService.queryAndNotifyService();
        // verify
        verify(payBillAccountRepository).findByMonitoringIsActiveTrue();
        verify(sendEmailService).sendMail(anyString(),anyString(),anyString(),any(BigDecimal.class));


    }
}
