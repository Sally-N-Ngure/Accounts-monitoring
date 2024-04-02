package com.safaricom.lipanabonga.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Response {
    @JsonProperty("GetServiceBalanceListVBMResponse")
    private GetServiceBalanceListVBMResponse getServiceBalanceListVBMResponse;
}
