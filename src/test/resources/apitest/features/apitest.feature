@api
Feature: Api Test Automation
  @CRUD-User
  Scenario: Create, Read, Update, and Delete a User
    Given the DummyAPI endpoint is configured
    When I create a new user with the title "mr", first name "Den", last name "Ned" and a randomly generated email
    Then the user is successfully created and an ID is returned
    When I fetch the user using the generated ID
    Then the API should return status code 200
    When I update the user's first name to "DenUpdated"
    Then the user is successfully updated
    And I delete the user using the generated ID to clean up data

  @Get-TagList
  Scenario: Get Tag List
    When I fetch the tag list
    Then the API return tag list

  @Negative-Case
  Scenario: Tester create user without email
    When Create user with title "mr", first name "Den", last name "Ned" and without email
    Then report error with code 400 and see message "BODY_NOT_VALID"