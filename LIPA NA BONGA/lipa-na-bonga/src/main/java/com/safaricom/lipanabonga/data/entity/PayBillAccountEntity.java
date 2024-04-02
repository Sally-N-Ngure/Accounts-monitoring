package com.safaricom.lipanabonga.data.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_paybill_accounts4")
@Getter
@Setter
public class PayBillAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_threshold_amount")
    private BigDecimal accountThresholdAmount;

    @Column(name = "threshold_type")
    private String  threshHoldType;

    @Column(name = "monitoring_is_active")
    private boolean monitoringIsActive;

    private String emailRecipients;
    public String phoneNumbers;

}
