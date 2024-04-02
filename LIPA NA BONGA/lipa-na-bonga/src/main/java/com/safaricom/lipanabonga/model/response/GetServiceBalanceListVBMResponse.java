package com.safaricom.lipanabonga.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetServiceBalanceListVBMResponse {
    @JsonProperty("ServiceBalanceVBO")
    private ServiceBalanceVBO serviceBalanceVBO;
}
