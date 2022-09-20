package api.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewOrder {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public static NewOrder getRandomOrderAllArgsAndScooterColor(List<String> color) {
        return new NewOrder(
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomNumeric(1),
                RandomStringUtils.randomNumeric(11),
                2,
                "2022-09-06",
                RandomStringUtils.randomAlphabetic(5),
                color
        );
    }
}
