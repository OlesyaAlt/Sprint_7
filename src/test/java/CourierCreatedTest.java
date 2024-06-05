import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.models.Courier;
import static org.hamcrest.Matchers.is;

public class CourierCreatedTest extends BaseTest{
    private Courier courier;

    @Before
    public void setupCourier(){
        courier= new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(8));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(12));
    }

    // курьера можно создать
    // запрос возвращает правильный код ответа
    // успешный запрос возвращает ok: true;
    @Test
    @DisplayName("creating a courier - /api/v1/courier")
    @Description("checking the creation of the courier, the response code and the response body")
    public void courierCreatedTest() {
        courierSteps
                .createCourier(courier)
                .assertThat().statusCode(201)
                .body("ok", is(true));

    }
    // нельзя создать двух одинаковых курьеров
    // если создать пользователя с логином, который уже есть, возвращается ошибка
    @Test
    @DisplayName("you cannot create two identical couriers - /api/v1/courier")
    @Description("check that it is impossible to create two identical couriers and error code 409")
    public void dontCreateTwoIdenticalCouriers() {
        courierSteps
                .createCourier(courier);

        courierSteps
                .createCourier(courier)
                .statusCode(409);
    }

    @After
    public void tearDown(){
        Integer id = courierSteps.login(courier)
                .extract().body().path("id");
        courier.setId(id);
        courierSteps.delete(courier);
    }

}
