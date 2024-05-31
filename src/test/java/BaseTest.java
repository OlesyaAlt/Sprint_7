import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.After;
import org.junit.Before;
import ru.yandex.praktikum.Courier;
import ru.yandex.praktikum.steps.CourierSteps;


public abstract class BaseTest {
    public static final String BASE_URL="https://qa-scooter.praktikum-services.ru";
    public final CourierSteps courierSteps = new CourierSteps();

    @Before

    public void setup(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = BASE_URL;
    }

}
