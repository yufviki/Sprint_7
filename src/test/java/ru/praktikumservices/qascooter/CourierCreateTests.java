package ru.praktikumservices.qascooter;

import api.client.CourierClient;
import api.util.CourierAuthorization;
import api.util.NewCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class CourierCreateTests extends TestBase {
    NewCourier newCourier;
    CourierClient courierClient;

    @Before
    public void setUpCourierClient() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Success Courier Create")
    @Description("The test verifies the successful creation of a courier: 201 Created")
    public void successCourierCreateTest() {
        newCourier = NewCourier.getRandomCourierAllArgs();

        Response response = courierClient.create(newCourier);
        response.then().log().all()
                .statusCode(201)
                .and()
                .assertThat().body("ok", is(true));

        deleteCourier();
    }

    @Test
    @DisplayName("Error When Courier Create Identical")
    @Description("The test checks a request with a duplicate login: 409 Сonflict")
    public void errorWhenCourierCreateIdenticalTest() {
        newCourier = NewCourier.getRandomCourierAllArgs();

        courierClient.create(newCourier);

        Response response = courierClient.create(newCourier);
        response.then().log().all()
                .statusCode(409)
                .and()
                .assertThat().body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Error When Courier Create Without Login")
    @Description("The test checks the request without a login: 400 Bad Request")
    public void errorWhenCourierCreateWithoutLoginTest() {
        newCourier = NewCourier.getRandomCourierWithoutLogin();

        Response response = courierClient.create(newCourier);
        response.then().log().all()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Error When Courier Create Without Password")
    @Description("The test checks the request without a password: 400 Bad Request")
    public void errorWhenCourierCreateWithoutPasswordTest() {
        newCourier = NewCourier.getRandomCourierWithoutPassword();

        Response response = courierClient.create(newCourier);
        response.then().log().all()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Error When Courier Create Without FirstName")
    @Description("The test checks the request without a firstName: 400 Bad Request")
    public void errorWhenCourierCreateWithoutFirstNameTest() {
        newCourier = NewCourier.getRandomCourierWithoutFirstName();

        Response response = courierClient.create(newCourier);
        response.then().log().all()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    public void deleteCourier() {
        CourierAuthorization authorizationData = CourierAuthorization.getAuthorizationData(newCourier);
        int courierId = courierClient.getCourierId(authorizationData)
                .then().log().all()
                .extract()
                .path("id");

        courierClient.delete(Integer.toString(courierId));
    }
}
