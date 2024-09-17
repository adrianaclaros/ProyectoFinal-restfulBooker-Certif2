Feature: Booking API tests

  Background: The user is working with the Booking API

#Validar que el endpoint de reservas funciona correctamente
  #debería pasar el test
  Scenario: Get all bookings
    Given I perform a GET call
    Then I verify that the status code is 200
    And I verify that the body does not have size 0

#Validar que el Get esté con Id Invalido
  #no debería pasar el test
  Scenario: Get with invalid ID
    Given I perform a GET call with invalid id
    Then I verify that the status code is 400
    And I log the response body

