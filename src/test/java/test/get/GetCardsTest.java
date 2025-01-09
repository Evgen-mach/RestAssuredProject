package test.get;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;

public class GetCardsTest extends BaseTest {

    @Test
    public void checkGetCards() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("id", "6758283447a428b4f2bc66b8")
                .get("/1/lists/{id}/cards")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_cards.json"));
    }

    @Test
    public void checkGetCard() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("id", "67582a66bd72066b2a732bed")
                .get("/1/cards/{id}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("test Card Raw"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"));
    }
}