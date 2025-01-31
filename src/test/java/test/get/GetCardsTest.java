package test.get;

import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;

public class GetCardsTest extends BaseTest {

    @Test
    public void checkGetCards() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("id", UrlParamValues.EXISTING_LIST_ID)
                .get(CardsEndpoints.GET_ALL_CARDS_URL)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_cards.json"));
    }

    @Test
    public void checkGetCard() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("id", UrlParamValues.EXISTING_CARD_ID)
                .get(CardsEndpoints.GET_CARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("test Card Raw"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"));
    }
}