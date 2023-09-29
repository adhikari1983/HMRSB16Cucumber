# usually one feature file for one user story
# every single "Test case" in the cucumber is considered as a "Scenario"
Feature: Syntax API HMRS Flow

  Background:
    Given a JWT is generated
  # 1. Making the POST call
  ## 1.1 Making the POST call with normal JSON string format..........
  @api @t
  Scenario: Creating the employee using API
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable

  ## 1.2 Making the POST call again with normal JSON object ........................
  @json @tt
  Scenario: Creating an employee using json body
    Given a request is prepared for creating an employee using json payLoad
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable

  ## 1.3 Making the POST call again passing the data directly from feature file for dynamic scenario ...................
  @dynamic @ttt
  Scenario: Creating an employee using highly dynamic scenario
    Given a request is prepared for creating an employee with data "Ganesh", "Basti","MS","M", "1987-07-23", "Working", "SDET";
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable

  # 2.Making the GET call
  @api @t
  Scenario: Get the created employee
    Given a request is prepared for retrieving an employee
    When a GET call is made to retrieve the employee
    Then the status code for this employee is 200
    And the employee id "employee.employee_id" must match as globally stored employee id
    And this employee data at "employee" object matches with the data used to create the employee
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title |
      | Ganesh        | Basti        | MS              | Male       | 1987-07-23   | working    | SDET          |

    @partialUpdate @t
    Scenario: Partially update the created employee
      Given the request is prepared to update the employee firstname to "Nitesh"
      When a PATCH call is made to update the employee
      Then the status code for partially updating an employee is 201
      And the employee updated has the updated firstname "Nitesh"