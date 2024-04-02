package com.safaricom.lipanabonga.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ServiceBalanceVBO {
    @JsonProperty("Desc")
    private String desc;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("id")
    private String id;
    @JsonProperty("created")
    private Created created;
    @JsonProperty("AccountDetails")
    private AccountDetails accountDetails;
}

@Data
@ToString
class Created {
    private long value;
}
