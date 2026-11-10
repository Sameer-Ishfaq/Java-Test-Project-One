package com.sparta.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/AuthEndpoint",
        glue = "com.sparta.steps",
        plugin = {"pretty",
        "html:target/cucumber-reports/report.html",
        "html:target/cucmber-reports/report.json"},
        tags = "not @ignore",
        publish = false
)

public class LoginDetailsTestRunner {
}
