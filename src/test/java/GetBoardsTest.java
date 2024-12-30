import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class GetBoardsTest extends BaseTest {

    @Test
    public void checkGetBoards() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("memberId", "yauhenim3")
                .get("/1/members/{memberId}/boards")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));
    }

    @Test
    public void checkGetBoard() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("boardId", "675067bee6c044df8c6dadd3")
                .get("/1/boards/{boardId}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("Test"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"));
    }
}