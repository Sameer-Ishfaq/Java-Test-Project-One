package com.sparta.sgAcademyApi.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostLoginDetailsSteps {
    private RequestSpecification request;
    private Response response;
    private String BASE_URI = "https://spartaacademyapi20240530152521.azurewebsites.net";


    @Given("i am in the swagger api and the endpoint is set to {string}")
    public void iAmInTheSwaggerApiAndTheEndpointIsSetToAuth(String basePath) {

        request = RestAssured
                .given()
                .header("Accept", "application/json")
                .baseUri(BASE_URI)
                .basePath(basePath);

    }

    @When("i enter {string} as username and {string} as password")
    public void iEnterSpartaAsUsernameAndGlobalAsPassword(String username, String password) {
        String body = """
                {
                "username": "%s",
                "password": "%s"
                }
                """.formatted(username, password);
        response = request
                .contentType("application/json")
                .body(body)
                .when()
                .post()
                .thenReturn();

    }

    @When("i enter an invalid username like {string} and an invalid password like {string}")
    public void iEnterAnInvalidUsernameLikeBadUserAndAnInvalidPasswordLikeBadPassword(String badUsername, String badPassword) {
        String body = """
                {
                "username": "%s",
                "password": "%s"
                }
                """.formatted(badUsername, badPassword);

        response = request
                .contentType("application/json") //without this, throws 404. Sending the username and password in JSON format.
                .body(body)
                .when()
                .post()
                .thenReturn();
    }
    @Then("a {int} response code should be returned")
    public void aResponseCodeShouldBeReturned(int responseCode) {
        assertThat(response.getStatusCode(), Matchers.is(responseCode));
    }



    @And("i should be presented with a unique {string}")
    public void iShouldBePresentedWithAUniqueBearerToken(String bearerToken) {
        bearerToken = response.jsonPath().getString("token");
        System.out.println(bearerToken);
        //check that there is a bearer token and it matches what is expected
        assertThat(bearerToken, Matchers.notNullValue());
    }

    @And("i should not be presented with any {string}")
    public void iShouldNotBePresentedWithAnyBearerToken(String bearerToken) {
        bearerToken = response.jsonPath().getString("token");
        System.out.println(bearerToken);
        //Check that there is no bearer token
        assertThat(bearerToken, Matchers.nullValue());
    }
}
