package auth.web;

import auth.AuthApplicationTests;
import auth.dto.request.CreateUserDTO;
import auth.dto.request.InstitutionDto;
import auth.dto.request.RoleDto;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class Oauth2Tests extends AuthApplicationTests {

    @Test
    public void testLogin() {
        String token = getToken();
        given()
                .header("Authorization", "Bearer " + token).log().all()
                .get("/profile")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("admin"))
                .body("lastName", equalTo("admin1"))
                .body("otherName", equalTo(null))
                .body("userName", equalTo("123456"))
                .body("contactEmail", equalTo("admin@gmail.com"))
                .body("contactPhoneNumber", equalTo("0708461561"))
                .body("verifiedEmail", equalTo(false))
                .body("verifiedPhoneNumber", equalTo(false));

        Map<String, String> introspectPayload = new HashMap<>();
        introspectPayload.put("token", token);

        given()
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("browser-client:secret".getBytes(StandardCharsets.UTF_8)))
                .formParams(introspectPayload)
                .post("/oauth2/introspect")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void testGetAllRoleWorks() {
        String token = getToken();
        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .get("/users")
                .then().log().all()
                .statusCode(200);

    }

    @Test
    public void testGetRoleWorks() {
        String token = getToken();
        given()
                .auth()
                .oauth2(token)
                .get("/users/{publicId}", "bb874ce2-dc46-4f11-8915-c1d644f236df")
                .then().log().all()
                .statusCode(200);

    }

    @Test
    public void testAddRoleToUser() {
        String token = getToken();
        RoleDto roleDto = new RoleDto("ROLE_SECRETARY");
        given()
                .auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .body(roleDto).log().all()
                .put("/users/assignRole/{publicId}", UUID.fromString("bb874ce2-dc46-4f11-8915-c1d644f236df"))
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void testcreateInstitutionAdmin() {
        String token = getToken();
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUserName("testtest");
        createUserDTO.setFirstName("Kharis");
        createUserDTO.setLastName("Ponny");
        createUserDTO.setOtherName("Boniface Othername");
        createUserDTO.setContactEmail("ponny@mail.com");
        createUserDTO.setContactPhonenumber("+2547391057862");
        createUserDTO.setPassword("123456789");
        createUserDTO.setInstitutionId(UUID.fromString("eb894640-60ba-432b-9be2-4b13707bcdc1"));

        given()
                .auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .body(createUserDTO).log().all()
                .post("/users/institution_admin")
                .then().log().all()
                .statusCode(200);
    }
}
