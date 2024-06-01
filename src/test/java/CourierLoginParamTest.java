import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.models.Courier;


@RunWith(Parameterized.class)
public class CourierLoginParamTest extends BaseTest {
    private final String login;
    private final String password;
    private Courier courier;

    public CourierLoginParamTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[] data() {
        return new Object[][]{
                {null, RandomStringUtils.randomAlphabetic(8)},
                {RandomStringUtils.randomAlphabetic(10), null}
        };
    }
    // для авторизации нужно передать все обязательные поля
    // если какого-то поля нет, запрос возвращает ошибку
    @Test
    @DisplayName("you cannot log in if the required fields are not filled in - /api/v1/courier/login")
    @Description("if the required field is empty, the error code is 400")
    public void needToPassAllRequiredFields() {
        Courier courier = new Courier();
        courier.setLogin(login);
        courier.setPassword(password);

        courierSteps
                .login(courier)
                .statusCode(400);
    }
}

