Feature: Login related scenarios

  Background:
   # Given user is navigated to HRMS application               -> now this step is implemented by Hooks

  # these tags ->@sprint14 @regression @smoke @newTestCase @newFeature needs to maintain on tag to run
  @sprint1 @regression @smoke @newTestCase @newFeature @validLogin @Repeat(3)
  Scenario: Valid admin login
    # don't add anything in the step after creating the step definitions
    #Given user is navigated to HRMS application
    When user enters valid admin username and password
    And user clicks on login button
    Then user is successfully logged in the application

    # ess is just the term, generally we prefer to say for employee in industry.
  @sprint1 @employee @login
  Scenario: valid ess login
    # Given user is navigated to HRMS application
    When user enters ess username and password
    And user clicks on login button
    Then user is successfully logged in the application

  @sprint1   @invalidLogin
  Scenario: invalid admin login
    # Given user is navigated to HRMS application
    When user enters invalid admin username and password
    And user clicks on login button
    Then error message is displayed

    @negative
    Scenario Outline: negative login test
      When user enters "<username>" and "<password>" and verifying the "<error>" for the combinations
      Examples:
        | username | password    | error |
        |admin     |adg          |Invalid credentials |
        |admin1    |Hum@nhrm123  |Invalid credentials |
        |          |Hum@nhrm123  |Username cannot be empty |
        |admin     |             |Password cannot be empty |