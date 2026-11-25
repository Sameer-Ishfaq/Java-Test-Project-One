Feature: As a tester, i want send a DELETE the spartans by ID, so that i can test its functionality

  Background:
    Given i am logged in and have a bearer token


    @Happy
    Scenario: DELETE request for a valid id;
      Given a spartan exists with a valid id
    When i send a DELETE request with a valid id
    Then i should get back a 204 response code

  @Sad
  Scenario Outline: Deleting course with an invalid id
    When i send a DELETE request to an invalid id
    Then i should get back a 404 response code

    Examples:
      |Id  |
      |1.1    |
      |0      |
      |-3     |
      |123456 |
      |*%^    |
      |abc    |

