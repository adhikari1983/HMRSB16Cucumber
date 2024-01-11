Feature: Adding employee in HRMS application

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    Then user is successfully logged in the application
    When user clicks on PIM option
    And user clicks on add employee button

  #1 we hard coded -> by passing the value using selenium webDriver from its element to add the employee info
  @test
  Scenario: adding one employee
    When user enters firstname and lastname
    And user clicks on save button
    Then employee added successfully

  #2 we just passed the data in Scenario step of feature file as "value"
  @sample1
  Scenario: Adding one employee from feature file
    # "Kiran" and "Gorkhali" and "Adhikari" => passing as values
    When user enters "Kiran" and "Gorkhali" and "Adhikari"
    And user clicks on save button
    Then employee added successfully

   #3 we just passed the data in Scenario Outline step of feature file as "<key>"   &  value  pair w/Example table
  @outline                                                          #e.g "<Kiran>"  & "Kiran"
  Scenario Outline: adding multiple employee using scenario outline
    # "<firstName>" and "<middleName>" and "<lastName>" => in angular for <> in double quotes "" passing as keys
    # |  Parbati  |       Kali        |       Poudel   |   in double quotes "" passing  as values in below Examples table
    When user enters "<firstName>" and "<middleName>" and "<lastName>" in data driven format
    And user clicks on save button
    Then employee added successfully
    Examples:
      | firstName | middleName | lastName |
      | Parbati   | Kali       | Poudel   |
      | Kripa     | Sani       | Adhikari |
      | Kiyan     | Babu       | Adhikari |
                                               # here Example table is applicable for one complete Scenario:
# FIRST -> hooks(@before -> preCondition), background executes => then, first row -> |Parbati |Kali |Poudel| gets added
# THEN -> hooks(@after -> postCondition) at the end => X3 times happens


   #4 forth we use DataTable in Scenario
  @datatable
  Scenario: adding multiple employees using data table
    When user enters firstname and middlename and lastname and verify employee has added
      | firstName | middleName | lastName |
      | Hari      | Krishna    | Poudel   |
      | Shyam     | Babu       | Adhikari |
      | Gopal     | Man        | Singh    |
                                             # here DataTable is applicable for just one step

   #5 fifth we use Excel sheet to pass the data from Scenario
  @excel
  Scenario: adding multiple employees using excel file
    When user adds multiple employees using excel from the sheet name "EmployeeDataBatch16" and verify it


  @DB
  Scenario: Adding one employee from feature file
    When user enters "Kiran" and "Gorkhali" and "Adhikari"
    And user clicks on save button
    Then employee added successfully
    Then verify employee is stored in database

