package com.safaricom.lipanabonga;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.safaricom.lipanabonga.config.ApplicationProperties;
import com.safaricom.lipanabonga.services.impl.QueryPayBillServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class QueryPayBillServiceUnitTests {


    private WireMockServer wireMockServer;

    private ApplicationProperties applicationProperties;


    private RestClient restClient;
    private QueryPayBillServiceImpl queryPayBillService;


    @BeforeEach
    public void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration
                .wireMockConfig().dynamicPort());
        wireMockServer.start();
        restClient = RestClient.create();
        applicationProperties= mock(ApplicationProperties.class);
        when(applicationProperties.getPayBillEndPoint()).thenReturn(wireMockServer.baseUrl());
        queryPayBillService = new QueryPayBillServiceImpl(restClient, applicationProperties);
    }

    @AfterEach
    public void tearDown() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }


    @Test
    public void testGetAccountBalance() throws JsonProcessingException {
        wireMockServer.stubFor(post("/").willReturn(
                WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("""
                                {}
                                """)
        ));

        this.queryPayBillService.getAccountBalance("test@gmail.com", "78888");

        verify(applicationProperties).getPayBillEndPoint();

    }
}
