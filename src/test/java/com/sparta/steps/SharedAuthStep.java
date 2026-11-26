package com.sparta.steps;

import io.restassured.response.Response;

public class SharedAuthStep {
    public String token;
    public int spartanId;
    public Response response;

    public String getToken() {

        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }

    public int getSpartanId() {

        return spartanId;
    }

    public void setSpartanId(int spartanId) {

        this.spartanId = spartanId;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
