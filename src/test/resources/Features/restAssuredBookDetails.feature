@featureTag
Feature: Sample feature

  @test2
  Scenario: Book Store Create User
    Given Navigate to BookStore API
    When post the create user
    Then Validate user created


  @E2E
  Scenario: Book Store Generate Bearer
    Given Generate Bearer Api
    When Authorized with userCredentials "TestUser" "Pass@123"
    Then validate response
    When Check is Authorized with "TestUser" "Pass@123"
    Then Validate authorized true

  @test
  Scenario: Sample scenario with Get Call and POJO
    Given Navigate to BookStore API
    When Run the Get books
    Then Validate book response