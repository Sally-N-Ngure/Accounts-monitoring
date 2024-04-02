package com.safaricom.lipanabonga.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class BalanceDetails {

    @JsonProperty("AvailableBalance")
    private String availableBalance;
    @JsonProperty("ReservedBalance")
    private String reservedBalance;
    @JsonProperty("UnclearedBalance")
    private String unclearedBalance;
    @JsonProperty("CurrentBalance")
    private String currentBalance;
    @JsonProperty("AccountTypeName")
    private String accountTypeName;
    @JsonProperty("AccountTypeAlias")
    private String accountTypeAlias;
}
