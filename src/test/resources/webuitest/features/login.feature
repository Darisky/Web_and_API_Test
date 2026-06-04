@web @login
Feature: Login
  @valid-login
  Scenario: User login with valid credential
    Given user at home page about to login
    And user click Log In Button
    And user see user name and password field
    When user input valid user name and password with "juniorTester" and "theJuniorTester"
    And user click Log In at login field
    Then user see "Welcome juniorTester" if success login

  @invalid-login
  Scenario Outline: User login with invalid credential
    Given user at home page about to login
    And user click Log In Button
    And user see user name and password field
    When user input invalid user name and password with "<User_Name>" and "<Password>"
    And user click Log In at login field
    Then user see error message "<Expected_Error_Message>"
  Examples:
    | User_Name       | Password        | Expected_Error_Message                 |
    | notJunior       | theJuniorTester | User does not exist.                   |
    | juniorTester    | wellPassword    | Wrong password.                        |
    |                 |                 | Please fill out Username and Password. |