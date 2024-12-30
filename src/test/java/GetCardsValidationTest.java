import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetCardsValidationTest {

    @BeforeAll
    public static void setBaseUrl() {
        RestAssured.baseURI = "https://api.trello.com";
    }

    private RequestSpecification requestWithAuth() {
        return RestAssured.given()
                .queryParams(Map.of(
                        "key", "6dfe2176e5f391f8414d73603b6a9f77",
                        "token", "ATTA6cd39dd9b6c03c1bdca8c6c0db90a355f3d5b25ae8fcb8a43833a512af78ea647CF220BF"
                ));
    }

    @Test
    public void checkGetCardWithInvalidId() {
        Response response = requestWithAuth()
                .pathParam("cardId", "invalid")
                .get("/1/cards/{cardId}");
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void checkGetCardWithInvalidAuth() {
        Response response = RestAssured.given()
                .pathParam("cardId", "67582a66bd72066b2a732bed")
                .get("/1/cards/{cardId}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized card permission requested", response.body().asString());
    }

    @Test
    public void checkGetCardWithAnotherUserCredentials() {
        Response response = RestAssured.given()
                .queryParams(Map.of(
                        "key", "6dfe2176e5f391f8414d73603b6a9f77",
                        "token", "ATTA6cd39dd9b6c03c1bdca8c6c0db90a355f3d5b25ae8fcb8a43833a512af78ea647CF220BE"
                ))
                .pathParam("cardId", "67582a66bd72066b2a732bed")
                .get("/1/cards/{cardId}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
}