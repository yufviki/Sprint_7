package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTests extends TestBase {
    public List<String> color;
    OrderClient orderClient = new OrderClient();

    public OrderCreateTests(List<String> color){
        this.color = color;
    }

    @Parameterized.Parameters(name = "color: {0}")
    public static Object[][] colorData() {
        return new Object[][] {
                {Arrays.asList("BLACK", "GREY")},
                {Arrays.asList("BLACK")},
                {Arrays.asList("GREY")},
                {null},
        };
    }

    @Test
    @DisplayName("Success Order Create With Scooter Color")
    @Description("The test verifies the successful creation of an order with different colors of the scooter: 201 Created")
    public void successOrderCreateWithScooterColorTest() {
        NewOrder newOrder = NewOrder.getRandomOrderAllArgsAndScooterColor(color);

        Response response = orderClient.create(newOrder);
        response.then().log().all()
                .statusCode(201)
                .and()
                .assertThat().body("track", notNullValue());

        int trackId = response
                .then().log().all()
                .extract().path("track");

        OrderTrack orderTrack = new OrderTrack(Integer.toString(trackId));
        cancelOrder(orderTrack);
    }

    public void cancelOrder(OrderTrack orderTrack) {
        orderClient.cancel(orderTrack);
    }
}
