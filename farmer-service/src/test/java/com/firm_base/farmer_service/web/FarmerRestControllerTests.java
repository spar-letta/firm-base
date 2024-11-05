package com.firm_base.farmer_service.web;

import com.firm_base.farmer_service.FarmerServiceApplicationTests;
import com.firm_base.farmer_service.model.dataType.ActionType;
import com.firm_base.farmer_service.model.request.FarmerContactDto;
import com.firm_base.farmer_service.model.request.FarmerDto;
import io.restassured.http.ContentType;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Slf4j
public class FarmerRestControllerTests extends FarmerServiceApplicationTests {

    @Test
    public void testCreateFarmer() {
        FarmerDto farmerDto = new FarmerDto();
        farmerDto.setFirstName("John");
        farmerDto.setLastName("Doe");
        farmerDto.setNationalId("123456789");
        farmerDto.setOtherName("peril kwakwa");
        farmerDto.setJoinDate(LocalDate.now());
        farmerDto.setLandSize(17);
        farmerDto.setCountyId(UUID.fromString("c219300f-d912-4abb-9a3c-459569f6a161"));
        farmerDto.setSubCountyId(UUID.fromString("7834f975-ccb0-4bc4-b5c9-f329c7f04e91"));
        farmerDto.setInstitutionId(UUID.fromString("e6cc80f4-62d4-40ee-9acb-772f8b4404fe"));
        farmerDto.setFarmerCategoriesIds(Arrays.asList(UUID.fromString("0c10b297-ad74-4a71-88fa-783b8245aece")));

        given()
                .contentType("application/json")
                .auth()
                .oauth2(accessToken)
                .body(farmerDto).log().all()
                .post("/farmers")
                .then()
                .statusCode(200);


        String publicId = given()
                .auth()
                .oauth2(accessToken)
                .get("/farmers")
                .then()
                .statusCode(200)
                .body("content.size()", greaterThanOrEqualTo(1))
                .extract().path("content[0].publicId");

        given()
                .auth()
                .oauth2(accessToken)
                .get("/farmers/{farmerId}", UUID.fromString(publicId))
                .then().log().all()
                .statusCode(200)
                .body("firstName", equalToIgnoringCase("John"))
                .body("otherName", equalToIgnoringCase("peril kwakwa"))
                .body("landSize", equalTo(17))
                .body("physicalAddress.size()", equalTo(0));

        FarmerContactDto farmerContactDto = new FarmerContactDto();
        farmerContactDto.setActionType(ActionType.create_contact);
        farmerContactDto.setPhoneNumber("0781232123");
        farmerContactDto.setEmailAddress("email123@yahoo.com");
        farmerContactDto.setLocation("Kamket");
        farmerContactDto.setPostalAddress("1123-kmt");
        farmerContactDto.setPostalCode("34-9000");

        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(accessToken)
                .body(farmerContactDto).log().all()
                .patch("/farmers/{farmerId}/actions", UUID.fromString(publicId))
                .then()
                .statusCode(200);

        given()
                .auth()
                .oauth2(accessToken)
                .get("/farmers/{farmerId}", UUID.fromString(publicId))
                .then().log().all()
                .statusCode(200)
                .body("firstName", equalToIgnoringCase("John"))
                .body("otherName", equalToIgnoringCase("peril kwakwa"))
                .body("landSize", equalTo(17))
                .body("physicalAddress.size()", greaterThanOrEqualTo(1))
                .body("physicalAddress.publicId", notNullValue())
                .body("physicalAddress.phoneNumber", notNullValue())
                .body("physicalAddress.emailAddress", notNullValue());
    }

}
