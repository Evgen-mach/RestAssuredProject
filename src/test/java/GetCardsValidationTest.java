import arguments.CardIdValidationArgumentsHolder;
import arguments.CardIdValidationArgumentsProvider;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Map;

public class GetCardsValidationTest extends BaseTest{

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkGetCardWithInvalidId(CardIdValidationArgumentsHolder validationArguments) {
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .get("/1/cards/{id}");
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @Test
    public void checkGetCardWithInvalidAuth() {
        Response response = requestWithoutAuth()
                .pathParam("id", "67582a66bd72066b2a732bed")
                .get("/1/cards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized card permission requested", response.body().asString());
    }

    @Test
    public void checkGetCardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(Map.of(
                        "key", "6dfe2176e5f391f8414d73603b6a9f77",
                        "token", "ATTA6cd39dd9b6c03c1bdca8c6c0db90a355f3d5b25ae8fcb8a43833a512af78ea647CF220BE"
                ))
                .pathParam("id", "67582a66bd72066b2a732bed")
                .get("/1/cards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
}