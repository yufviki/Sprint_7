package ru.praktikum_services.qa_scooter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourierAuthorization {
    private String login;
    private String password;

    public static CourierAuthorization getAuthorizationData(NewCourier newCourier) {
        return new CourierAuthorization(
                newCourier.getLogin(),
                newCourier.getPassword()
        );
    }

    public static CourierAuthorization getAuthorizationDataWithoutLogin(NewCourier newCourier) {
        return new CourierAuthorization(
                null,
                newCourier.getPassword()
        );
    }

    public static CourierAuthorization getAuthorizationDataWithoutPassword(NewCourier newCourier) {
        return new CourierAuthorization(
                newCourier.getLogin(),
                null
        );
    }

    public static CourierAuthorization getAuthorizationDataInvalidLogin(NewCourier newCourier) {
        return new CourierAuthorization(
                RandomStringUtils.randomAlphabetic(5),
                newCourier.getPassword()
        );
    }

    public static CourierAuthorization getAuthorizationDataInvalidPassword(NewCourier newCourier) {
        return new CourierAuthorization(
                newCourier.getLogin(),
                RandomStringUtils.randomAlphabetic(5)
        );
    }
}
