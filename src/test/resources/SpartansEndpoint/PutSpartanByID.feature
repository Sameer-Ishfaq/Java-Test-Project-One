Feature: Updating Spartan details via PUT request
  As a user, i want to update Spartan details, so that their information isn't outdated

  Background:
    Given i am authenticated

  @Happy
  Scenario Outline: Updating spartan details with a valid Id
    When i send a PUT request with a valid <Id>
    Then i should receive a 204 response code in return
    Examples:
      |Id   |
      |1    |
      |10   |

  @Sad
  Scenario Outline: Updating spartan details with a numerical invalid Id
    When i send a PUT request with an invalid numerical <Id>
    Then i should receive a 404 response code in return
    Examples:
      |Id  |
      |-3     |
      |123456 |


    @Sad
    Scenario Outline: Updating spartan details with a non numerical invalid Id
      When i send a PUT request with an invalid non-numerical "<Id>"
      Then i should receive a 400 response code in return
      Examples:
        |Id     |
        |*%^    |
        |abc    |
