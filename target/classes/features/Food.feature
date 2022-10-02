Feature: User should be able to ADD food to cache

  Background:
#  Given reset cached food in Food Delivery API
  #truncate table
  Given "food" table is truncated

  Scenario: User adds new item to cache
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name   | foodType  |
      | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
    Then verify that status code is 200.
    Then verify that food has been successfully added
      | description | imageUrl        | price | name   | foodType  |
      | Wine        | https:foods.com | 20.00 | Merlot | Beverages |


  Scenario: Adding new food without image url - negative
    Given add new food to FoodDelivery with the following fields
      | description | price | name   | foodType  |
      | Wine        | 20.00 | Merlot | Beverages |
    Then verify that status code is 403.
    Then verify response error message "Invalid request - Food image url cannot be null or empty."


  Scenario: User commits food in cache
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name   | foodType  |
      | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
    Then verify that status code is 200.
    Then verify that food has been successfully added
      | description | imageUrl        | price | name   | foodType  |
      | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
    When user commits the cache to DB
    Then app should send the following response
      | numberOfFoodSaved | message                       |
      | 1                 | Food Cache is committed to db |
    And the following food should be commited to DB
      | description | food_type | image_url      | name   | price |
      | Wine        | 0         | http:foods.com | Merlot | 20.00 |
