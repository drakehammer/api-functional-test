Feature: Register users
  In order to be able to manage my products
  I as a user want to be able to register
  To be able to perform x actions

  Scenario: User registry success
    Given Diego it's a client who wants to manage his products
    When He send the login information
    Then He must get a account to login when required

  Scenario: Consult users
    Given Diego as administrator wants to get the users from application
    When He send the consult information
    Then He must get a list of users