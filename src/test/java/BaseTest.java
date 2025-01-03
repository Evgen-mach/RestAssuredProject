import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

public class BaseTest {

    @BeforeAll
    public static void setBaseUrl () {
        RestAssured.baseURI = "https://api.trello.com";
    }

    protected RequestSpecification requestWithAuth() {
        return requestWithoutAuth()
                .queryParams(Map.of(
                        "key", "6dfe2176e5f391f8414d73603b6a9f77",
                        "token", "ATTA6cd39dd9b6c03c1bdca8c6c0db90a355f3d5b25ae8fcb8a43833a512af78ea647CF220BF"
                ));
    }

    protected RequestSpecification requestWithoutAuth() {
        return RestAssured.given();
    }
}
