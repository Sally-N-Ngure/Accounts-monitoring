package com.safaricom.lipanabonga.client;

import com.safaricom.lipanabonga.config.ApplicationProperties;
import com.safaricom.lipanabonga.model.request.RequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class QueryPayBillClient {
    private final RestClient restClient;
    private final ApplicationProperties appProperties;
    private static final String RESPONSE_CODE = "RESPONSE_CODE";
    private static final String RESPONSE_BODY = "RESPONSE_BODY";


    public QueryPayBillClient(RestClient restClient, ApplicationProperties appProperties) {
        this.restClient = restClient;
        this.appProperties = appProperties;
    }

    public Map<String, String> getAccountBalance(RequestWrapper request){
        String url = appProperties.getPayBillEndPoint();
        HashMap<String, String> responsePayload = new HashMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestWrapper> requestEntity = new HttpEntity<>(request, headers);

        try {
            log.info("CALLING: {}", url);
            ResponseEntity<String> responseEntity = restClient.
                    post()
                    .uri(url)
                    .body(requestEntity)
                    .retrieve().toEntity(String.class);

            HttpStatusCode statusCode= responseEntity.getStatusCode();
            if (statusCode == HttpStatus.OK){
                responsePayload.put(RESPONSE_CODE, "000");
            }
            responsePayload.put(RESPONSE_BODY, responseEntity.getBody());
        } catch (Exception ex){
            log.error("ERROR WHILE MAKING HTTP CALL:: {}", ex.getMessage());
            responsePayload.put(RESPONSE_BODY, ex.getMessage());
        }

        return responsePayload;
    }
}
