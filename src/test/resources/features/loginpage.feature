Feature: Login Functionality for Sigma Website

  Background:
    Given I am on the Sigma login page

  Scenario: Successful login with valid credentials
    Given I enter username "robin" and password "sigma@123"
    When I click on the login button
    Then I should be logged in successfully

  Scenario Outline: Unsuccessful login with invalid credentials
    Given I enter username "<username>" and password "<password>"
    When I click on the login button
    Then I should see error message "<error_message>"

    Examples:
      | username | password   | error_message                     |
      | xkajddd  | sigma@123  | Invalid UserName or Password !!!  |
      | robin    | sd000ss    | Invalid UserName or Password !!!  |

  Scenario Outline: Unsuccessful login with empty credentials
    Given I enter username "<username>" and password "<password>"
    When I click on the login button
    Then I should see alert "<error_message>"

    Examples:
      | username | password   | error_message                     |
      |          | sigma@123  | Username is required !            |
      | robin    |            | Password is required !            |
