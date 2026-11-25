Feature: Get all Spartans by ID
  As a user, i want to retrieve all spartans by ID so that i can view the contents



    Background:
  Given i am authenticated


  @Happy
  Scenario Outline: Get spartan by Id
    When i send a GET request retrieving a valid id 1
    Then i should get a 200 response code back
    Examples:
    |id   |
    |1    |
    |10   |

  @Happy
  Scenario: Checking that the returned format is JSON
    When i send a GET request to the endpoint with id 2
    Then i should get a 200 response code back
    And the output should be in Json format

  @Sad
  Scenario Outline: Getting Spartans with an invalid Id
    When i send a GET request to get an invalid 'id'
    Then i should get a 400 response code back
    Examples:
      |Id  |
      |1.1    |
      |1.1.1  |
      |0      |
      |-3     |
      |123456 |
      |*%^    |
      |abc    |

  @Sad
  Scenario: Send the wrong request to the api
    When i send a PUT request
    Then i should get a 405 response code back