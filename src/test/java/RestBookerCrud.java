import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class RestBookerCrud {

    // Validar que el endpoint de reservas funciona correctamente
    @Test
    public void getBookings() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = RestAssured
                .when().get("/booking");
        response.then().assertThat().statusCode(200);
        response.then().log().body();  // Imprime el cuerpo de la respuesta
    }

    // Validar que el Get esté con Id Invalido
    @Test
    public void getBookingsInvalido() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = RestAssured
                .given().pathParam("id", "BFDS")
                .when().get("/booking/{id}");
        response.then().assertThat().statusCode(400);
        response.then().log().body();  // Imprime el cuerpo de la respuesta
    }
}
