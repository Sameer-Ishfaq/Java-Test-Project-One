Feature: As a user i want to send a GET request to spartans so that i can view their information

  Background:
    Given i am on the api and the endpoint is set to 'api/Spartans'



  @Happy
    Scenario: Sending a GET request
    When i send a GET request
    Then i should receive a 200 response code

  @Happy
  Scenario: Checking that the Spartans details are returned in JSON
    When i send a GET request
    Then i should receive a 200 response code
    And the output should be in JSON format


  @Sad
  Scenario: Sending a GET request to a bad endpoint
    When i send a GET request to the api with the bad endpoint '/api/spartns'
    Then i should get a 404 response code in return

  @Sad
  Scenario: Sending a GET request with an extra path
    When i send a GET request to the extra path '/123'
    Then i should get a 404 response code in return

