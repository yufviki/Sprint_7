package ru.praktikum_services.qa_scooter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCourier {
    private String login;
    private String password;
    private String firstName;

    public static NewCourier getRandomCourierAllArgs() {
        return new NewCourier(
                RandomStringUtils.randomAlphabetic(6),
                "1234",
                RandomStringUtils.randomAlphabetic(6)
        );
    }

    public static NewCourier getRandomCourierWithoutLogin() {
        return new NewCourier(
                null,
                "1234",
                RandomStringUtils.randomAlphabetic(6)
        );
    }

    public static NewCourier getRandomCourierWithoutPassword() {
        return new NewCourier(
                RandomStringUtils.randomAlphabetic(6),
                null,
                RandomStringUtils.randomAlphabetic(6)
        );
    }

    public static NewCourier getRandomCourierWithoutFirstName() {
        return new NewCourier(
                RandomStringUtils.randomAlphabetic(6),
                "1234",
                null
        );
    }
}
