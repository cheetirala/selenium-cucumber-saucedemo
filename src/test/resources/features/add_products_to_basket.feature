Feature: Add products to basket

  Scenario: User adds highest price product to the basket
    Given the user navigate to SauceDemo
    When the user login with valid credentials
    Then the user logged in successfully
    When the user adds the highest priced product to the basket
    Then the product should be added successfully
    When the user navigate to basket page
    Then the user can see added product in the basket

