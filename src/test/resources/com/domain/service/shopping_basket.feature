Feature: Shopping Basket Test

  Scenario: shopping basket has 4 fruits types
    Given The shopping basket has 1 Apple, 2 Banana, 3 Melon, 4 Lime
    When I calculate the final price
    Then The price should show 2.2

  Scenario: shopping basket has only apple
    Given The shopping basket has 1 Apple
    When I calculate the final price
    Then The price should show 0.35

  Scenario: shopping basket has apple and banana
    Given The shopping basket has 1 Apple and 2 banana
    When I calculate the final price
    Then The price should show 0.75

  Scenario: shopping basket has apple and banana and melon
    Given The shopping basket has 1 Apple and 2 banana and 3 melon
    When I calculate the final price
    Then The price should show 1.75