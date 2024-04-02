package com.safaricom.lipanabonga.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class RequestBody {
    private String serviceIdentifierType;
    private String serviceBalanceType;
    private String serviceIdentifier;
}
