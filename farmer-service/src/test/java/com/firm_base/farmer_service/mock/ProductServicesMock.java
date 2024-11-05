package com.firm_base.farmer_service.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.UUID;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class ProductServicesMock {
    public static void setupMockGetProduct(WireMockServer mockService, UUID productId) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo(String.format("/internal/products/%s", productId)))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(copyToString(
                                ProductServicesMock.class.getClassLoader().getResourceAsStream("payload/fetchProductOk.json"),
                                defaultCharset()))));
    }
}
