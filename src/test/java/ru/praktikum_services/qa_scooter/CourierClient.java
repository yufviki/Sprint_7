package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierClient {
    private final String ROOT = "/courier";
    private final String LOGIN = "/courier/login";
    private final String COURIER_ID = "/courier/{courierId}";

    @Step("Send POST request to create a courier: /api/v1/courier")
    public Response create(NewCourier newCourier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(newCourier)
                .when()
                .post(ROOT);
    }

    @Step("Send POST request to receive courierId: /api/v1/courier/login")
    public Response getCourierId(CourierAuthorization courierAuthorization) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierAuthorization)
                .when()
                .post(LOGIN);
    }

    @Step("Send DELETE request to delete the courier: /api/v1/courier/:id")
    public void delete(String courierId) {
         given()
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER_ID);
    }
}
