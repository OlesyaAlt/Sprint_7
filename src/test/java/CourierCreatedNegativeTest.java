import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import ru.yandex.praktikum.models.Courier;

public class CourierCreatedNegativeTest extends BaseTest {
    private Courier courier;

    @Test //в теле запроса отсутствуте поле Имя
    public void courierCreatedWithLoginAndPasswordFields() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(8));

        courierSteps
                .createCourier(courier)
                .statusCode(400);
    }
    @Test //в теле запроса отсутствуте поле Пароль
    public void courierCreatedWithLoginAndFirstnameFields() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(12));

        courierSteps
                .createCourier(courier)
                .statusCode(400);
    }
    @Test //в теле запроса отсутствуте поле Логин
    public void courierCreatedWithPasswordAndFirstnameFields() {
        courier = new Courier();
        courier.setPassword(RandomStringUtils.randomAlphabetic(8));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(12));

        courierSteps
                .createCourier(courier)
                .statusCode(400);
    }
}
