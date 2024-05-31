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
    public void needToPassAllRequiredFields() {
        Courier courier = new Courier();
        courier.setLogin(login);
        courier.setPassword(password);

        courierSteps
                .login(courier)
                .statusCode(400);
    }
}
// второй тест падает по таймауту
