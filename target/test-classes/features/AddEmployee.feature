Feature: Adding employee in HRMS application

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    Then user is successfully logged in the application
    When user clicks on PIM option
    And user clicks on add employee button

  @test
  Scenario: adding one employee
    When user enters firstname and lastname
    And user clicks on save button
    Then employee added successfully

  @sample
  Scenario: Adding one employee from feature file
    When user enters "Kiran" and "Gorkhali" and "Adhikari"
    And user clicks on save button
    Then employee added successfully

  @outline
  Scenario Outline: adding multiple employee using scenario outline
    When user enters "<firstName>" and "<middleName>" and "<lastName>" in data driven format
    And user clicks on save button
    Then employee added successfully
    Examples:
      | firstName | middleName | lastName |
      | Parbati   | Kali       | Poudel   |
      | Kripa     | Sani       | Adhikari |
      | Kiyan     | Babu       | Adhikari |

# FIRST ->  hooks(@before -> pre condition) , background executes  => then, first row -> |Parbati  |Kali      |Poudel  |
# THEN hooks(@after -> post condition) at the end  X3 times happens

  @datatable
  Scenario: adding multiple employees using data table
    When user enters firstname and middlename and lastname and verify employee has added
      | firstName | middleName | lastName |
      | Hari      | ms         | Poudel   |
      | Shyam     | ms         | Adhikari |
      | Gopal     | ms         | Adhikari |

  @excel
  Scenario: adding multiple employees using excel file
    When user adds multiple employees using excel from "EmployeeDataBatch16" and verify it