package ru.praktikum_services.qa_scooter;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierClient {
    private final String ROOT = "/courier";
    private final String LOGIN = "/courier/login";
    private final String COURIER_ID = "/courier/{courierId}";

    public Response create(NewCourier newCourier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(newCourier)
                .when()
                .post(ROOT);
    }

    public Response getCourierId(CourierAuthorization courierAuthorization) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierAuthorization)
                .when()
                .post(LOGIN);
    }

    public void delete(String courierId) {
         given()
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER_ID);
    }
}
