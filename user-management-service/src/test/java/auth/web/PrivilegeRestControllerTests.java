package auth.web;

import auth.AuthApplicationTests;
import auth.dto.request.PrivilegeDto;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PrivilegeRestControllerTests extends AuthApplicationTests {

    @Test
    public void testCreatePrivilege() {
        String token = getToken();
        PrivilegeDto privilegeDto = new PrivilegeDto("READ_PRIVILEGE");

        given()
                .auth()
                .oauth2(token)
                .contentType("application/json")
                .body(privilegeDto).log().all()
                .post("/privileges")
                .then().log().all()
                .statusCode(200);

        given()
                .contentType("application/json")
                .auth()
                .oauth2(token)
                .get("/privileges")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void testReadPrivilege() {
        String token = getToken();
        given()
                .auth()
                .oauth2(token)
                .get("/privileges")
                .then().log().all()
                .statusCode(200);
    }
}
