Feature: Send a POST request to create a new spartan
  As an admin, i want to be able to create new spartans, so that they can be added to the existing database

  Background:
  Given i am authenticated

  @Happy
  Scenario: Successful POST request
  When i send a POST request
  Then i should be returned a 200 response code

  @Sad
  Scenario: Bad POST request
  When i send a POST request
  Then i should be returned a 400 response code