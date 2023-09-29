Feature: Editing personal details in HRMS application

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    Then user is successfully logged in the application
    When user clicks on PIM option and employee list option

  @smoke @regression @searchEmployee
  Scenario: Search employee by id
    And user enters valid employee id
    And user clicks on search button
    Then user is able to see employee information
    And user clicks on same valid employee id on the listed employee data table

  @editEmployee
  Scenario: Edit employee details
    When user edit firstName and middleName and lastName and gender and maritalStatus and nationality and verify it
      | firstName | middleName | lastName | gender | maritalStatus | nationality |
      | Kiran     | Prayas     | Adhikari | Male   | Married       | Nepalese    |
