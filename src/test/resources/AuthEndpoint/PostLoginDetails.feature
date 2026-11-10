Feature: Post with username and password
  As a user,
  I want to enter a valid username and password,
  So That i am logged in,
  And receive bearers token


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
