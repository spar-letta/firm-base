package com.firm_base.farmer_service.web;

import com.firm_base.farmer_service.FarmerServiceApplicationTests;
import com.firm_base.farmer_service.model.dataType.FarmerCategoryEnum;
import com.firm_base.farmer_service.model.request.FarmerCategoryRequest;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FarmerCategoryRestControllerTests extends FarmerServiceApplicationTests {

    @Test
    public void createFarmerCategoryWorks() {
        FarmerCategoryRequest request = new FarmerCategoryRequest();
        request.setCategory(FarmerCategoryEnum.Small);
        request.setLandSize(5);

        given()
                .contentType("application/json")
                .auth()
                .oauth2(accessToken)
                .body(request).log().all()
                .post("/farmerCategories")
                .then()
                .statusCode(200);
    }

    @Test
    public void getFarmerCategoryWorks() {
        given()
                .auth()
                .oauth2(accessToken)
                .get("/farmerCategories")
                .then().log().all()
                .statusCode(200);
    }
}
