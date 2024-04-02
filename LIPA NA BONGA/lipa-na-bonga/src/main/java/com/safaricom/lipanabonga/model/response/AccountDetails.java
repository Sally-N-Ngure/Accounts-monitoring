package com.safaricom.lipanabonga.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccountDetails {

    @JsonProperty("AccountHolderID")
    private int accountHolderId;
    @JsonProperty("AccountHolderPublicName")
    private String accountHolderPublicName;
    @JsonProperty("UtilityAccountDetails")
    private BalanceDetails utilityAccountDetails;
    @JsonProperty("WorkingAccountDetails")
    private BalanceDetails workingAccountDetails;
}
