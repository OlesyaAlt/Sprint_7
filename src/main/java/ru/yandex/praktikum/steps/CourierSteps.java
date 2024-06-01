package ru.yandex.praktikum.steps;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.models.Courier;
import static io.restassured.RestAssured.given;

public class CourierSteps {

    public static final String COURIER = "/api/v1/courier";
    public static final String LOGIN = "/api/v1/courier/login";
    public static final String COURIER_DELETE = "/api/v1/courier/{id}";

    public ValidatableResponse createCourier(Courier courier){

        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER)
                .then();

    }
    public ValidatableResponse login(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(LOGIN)
                .then();

    }
    public ValidatableResponse delete(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .pathParam("id", courier.getId())
                .when()
                .delete(COURIER_DELETE)
                .then();
    }
}
