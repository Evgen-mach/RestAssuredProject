package steps;

import consts.BoardsEndpoints;
import consts.UrlParamValues;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetBoardsSteps {

    private RequestSpecification request;
    private Response response;

    private RequestSpecification requestWithAuth() {
        return requestWithoutAuth()
                .queryParams(UrlParamValues.AUTH_QUERY_PARAMS);
    }

    private RequestSpecification requestWithoutAuth() {
        RestAssured.baseURI = "https://api.trello.com";
        return RestAssured.given();
    }
    @Given("a request with authorization")
    public void aRequestWithAuthorization() {
        request = requestWithAuth();
    }

    @And("the request has fields query params")
    public void theRequestHasFieldsQueryParams() {
        request = request.queryParam("fields", "id,name");
    }

    @And("the request has member path params")
    public void theRequestHasMemberPathParams() {
        request = request.pathParam("id", UrlParamValues.USER_NAME);
    }

    @When("the request is sent to getBoards endpoint")
    public void theRequestIsSentToGetBoardsEndpoint() {
        response = request.get(BoardsEndpoints.GET_ALL_BOARDS_URL);
    }

    @Then("the getBoards response status code is 200")
    public void theGetBoardsResponseStatusCodeIs() {
        response.then().statusCode(200);
    }

    @And("the getBoards response matches get_boards.json schema")
    public void theGetBoardsResponseMatchesGet_boardsJsonSchema() {
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));
    }
}