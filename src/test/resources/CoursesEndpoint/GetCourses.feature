Feature: Get all courses
  As a user, i want to retrieve all courses so that i can view the contents

  Background:
    Given i am in the swagger api and the endpoint is set to 'api/courses'

  @Happy
  Scenario: Get courses
    When i send a GET request
    Then i should receive a 200 response code

  @Happy
  Scenario: Checking that the courses are returned in JSON
    When i send a GET request
    Then i should receive a 200 response code
    And the output should be in JSON format

    @Sad
    Scenario: GET request for a bad endpoint
      Given in am in the swagger api and the endpoint is set to '/api/corses'
      When i send a GET request
      Then i should receive a 404 response code

    @Sad
    Scenario: Get request with an extra path
      When i send a GET request with the extra path '/abc'
      Then i should receive a 400 response code

    @Sad
    Scenario: Send the wrong request
      When i send a POST request
      Then i should receive a 405 response code
