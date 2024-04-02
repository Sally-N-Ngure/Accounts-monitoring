package com.safaricom.lipanabonga.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class RequestHeader {

    @JsonProperty("x-route-id")
    private String xRouteId;
    @JsonProperty("x-source-system")
    private String xSourceSystem;
    @JsonProperty("x-source-identity-token")
    private String xSourceIdentityToken;
    @JsonProperty("Accept-Encoding")
    private String acceptEncoding;
    @JsonProperty("x-correlation-conversationid")
    private String xCorrelationConversationId;
    @JsonProperty("Content-Type")
    private String contentType;
}
