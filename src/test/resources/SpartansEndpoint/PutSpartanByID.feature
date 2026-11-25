Feature: Updating Spartan details via PUT request
  As a user, i want to update Spartan details, so that their information isn't outdated

  Background:
    Given i am on the api and the endpoint is set to 'api/Spartans/{Id}'

  @Happy
  Scenario: Updating spartan details with a valid Id
    When i send a PUT request with a valid Id
    Then i should receive a 200 response code

  @Sad
  Scenario: Updating spartan details with an invalid Id
    When i send a PUT request with an Invalid Id
    Then i should receieve a 404 response code