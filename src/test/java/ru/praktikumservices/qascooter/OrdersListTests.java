package ru.praktikumservices.qascooter;

import api.client.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersListTests extends TestBase {
    OrderClient orderClient;

    @Before
    public void setUpOrderClient() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Get Orders List")
    @Description("The test checks the request to receive the entire list of orders: 200")
    public void getOrdersListTest() {
        Response response = orderClient.getListOrder();
        response.then().log().all()
                .statusCode(200)
                .and()
                .assertThat().body("orders", notNullValue());
    }
}