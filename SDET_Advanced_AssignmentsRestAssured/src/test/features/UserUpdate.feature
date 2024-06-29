Feature: Login to regres.in

  Background: User has access to get users

    Scenario Outline: Authorized user able to get user details
    Given Url is given "BASE_URL"
    When System gets user ID <id>
    Then System verifies user exist <statusCode>
    Then The response will return ID <id> and email "<user_email>" and first name "<user_first_name>" and last name "<user_last_name>"

    Examples:
      | statusCode | id  | user_email              | user_first_name    | user_last_name   |
      | 200        | 2   | janet.weaver@reqres.in  | Janet              | Janet            |

  Scenario Outline: Authorized user update user details
    Given Url is given "BASE_URL"
    When System updates user details name "<name>" and job "<job>" for user ID <id>
    Then System verifies user exist <statusCode>
    Then The response will return name "<name>" and job "<job>" and updatedAt
    Examples:
      | statusCode | id  |  name     | job    |
      | 200        | 2   |  API Demo | API Tester  |