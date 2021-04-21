Feature: Blaze test

  Scenario: Do a small test on Blaze
    When I go to "https://blazedemo.com/"
    And I check that title is "Welcome to the Simple Travel Agency!"
    And I check the flights available from a location to another are "Paris\nPhiladelphia\nBoston\nPortland\nSan Diego\nMexico City\nSão Paolo" and "Buenos Aires\nRome\nLondon\nBerlin\nNew York\nDublin\nCairo"
    Then I want to book a flight from "Boston" to "New York"
    Then I confirm the flights from "Boston" to "New York"
    And I fullfill all the information needed
    Then I expect the confirmation "BlazeDemo Confirmation"