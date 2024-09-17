package utils;

import constants.BookerEndpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class Request {

    //1
    public static Response get(String endpoint) {
        RestAssured.baseURI = BookerEndpoints.BASE_URL;
        Response response = RestAssured
                .when().get(endpoint);
        response.then().log().body();
        return response;
    }

 /*   static {
        // Registra el parser para manejar respuestas de tipo text/plain
        RestAssured.registerParser("text/plain", Parser.TEXT);
    }*/

    //2
    public static Response getById(String endpoint, String id) {
        RestAssured.baseURI = BookerEndpoints.BASE_URL;
        Response response = RestAssured
                .given().pathParam("id", id)
                .when().get(endpoint);
        response.then().log().body();
        return response;
    }

    //4
    public static Response post(String endpoint, String payload) {
        RestAssured.baseURI = BookerEndpoints.BASE_URL;
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json") // Asegúrate de enviar JSON
                .header("Accept", "application/json")       // Espera una respuesta JSON
                .body(payload)
                .when().post(endpoint);
        response.then().log().body();
        return response;
    }
}
