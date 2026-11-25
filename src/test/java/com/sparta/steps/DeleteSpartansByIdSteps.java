package com.sparta.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteSpartansByIdSteps {
    private final String BASE_URI = "https://spartaacademyapi20240530152521.azurewebsites.net/api/Spartans";
    private final String BASE_URI_FOR_LOGIN = "https://spartaacademyapi20240530152521.azurewebsites.net";

    private RequestSpecification request = RestAssured
            .given()
            .header("Accept", "application/json")
            .baseUri(BASE_URI);

    private Response response;
    private String token;
    private int createdId;

    @Given("i am logged in and have a bearer token")
    public void iAmLoggedInAndHaveABearerToken() {
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

    @Given("a spartan exists with a valid id")
    public void aSpartanExistsWithAValidId() {
       //  createdId = 2;
        //    ************************************************************************
//          ** Attempting a delete request using this JSON body returns 500 error. Did everything i could to resolve this but no progress
//          ** Using an int variable and assigning it an existing id within the api passes the test
//          ** Code below to reproduce the error
        String body = """
                {
                  "id": 2,
                  "firstName": "Sameer",
                  "lastName": "Ishfaq",
                  "university": "Staffordshire University",
                  "degree": "Computer Science",
                  "course": {
                  "id" : 1,
                  "name": "Engineering",
                  "stream": {
                   "id": 1,
                   "name": "Technology"
                   },
                  "streamId": 1,
                  "trainer": "Trainer"
                  },
                  "courseId": 1,
                  "graduated": true
                 }
                """;
		response = request
                .header("Authorization","Bearer " + token)
                .contentType("application/json")
                .body(body)
                .when()
                .log().all()
                .post()
                .thenReturn();
        assertThat(response.statusCode(), Matchers.is(201));

        createdId = response.jsonPath().getInt("id");
 //  ************************************************************************
    }

    @When("i send a DELETE request with a valid id")
    public void iSendADELETERequestWithAValidId() {
        response = request
                .header("Authorization","Bearer " + token)
               // .log().all()
                .when()
                .delete("/" + createdId)
                .thenReturn();

    }
    @When("i send a DELETE request to an invalid id")
    public void iSendADELETERequestToAnInvalidId(){
        response = request
                .header("Authorization","Bearer " + token)
                .when()
                .delete("/" + createdId)
                .thenReturn();
    }

    @Then("i should get back a {int} response code")
    public void iShouldGetBackAResponseCode(int responseCode) {
        assertThat(response.statusCode(), Matchers.is(responseCode));
    }


}
