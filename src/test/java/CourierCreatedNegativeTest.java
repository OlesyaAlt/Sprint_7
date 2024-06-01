import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import ru.yandex.praktikum.models.Courier;

public class CourierCreatedNegativeTest extends BaseTest {
    private Courier courier;
    //в теле запроса отсутствуте поле Имя
    @Test
    @DisplayName("the Name field is missing in the request body - /api/v1/courier")
    @Description("you cannot create a courier if the required Name field is missing")
    public void courierCreatedWithLoginAndPasswordFields() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(8));

        courierSteps
                .createCourier(courier)
                .statusCode(400);
    }
    //в теле запроса отсутствуте поле Пароль
    @Test
    @DisplayName("the Password field is missing in the request body - /api/v1/courier")
    @Description("you cannot create a courier if the required Password field is missing")
    public void courierCreatedWithLoginAndFirstnameFields() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(12));

        courierSteps
                .createCourier(courier)
                .statusCode(400);
    }
    //в теле запроса отсутствуте поле Логин
    @Test //в теле запроса отсутствуте поле Логин
    @DisplayName("the Login field is missing in the request body - /api/v1/courier")
    @Description("you cannot create a courier if the required Login field is missing")
    public void courierCreatedWithPasswordAndFirstnameFields() {
        courier = new Courier();
        courier.setPassword(RandomStringUtils.randomAlphabetic(8));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(12));

        courierSteps
                .createCourier(courier)
                .statusCode(400);
    }
}
