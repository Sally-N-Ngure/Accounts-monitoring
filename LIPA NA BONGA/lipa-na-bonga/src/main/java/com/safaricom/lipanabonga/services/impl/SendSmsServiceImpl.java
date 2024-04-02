package com.safaricom.lipanabonga.services.impl;

import com.safaricom.lipanabonga.config.ApplicationProperties;
import com.safaricom.lipanabonga.services.SendSmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@RequiredArgsConstructor
@Slf4j
@Service
public class SendSmsServiceImpl implements SendSmsService {

    private final RestClient restClient;
    private final ApplicationProperties properties;

    @Override
    public void sendSms(String phoneNumber, String message) {
        String smsResponse = this.restClient.post().uri(properties.getSmsEndpoint())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .headers(httpHeaders -> {
                    httpHeaders.setBasicAuth(properties.getSmsUsername(), properties.getSmsPassword());
                    httpHeaders.add("x-source-system", "lipa-na-bonga");
                    httpHeaders.add("x-correlation-conversationid", UUID.randomUUID().toString());
                })
                .body("""
                        {
                          "roles": {
                            "receiver": {
                              "id": [
                                {
                                  "value": "%s"
                                }
                              ]
                            }
                          },
                          "parts": {
                            "body": {
                              "text": "%s"
                            },
                            "trailer": {
                              "text": "SAFARICOM"
                            }
                          }
                        }
                                               
                        """.formatted(phoneNumber, message)).retrieve().body(String.class);
        log.info("Sms response {}", smsResponse);
    }
}
