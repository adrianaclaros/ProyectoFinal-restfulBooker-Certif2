package stepDefinitions;

import constants.BookerEndpoints;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import utils.Request;

import static org.hamcrest.Matchers.not;

public class BookingSteps {
    Response response;

    // Realiza la solicitud GET al endpoint de reservas
    @Given("I perform a GET call")
    public void getBookings() {
        response = Request.get(BookerEndpoints.GET_BOOKINGS);
    }

    @Then("I verify that the status code is {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();

        if (actualStatusCode != expectedStatusCode) {
            String errorMessage = "Expected status code: " + expectedStatusCode + "\n" +
                    "Actual status code: " + actualStatusCode + "\n" +
                    "Response body: " + response.getBody().asString();
            throw new AssertionError(errorMessage);
        }
        response.then().statusCode(expectedStatusCode); // Asegura que el código de estado sea el esperado
    }

    @And("I verify that the body does not have size {int}")
    public void verifyResponseSize(int size) {
        response.then().assertThat().body("size()", not(size));
    }

    // Realizar la solicitud GET con un ID inválido
    @Given("I perform a GET call with invalid id")
    public void getBookingWithInvalidId() {
        response = Request.get(BookerEndpoints.GET_BOOKING_BY_ID.replace("{id}", "BFDS"));
    }

    // Verificar que el código de estado es 400
    @Then("I verify that the status code is 400 when getting with invalid id")
    public void verifyStatusCode400() {
        int expectedStatusCode = 400;
        int actualStatusCode = response.getStatusCode();

        if (actualStatusCode != expectedStatusCode) {
            String errorMessage = "Expected status code: " + expectedStatusCode + "\n" +
                    "Actual status code: " + actualStatusCode + "\n" +
                    "Response body: " + response.getBody().asString();
            throw new AssertionError(errorMessage);
        }
        response.then().statusCode(expectedStatusCode); // Asegura que el código de estado sea 400
    }

    // Registrar el cuerpo de la respuesta
    @And("I log the response body")
    public void logResponseBody() {
        System.out.println(response.getBody().asString()); // Imprime el cuerpo de la respuesta en los logs
    }
}
