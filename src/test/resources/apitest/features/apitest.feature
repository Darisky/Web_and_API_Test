@api
Feature: Api Test Automation
  @CRUD-User
  Scenario: Create, Read, Update, and Delete a User
    Given the DummyAPI endpoint is configured
    When I create a new user and get random email:
    | Title | First_Name | Last_Name |
    | mr    |    Den     |  Ned      |
    And the user is successfully created and an ID is returned
    And I fetch the user using the generated ID
    And the API should return status code 200
    And I update the user's first name to "DenUpdated"
    Then the user is successfully updated
    And I delete the user using the generated ID to clean up data

  @Get-TagList
  Scenario: Get Tag List
    When I fetch the tag list
    Then the API return tag list

  @Negative-Case
  Scenario Outline: Tester create user with various failed test
    When Create user with title "<Title>", first name "<First_Name>", last name "<Last_Name>" and email "<Email>"
    Then report error with code <Expected_Code>, "<Path>" and see message "<Expected_Message>"

  Examples:
    | Title | First_Name | Last_Name | Email          | Expected_Code  | Path           | Expected_Message                               |
    |       | Den        | Ned       | Den31@Mail.com | 400            | data.title     | `` is not a valid enum value for path `title`. |
    | mr    |            | Ned       | Den31@mail.com | 400            | data.firstName | Path `firstName` is required.                  |
    | mr    | Den        |           | Den31@mail.com | 400            | data.lastName  | Path `lastName` is required.                   |
    | mr    | den        | ned       |                | 400            | data.email     | Path `email` is required.                      |

