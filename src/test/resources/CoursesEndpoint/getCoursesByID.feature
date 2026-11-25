Feature: Get all courses
  As a user, i want to retrieve all courses by ID so that i can view the contents

  Background:
    Given i am in the swagger api and the endpoint is set to 'api/courses/{Id}'

    @Happy
  Scenario: Get course by Id
    When i send a GET request with a valid id 1
    Then i should get a 200 response code

  @Happy
  Scenario: The returned output should be in JSON format
    When i send a GET request
    Then i should receive a 200 response code
    And the output should be in JSON format

  @Sad
  Scenario Outline: Getting course with an invalid Id
    When i send a GET request to an invalid 'id'
    Then i should get a 400 response code

    Examples:
    |Id  |
    |1.1    |
    |0      |
    |-3     |
    |123456 |
    |*%^    |
    |abc    |

    @Sad
    Scenario: Send the wrong request to the api
      When i send a PUT request to the api
      Then i should get a 405 response code





