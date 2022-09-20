package ru.praktikumservices.qascooter;

import api.client.CourierClient;
import api.util.CourierAuthorization;
import api.util.NewCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class CourierLoginTests extends TestBase{
    NewCourier newCourier;
    CourierClient courierClient;

    @Before
    public void setUpCourierClient() {
        courierClient = new CourierClient();
        newCourier = NewCourier.getRandomCourierAllArgs();
        courierClient.create(newCourier);
    }

    @Test
    @DisplayName("Success Courier Login")
    @Description("The test verifies the successful authorization of the courier: 200")
    public void successCourierLoginTest() {
        CourierAuthorization authorizationData = CourierAuthorization.getAuthorizationData(newCourier);

        Response response = courierClient.getCourierId(authorizationData);
        response.then().log().all()
                .statusCode(200)
                .and()
                .assertThat().body("id", notNullValue());

        deleteCourier();
    }

    @Test
    @DisplayName("Error Courier Login Without Login")
    @Description("The test checks the authorization request without a login: 400 Bad Request")
    public void errorCourierLoginWithoutLoginTest() {
        CourierAuthorization authorizationData = CourierAuthorization.getAuthorizationDataWithoutLogin(newCourier);

        Response response = courierClient.getCourierId(authorizationData);
        response.then().log().all()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Error Courier Login Without Password")
    @Description("The test checks the authorization request without a password: 400 Bad Request")
    public void errorCourierLoginWithoutPasswordTest() {
        CourierAuthorization authorizationData = CourierAuthorization.getAuthorizationDataWithoutPassword(newCourier);

        Response response = courierClient.getCourierId(authorizationData);
        response.then().log().all()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Error Courier Login Invalid Login")
    @Description("The test checks the authorization request with invalid login: 404 Not Found")
    public void errorCourierLoginInvalidLoginTest() {
        CourierAuthorization authorizationData = CourierAuthorization.getAuthorizationDataInvalidLogin(newCourier);

        Response response = courierClient.getCourierId(authorizationData);
        response.then().log().all()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Error Courier Login Invalid Password")
    @Description("The test checks the authorization request with invalid password: 404 Not Found")
    public void errorCourierLoginInvalidPasswordTest() {
        CourierAuthorization authorizationData = CourierAuthorization.getAuthorizationDataInvalidPassword(newCourier);

        Response response = courierClient.getCourierId(authorizationData);
        response.then().log().all()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));;
    }

    public void deleteCourier() {
        CourierAuthorization authorizationData = CourierAuthorization.getAuthorizationData(newCourier);
        int courierId = courierClient.getCourierId(authorizationData)
                .then().log().all()
                .extract().path("id");

        courierClient.delete(Integer.toString(courierId));
    }
}
