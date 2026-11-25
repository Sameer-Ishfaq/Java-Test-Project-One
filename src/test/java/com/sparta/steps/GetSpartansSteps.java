package com.sparta.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GetSpartansSteps {
    private final String BASE_URI = "https://spartaacademyapi20240530152521.azurewebsites.net/api/Spartans";
    private RequestSpecification request = RestAssured
            .given()
            .header("Accept", "/json")
            .baseUri(BASE_URI);
    private Response response;

    @Given("i am on the api and the endpoint is set to {string}")
    public void iAmOnTheApiAndTheEndpointIsSetToApiSpartans(String basePath) {
        request = RestAssured
                .given()
                .header("Accept", "/json")
                .baseUri(BASE_URI)
                .basePath(basePath);
    }

    @When("i send a GET request to the extra path {string}")
    public void iSendAGETRequestToTheExtraPath(String extraPath) {
        response = request
                .given()
                .when()
                .get("/" + extraPath)
                .thenReturn();
    }

    @When("i send a GET request to the api with the bad endpoint {string}")
    public void iSendAGETRequestToTheApiWithTheBadEndpoint(String badEndpoint) {
        response = request
                .given()
                .when()
                .get("/" + badEndpoint)
                .thenReturn();
    }

    @Then("i should get a {int} response code in return")
    public void iShouldGetAResponseCodeInReturn(int responseCode) {
        System.out.println("Status Code: " + response.statusCode());
        assertThat(response.statusCode(), is(responseCode));
    }


}
