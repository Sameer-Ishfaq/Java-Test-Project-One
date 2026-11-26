package com.sparta.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;

public class PostSpartanDetails {
    private SharedAuthStep auth;
    private String BASE_URI = "https://spartaacademyapi20240530152521.azurewebsites.net/api/Spartans";

    String body = """
            
            """;

    @Then("i should be returned a {int} response code")
    public void iShouldBeReturnedAResponseCode(int responseCode) {

    }
}
