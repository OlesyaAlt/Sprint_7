import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


public class OrderListTest extends BaseTest{
    //в тело ответа возвращается список заказов
    @Test
    @DisplayName("getting the order list - /api/v1/orders")
    @Description("check that the list of orders is returned to the response body")
    public void orderListTest(){
        given()
                .get("/api/v1/orders")
                .then()
                .statusCode(200)
                .body("orders.id", notNullValue());
    }
}
