import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetCardsTest {

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