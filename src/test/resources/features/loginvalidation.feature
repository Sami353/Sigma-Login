Feature: Login Validation Tests

  Background:
    Given I am on the login page

  @positive
  Scenario: Login with valid credentials - username first
    When I enter valid username
    And I enter valid password
    And I click login button
    Then I should see dashboard page

  @positive  
  Scenario: Login with valid credentials - password first
    When I enter valid password
    And I enter valid username
    And I click login button
    Then I should see dashboard page

  @negative
  Scenario: Login with invalid credentials - username first
    When I enter invalid username
    And I enter invalid password
    And I click login button
    Then I should see toast error message

  @negative  
  Scenario: Login with invalid credentials - password first
    When I enter invalid password
    And I enter invalid username
    And I click login button
    Then I should see toast error message

  @positive
  Scenario: Login with valid credentials - no click first
    When I enter valid password
    And I enter valid username
    And I click login button
    Then I should see dashboard page

  @negative
  Scenario: Login with invalid credentials - no click first
    When I enter invalid password
    And I enter invalid username
    And I click login button
    Then I should see toast error message

  @validation
  Scenario: Login with empty password
    When I leave password empty
    And I enter valid username
    And I click login button
    Then I should see field alert message

  @validation  
  Scenario: Login with empty username
    When I enter valid password
    And I leave username empty
    And I click login button
    Then I should see field alert message