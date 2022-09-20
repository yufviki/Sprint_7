package api.client;

import api.util.NewOrder;
import api.util.OrderTrack;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OrderClient {
    private final String ROOT = "/orders";
    private final String CANCEL_ORDER = "/orders/cancel";

    @Step("Send POST request to create an order: /api/v1/orders")
    public Response create(NewOrder newOrder) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(newOrder)
                .when()
                .post(ROOT);
    }

    @Step("Send PUT request to cancel the order: /api/v1/orders/cancel")
    public void cancel(OrderTrack orderTrack) {
            given()
                .header("Content-type", "application/json")
                .and()
                .body(orderTrack)
                .when()
                .put(CANCEL_ORDER);
    }

    @Step("Send GET request to receive a list of orders: /api/v1/orders")
    public Response getListOrder() {
        return given()
                .get(ROOT);
    }
}
