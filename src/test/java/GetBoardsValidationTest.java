import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetBoardsValidationTest {

    @BeforeAll
    public static void setBaseUrl () {
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
    public void checkGetBoardWithInvalidId() {
        Response response = requestWithAuth()
                .pathParam("boardId", "invalid")
                .get("/1/boards/{boardId}");
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

//    @Test
//    public void checkGetBoardsWithInvalidAuth() {
//        Response response = RestAssured.given()
//                .pathParam("boardId", "675067bee6c044df8c6dadd3")
//                .get("/1/boards/{boardId}");
//        response
//                .then()
//                .statusCode(401);
//        Assertions.assertEquals("unauthorized permission request", response.body().asString());
//    }

    @Test
    public void checkGetBoardWithAnotherUserCredentials() {
        Response response = RestAssured.given()
                .queryParams(Map.of(
                        "key", "6dfe2176e5f391f8414d73603b6a9f77",
                        "token", "ATTA6cd39dd9b6c03c1bdca8c6c0db90a355f3d5b25ae8fcb8a43833a512af78ea647CF220BE"
                ))
                .pathParam("boardId", "675067bee6c044df8c6dadd3")
                .get("/1/boards/{boardId}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
}