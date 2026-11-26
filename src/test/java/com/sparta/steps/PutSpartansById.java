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

public class PutSpartansById {
    private SharedAuthStep auth;
    public PutSpartansById(SharedAuthStep auth){
        this.auth = auth;
    }
    private String BASE_URI = "https://spartaacademyapi20240530152521.azurewebsites.net/api/Spartans";

    private RequestSpecification request = RestAssured
            .given()
            .header("Accept", "/json")
            .baseUri(BASE_URI);
    private Response response;


    private String validBody = """
            {
                   "id": %s,
                   "firstName": "Sameer",
                   "lastName": "Ishfaq",
                   "university": "Staffordshire University",
                   "degree": "Computer Science",
                   "course": {
                     "id": 1,
                     "name": "Tech 615",
                     "stream": {
                       "id": 1,
                       "name": "Tech 615",
                       "courses": []
                     },
                     "streamId": 1,
                     "trainer": "Nish",
                     "startDate": "2026-11-25T15:43:01.968Z",
                     "endDate": "2026-11-25T15:43:01.968Z",
                     "spartans": []
                   },
                   "courseId": 1,
                   "graduated": true
                 }
            """;

    @When("i send a PUT request with a valid {int}")
    public void iSendAPUTRequestWithAValid(int id) {
        String body = validBody.formatted(id);
        response = request
                .header("Authorization","Bearer " + auth.getToken())
                .contentType("application/json")
                .body(body)
                .when()
                .put("/" + id)
                .thenReturn();
        System.out.println(response.asPrettyString());
    }

    @When("i send a PUT request with an invalid numerical {int}")
    public void iSendAPUTRequestWithAnInvalidNumericalId(int invalidId) {
        String body = validBody.formatted(invalidId);
        response = request
                .header("Authorization","Bearer " + auth.getToken())
                .contentType("application/json")
                .body(body)
                .when()
                .put("/" + invalidId)
                .thenReturn();

        System.out.println(response.asPrettyString());
    }

    @When("i send a PUT request with an invalid non-numerical {string}")
    public void iSendAPUTRequestWithAnInvalidNonNumerical(String invalidId) {
        String body = validBody.formatted(invalidId);
        response = request
                .header("Authorization","Bearer " + auth.getToken())
                .contentType("application/json")
                .body(body)
                .when()
                .put("/" + invalidId)
                .thenReturn();

        System.out.println(response.asPrettyString());
    }

    @Then("i should receive a {int} response code in return")
    public void iShouldReceiveAResponseCodeInReturn(int responseCode) {
        assertThat(response.statusCode(), Matchers.is(responseCode));
    }

}

