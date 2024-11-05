package com.firm_base.product_service.web;

import com.firm_base.product_service.ProductServiceApplicationTests;
import com.firm_base.product_service.model.dto.ProductRequest;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductRestControllerTests extends ProductServiceApplicationTests {

    @Test
    public void createProductWorks() {
        ProductRequest request = new ProductRequest();
        request.setName("Test");
        request.setDescription("Test");
        request.setPrice(new BigDecimal("100.00"));
        request.setQuantity(1);

        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(accessToken)
                .body(request).log().all()
                .post("/products")
                .then()
                .statusCode(200);

        given()
                .auth()
                .oauth2(accessToken)
                .get("/products")
                .then().log().all()
                .statusCode(200)
                .body("content.size()", greaterThanOrEqualTo(1))
                .body("content[0].name", equalToIgnoringCase(request.getName()))
                .body("content[0].description", equalToIgnoringCase(request.getDescription()))
                .body("content[0].price", equalTo(100.0F));
    }
}
