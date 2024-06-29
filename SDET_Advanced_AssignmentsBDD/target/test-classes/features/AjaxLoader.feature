Feature: Login to HRM Application

  Background:


  @ValidCredentials
  Scenario: AjaxLoader verification
    Given User is on application page
    When User confirms Ajax Loader link is present
    Then User clicks on Ajax Loader link
    Then User confirms Click Me! is present
    Then User clicks on Click Me! link
    Then User confirms pop-up is present