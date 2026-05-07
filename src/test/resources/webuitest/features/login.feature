@web
Feature: Login
  @valid-login
  Scenario: User login
    Given user at home page about to login
    When user click Log In Button
    Then user see user name and password field
    And user input valid user name and password with "juniorTester" and "theJuniorTester"
    Then user click login button
    And user see "Welcome juniorTester" if success login

  @invalid-login
  Scenario: User login with invalid credential
    Given user at home page about to login
    When user click Log In Button
    Then user see user name and password field
    And user input invalid username and password with "wrongUserName" and "theJuniorTester"
    Then user click login button
    And user see error message "User does not exist."

  @empty-credential
  Scenario: User login with empty credential
    Given user at home page about to login
    When user click Log In Button
    Then user see user name and password field
    And user skip input username and password with "" and ""
    Then user click login button
    And user see error message "Please fill out Username and Password."