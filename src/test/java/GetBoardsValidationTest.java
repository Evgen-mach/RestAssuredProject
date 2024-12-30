import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetBoardsValidationTest extends BaseTest {

    @Test
    public void checkGetBoardWithInvalidId() {
        Response response = requestWithoutAuth()
                .pathParam("boardId", "invalid")
                .get("/1/boards/{boardId}");
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void checkGetBoardWithInvalidAuth() {
        Response response = requestWithoutAuth()
                .pathParam("boardId", "675067bee6c044df8c6dadd3")
                .get("/1/boards/{boardId}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized permission requested", response.body().asString());
    }

    @Test
    public void checkGetBoardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
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