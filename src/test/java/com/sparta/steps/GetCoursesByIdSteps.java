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

public class GetCoursesByIdSteps {
    private String BASE_URI = "https://spartaacademyapi20240530152521.azurewebsites.net/api/Courses";
    private RequestSpecification request = RestAssured
            .given()
            .log().all()
            .header("Accept", "/json")
            .baseUri(BASE_URI);
    private Response response;

    @When("i send a GET request with a valid id {int}")
    public void iSendAGETRequestWithAValid(int validId) {
        response = request
                .accept("application/json")
                .when()
                .get("/" + validId)
                .thenReturn();
    }

    @When("i send a PUT request to the api")
    public void iSendAPUTRequestToTheApi() {
        response = request
                .accept("application/json")
                .when()
                .put()
                .thenReturn();
    }

    @When("i send a GET request to an invalid {string}")
    public void iSendAGETRequestToAnInvalidId(Object invalidId) {
        response = request
                .accept("application/json")
                .when()
                .get(String.valueOf(invalidId))
                .thenReturn();
    }

    @Then("i should get a {int} response code")
    public void iShouldGetAResponseCode(int responseCode){
        assertThat(response.statusCode(), is(responseCode));
    }
}
