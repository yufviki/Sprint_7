package ru.praktikum_services.qa_scooter;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OrderClient {
    private final String ROOT = "/orders";
    private final String CANCEL_ORDER = "/orders/cancel";

    public Response create(NewOrder newOrder) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(newOrder)
                .when()
                .post(ROOT);
    }

    public void cancel(OrderTrack orderTrack) {
        given()
                .header("Content-type", "application/json")
                .and()
                .body(orderTrack)
                .when()
                .put(CANCEL_ORDER);
    }

    public Response getListOrder() {
        return given()
                .get(ROOT);
    }
}
