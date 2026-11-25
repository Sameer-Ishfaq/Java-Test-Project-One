package com.sparta.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GetCoursesSteps {
    private String BASE_URI = "https://spartaacademyapi20240530152521.azurewebsites.net/api/Courses";
    private Response response;
    private RequestSpecification request = RestAssured
            .given()
            .log().all()
            .header("Accept", "/json")
            .baseUri(BASE_URI);



    @Given("in am in the swagger api and the endpoint is set to {string}")
    public void inAmInTheSwaggerApiAndTheEndpointIsSetToApiCorses(String basePath) {
        request = RestAssured
                .given()
                .header("Accept", "/json")
                .baseUri(BASE_URI)
                .basePath(basePath);
    }
    @When("i send a GET request")
    public void iSendAGETRequest() {
        response = request
                .accept("application/json")
                .log().all()
                .when()
                .get()
                .thenReturn();

    }
    @When("i send a GET request with the extra path {string}")
    public void iSendAGETRequestWithTheExtraPathAbc(String extraPath) {
        response = request
                .given()
                .when()
                .get("/" + extraPath)
                .thenReturn();
    }

    @When("i send a POST request")
    public void iSendAPOSTRequest() {
        response = request
                .when()
                .post()
                .thenReturn();
    }

    @Then("i should receive a {int} response code")
    public void iShouldReceiveAResponseCode(int expectedCode) {
        System.out.println("Status Code: " + response.getStatusCode());
        assertThat(response.statusCode(), is(expectedCode));
    }

    @And("the output should be in JSON format")
    public void theOutputShouldBeInJSONFormat() {
        System.out.println("Content Type: " + response.getContentType());
        System.out.println("Response Code" + response.statusCode());
        assertThat(response.getContentType(), Matchers.containsString("application/json"));
    }

}
