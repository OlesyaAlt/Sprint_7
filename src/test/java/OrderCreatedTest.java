import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.models.Order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreatedTest extends BaseTest{
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private String color;

    public OrderCreatedTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getFormOrder() {
        return new Object[][] {
                { "Иван", "Иванов", "г. Москва", "Арбатская", "12345678912", 2, "25.06.2024", "в 8 утра", "BLACK"},
                { "Алена", "Кикабидзе", "г. Москва, Набережная", "Курская", "111111111111", 1, "30.06.2024", "в 9 утра", null},
                { "Гулена", "Хихибидзе", "г. Москва, Вокзал", "Курская", "111111111112", 3, "29.06.2024", "бегом", "GREY"}

        };
    }
    @Test
    public void orderCreatedTest(){
        Order order= new Order();
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setMetroStation(metroStation);
        order.setPhone(phone);
        order.setRentTime(rentTime);
        order.setDeliveryDate(deliveryDate);
        order.setComment(comment);
        order.setColor(color);

        given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then()
                .assertThat().statusCode(201)
                .body("track", notNullValue());
    }
}
