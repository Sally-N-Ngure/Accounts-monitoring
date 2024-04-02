package com.safaricom.lipanabonga.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class RequestWrapper {
    @JsonProperty("RequestHeader")
    private RequestHeader header;
    @JsonProperty("Body")
    private RequestBody body;
}
