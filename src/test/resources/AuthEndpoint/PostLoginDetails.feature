Feature: Post with username and password
  as a user,
  i want to enter a valid username and password,
  so that i am logged in,
  and receive a bearers token


  Background:
    Given i am in the swagger api and the endpoint is set to '/Auth/login'

  @Happy
    Scenario: Post valid login details and receive a bearers token
    When i enter 'sparta' as username and 'global' as password
    Then a 200 response code should be returned
    And i should be presented with a unique 'bearerToken'

  @Sad
    Scenario: Post invalid login details
    When i enter an invalid username like 'badUser' and an invalid password like 'badPassword'
    Then a 401 response code should be returned
    And i should not be presented with any 'bearerToken'
