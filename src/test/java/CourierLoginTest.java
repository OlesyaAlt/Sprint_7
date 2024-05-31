import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.Courier;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginTest extends BaseTest{
    private Courier courier;

    @Before
    public void setupCourier(){
        courier= new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(8));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(12));

        courierSteps
                .createCourier(courier);
    }
    // курьер может авторизоваться
    // успешный запрос возвращает id
    @Test
    public void successfulAuthorization(){
        courierSteps
                .login(courier)
                .statusCode(200)
                .body("id", notNullValue());
    }

    @After
    public void tearDown(){
        Integer id = courierSteps.login(courier)
                .extract().body().path("id");
        courier.setId(id);
        courierSteps.delete(courier);
    }

}
