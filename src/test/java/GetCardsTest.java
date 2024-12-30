import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class GetCardsTest extends BaseTest {

    @Test
    public void checkGetCards() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("listId", "6758283447a428b4f2bc66b8")
                .get("/1/lists/{listId}/cards")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_cards.json"));
    }

    @Test
    public void checkGetCard() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("cardId", "67582a66bd72066b2a732bed")
                .get("/1/cards/{cardId}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("test Card Raw"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"));
    }
}