package auth;

import auth.application.config.oauth2.Oauth2Settings;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AuthApplicationTests {
    private final SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor adminCredentials = SecurityMockMvcRequestPostProcessors.user("userAdmin").roles("ALL");
    @Value("${local.server.port}")
    public int port;

    @Autowired
    private WebApplicationContext context;

    public MockMvc mvc;

    @Before
    public void setUpGlobal() throws IOException {
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;

        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    public String getToken() {

        JsonNode user = given()
                .get("/users/{publicId}", "bb874ce2-dc46-4f11-8915-c1d644f236df")
                .then()
                .statusCode(200)
                .extract().as(JsonNode.class);

        Map<String, String> tokenAccessPayload = new HashMap<>();
        tokenAccessPayload.put("grant_type", Oauth2Settings.GRANT_TYPE_PASSWORD);
        tokenAccessPayload.put("username", user.get("userName").asText());
        tokenAccessPayload.put("password", "123456789");
        tokenAccessPayload.put("scope", "* openid");

        String accessToken = given()
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("browser-client:secret".getBytes(StandardCharsets.UTF_8)))
                .formParams(tokenAccessPayload).log().all()
                .post("/oauth2/token")
                .then()
                .statusCode(200)
                .body("access_token", notNullValue())
                .body("token_type", notNullValue())
                .body("expires_in", notNullValue())
                .extract().path("access_token");
        return accessToken;
    }

}
