package com.safaricom.lipanabonga.services.impl;

import com.safaricom.lipanabonga.client.QueryPayBillClient;
import com.safaricom.lipanabonga.config.ApplicationProperties;
import com.safaricom.lipanabonga.model.request.RequestBody;
import com.safaricom.lipanabonga.model.request.RequestHeader;
import com.safaricom.lipanabonga.model.request.RequestWrapper;
import com.safaricom.lipanabonga.model.response.Response;
import com.safaricom.lipanabonga.services.QueryPayBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
public class QueryPayBillServiceImpl implements QueryPayBillService {

    private final RestClient restClient;
    private final ApplicationProperties properties;
    private final QueryPayBillClient client;

    @Override
    public String getAccountBalance(String payBillNumber, String accountType) {
        String accountBalance;
        RequestHeader requestHeader = RequestHeader.builder()
                .xRouteId("12345")
                .xSourceSystem("local_test")
                .xSourceIdentityToken("234322")
                .acceptEncoding("application/json")
                .xCorrelationConversationId("2344322")
                .contentType("application/json").build();
        RequestBody requestBody = RequestBody.builder()
                .serviceIdentifier("4")
                .serviceBalanceType(accountType)
                .serviceIdentifier(payBillNumber).build();
        RequestWrapper requestWrapper = RequestWrapper.builder()
                .header(requestHeader)
                .body(requestBody).build();

        Map<String , String> responseMap = client.getAccountBalance(requestWrapper);
        if (Objects.equals(responseMap.get("RESPONSE_CODE"), "000")){
            String responseBody = responseMap.get("RESPONSE_BODY");
            Response response = (Response) UtilService.jsonStringToPojo(responseBody, Response.class);
            log.info("RESPONSE: {}", response);

            if (accountType.equalsIgnoreCase("WorkingAccountDetails")){
                accountBalance = response.getGetServiceBalanceListVBMResponse()
                        .getServiceBalanceVBO().getAccountDetails().getWorkingAccountDetails().getCurrentBalance();
            } else {
                accountBalance = response.getGetServiceBalanceListVBMResponse()
                        .getServiceBalanceVBO().getAccountDetails().getUtilityAccountDetails().getCurrentBalance();
            }


            log.info("ACCOUNT BALANCE: {}", accountBalance);
            return accountBalance;
        } else {
            throw new RuntimeException(responseMap.get("RESPONSE_BODY"));
        }

//        String queryPayBillResponse = this.restClient.post().uri(properties.getPayBillEndPoint())
//                .contentType(MediaType.APPLICATION_JSON)
//                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .body("""
//                        {
//                          "RequestHeader": {
//                            "x-route-id": "12345",
//                            "x-source-system": "local_test",
//                            "x-source-identity-token": "234322",
//                            "Accept-Encoding": "application/json",
//                            "x-correlation-conversationid": "2344322",
//                            "Content-Type": "application/json"
//                          },
//                          "Body": {
//                            "serviceIdentifierType": "4",
//                            "serviceBalanceType": "%s",
//                            "serviceIdentifier": "%s"
//                          }
//                        }
//                        """.formatted(accountType,payBillNumber))
//                .retrieve().body(String.class);
//
//        log.info("Query paybill response: {}",queryPayBillResponse);
//
//        return queryPayBillResponse;
//
    }


}
