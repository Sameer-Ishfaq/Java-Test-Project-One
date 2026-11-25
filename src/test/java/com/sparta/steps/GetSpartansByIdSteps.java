package com.sparta.steps;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.plugin.event.Node;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class GetSpartansByIdSteps {
    private final String BASE_URI = "https://spartaacademyapi20240530152521.azurewebsites.net/api/Spartans";
    private final String BASE_URI_FOR_LOGIN = "https://spartaacademyapi20240530152521.azurewebsites.net";
    private RequestSpecification request = RestAssured
            .given()
            .header("Accept", "/json")
            .baseUri(BASE_URI);

    private Response response;
    private String token;


@Given("i am authenticated")
public void iAmAuthenticated() {
    String body = """
            {
            "username": "sparta",
            "password": "global"
            }
            """;
    response = RestAssured
            .given()
            .baseUri(BASE_URI_FOR_LOGIN)
            .contentType("application/json")
            .body(body)
            .post("Auth/login");

    token = response.jsonPath().getString("token");
}

    @When("i send a GET request retrieving a valid id {int}")
    public void iSendAGETRequestRetrievingAValidId(int validId) {

        response = request
                .header("Authorization"," Bearer " + token)
                .accept("application/json")
                .when()
                .get("/" + validId)
                .thenReturn();
    }

    @When("i send a GET request to the endpoint with id {int}")
    public void iSendAGETRequestToTheEndpointWithId(int id) {
        response = request
                .header("Authorization","Bearer " + token)
                .accept("application/json")
                .when()
                .get("/" + id)
                .thenReturn();
    }

    @When("i send a GET request to get an invalid {string}")
    public void iSendAGETRequestToGetAnInvalidId(Object invalidId) {
        response = request
                .header("Authorization","Bearer " + token)
                .accept("application/json")
                .when()
                .get("/" + invalidId)
                .thenReturn();

    }
    @When("i send a PUT request")
    public void iSendAPUTRequest() {
        response = request
                .accept("application/json")
                .when()
                .put()
                .thenReturn();
    }

    @Then("i should get a {int} response code back")
    public void iShouldGetAResponseCodeBack(int responseCode) {
        assertThat(response.statusCode(), is(responseCode));
    }

    @And("i should be presented with a fresh {string}")
    public void iShouldBePresentedWithAUniqueBearerToken(String bearerToken) {
        bearerToken = response.jsonPath().getString("token");
        System.out.println(bearerToken);

        assertThat(bearerToken, Matchers.notNullValue());
    }

    @And("the output should be in Json format")
    public void theOutputShouldBeInJsonFormat() {
        assertThat(response.getContentType(), Matchers.containsString("application/json"));
    }
}



