import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetBoardsTest {

    @BeforeAll
    public static void setBaseUrl () {
        RestAssured.baseURI = "https://api.trello.com";
    }

    private static RequestSpecification requestWithAuth() {
        return RestAssured.given()
                .queryParams(Map.of(
                        "key", "6dfe2176e5f391f8414d73603b6a9f77",
                        "token", "ATTA6cd39dd9b6c03c1bdca8c6c0db90a355f3d5b25ae8fcb8a43833a512af78ea647CF220BF"
                ));
    }

    @Test
    public void checkGetBoards() {
        requestWithAuth()
                .pathParam("memberId", "yauhenim3")
                .get("/1/members/{memberId}/boards")
                .then()
                .statusCode(200);
    }

    @Test
    public void checkGetBoard() {
        requestWithAuth()
                .pathParam("boardId", "675067bee6c044df8c6dadd3")
                .get("/1/boards/{boardId}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("Test"));
    }
}