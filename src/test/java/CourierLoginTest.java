import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.models.Courier;
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
    @DisplayName("successful authorization of the courier - /api/v1/courier/login")
    @Description("a successful request returns the id")
    public void successfulAuthorization(){
        courierSteps
                .login(courier)
                .statusCode(200)
                .body("id", notNullValue());
    }
    // система вернёт ошибку, если неправильно указать логин или пароль
    // если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
    @Test
    @DisplayName("you cannot log in if you enter an incorrect username or password - /api/v1/courier/login")
    @Description("verification of authorization under a non-existent user - error code 404")
    public void unsuccessfulAuthorization(){
        Courier courierBad = new Courier();
        courierBad.setLogin(RandomStringUtils.randomAlphabetic(10));
        courierBad.setPassword(RandomStringUtils.randomAlphabetic(8));
        courierSteps
                .login(courierBad)
                .statusCode(404);
    }

    @After
    public void tearDown(){
        Integer id = courierSteps.login(courier)
                .extract().body().path("id");
        courier.setId(id);
        courierSteps.delete(courier);
    }

}
