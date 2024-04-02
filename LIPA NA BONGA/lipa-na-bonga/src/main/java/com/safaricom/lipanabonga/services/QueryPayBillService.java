package com.safaricom.lipanabonga.services;

public interface QueryPayBillService {
    String getAccountBalance(String payBillNumber, String accountType);
}
