@featureTag
Feature: Sample feature

  @test
  Scenario: Sample scenario with Get Call
    Given Navigate to BookStore API
    When Run the Get books
    Then Validate book response

  @test2
  Scenario: Simple scenario using Post Call
    Given Navigate to BookStore API
    When post the create user
    Then Validate user created
