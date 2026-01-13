Feature: Add highest price item to cart

  Scenario: User adds highest price product to the cart
    Given the user navigate to SauceDemo
    When the user login with valid credentials
    And the user adds the highest priced item to the cart
    Then the item should be added successfully
