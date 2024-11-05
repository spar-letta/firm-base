package com.firm_base.farmer_service.web;

import com.firm_base.farmer_service.FarmerServiceApplicationTests;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountyRestControllerTests extends FarmerServiceApplicationTests {

    @Test
    public void getCounties() {
        given()
                .auth()
                .oauth2(accessToken)
                .get("/counties")
                .then().log().all()
                .statusCode(200);
    }
}
