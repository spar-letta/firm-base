package auth.web;

import auth.AuthApplicationTests;
import auth.dto.request.InstitutionDto;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class InstitutionRestControllerTests extends AuthApplicationTests {

    @Test
    public void testCreateInstitution() {
        String token = getToken();
        InstitutionDto request = new InstitutionDto();
        request.setInstitutionName("Nambengele Agrovet");
        request.setEmailAddress("namb@gmail.com");
        request.setWebsite("www.google.com");
        request.setPhoneNumber("0754444444");

        given()
                .auth()
                .oauth2(token)
                .contentType("application/json")
                .body(request).log().all()
                .post("/institutions")
                .then().log().all()
                .statusCode(200);

        given()
                .auth()
                .oauth2(token)
                .get("/institutions")
                .then().log().all()
                .statusCode(200);
    }
}
