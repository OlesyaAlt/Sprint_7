import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.models.Courier;


@RunWith(Parameterized.class)
public class CourierCreatedParamTest extends BaseTest {
    private final String login;
    private final String password;
    private final String firstName;

    public CourierCreatedParamTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters
    public static Object[] data() {
        return new Object[][]{
                {null, RandomStringUtils.randomAlphabetic(8), RandomStringUtils.randomAlphabetic(12)},
                {RandomStringUtils.randomAlphabetic(10), null, RandomStringUtils.randomAlphabetic(12)},
                {RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(8), null}
        };
    }
    //чтобы создать курьера, нужно передать в ручку все обязательные поля
    @Test
    @DisplayName("you cannot create a courier if the required field is empty - /api/v1/courier")
    @Description("checking the required field for completion")
    public void needToPassAllRequiredFields() {
        Courier courier = new Courier();
        courier.setLogin(login);
        courier.setPassword(password);
        courier.setFirstName(firstName);

        courierSteps
                .createCourier(courier)
                .statusCode(400);
        }
    }





