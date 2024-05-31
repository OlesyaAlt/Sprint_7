import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.Courier;

@RunWith(Parameterized.class)
public class CourierLoginParamTest extends BaseTest{
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
                {RandomStringUtils.randomAlphabetic(10), null},
        };
    }
    @Test
    public void needToPassAllRequiredFields() {
        Courier courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(8));
        courierSteps
                .createCourier(courier);

        Courier courierBad = new Courier();
        courierBad.setLogin(login);
        courierBad.setPassword(password);
        courierSteps
                .login(courierBad)
                .statusCode(400);
    }
    @After
    public void tearDown(){
        Integer id = courierSteps.login(courier)
                .extract().body().path("id");
        courier.setId(id);
        courierSteps.delete(courier);
    }
}
