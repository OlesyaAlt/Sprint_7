
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.models.Order;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreatedTest extends BaseTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;

    public OrderCreatedTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
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
        return new Object[][]{
                {"Иван", "Иванов", "г. Москва", "Арбатская", "12345678912", 2, "2024-06-25", "в 8 утра", new String[]{"BLACK"}},
                {"Алена", "Кикабидзе", "г. Москва, Набережная", "Курская", "111111111111", 1, "2024-06-23", "в 9 утра", null},
                {"Гулена", "Хихибидзе", "г. Москва, Вокзал", "Курская", "111111111112", 3, "2024-06-22", "бегом", new String[]{"GREY"}},
                {"лена", "хибидзе", "Москва, Вокзал", "Курская", "111111111122", 3, "2024-06-21", "вечером", new String[]{"BLACK", "GREY"}}

        };
    }

    //можно указать один из цветов — BLACK или GREY
    //можно указать оба цвета
    //можно совсем не указывать цвет
    //тело ответа содержит track
    @Test
    @DisplayName("You can create an order with or without specifying the color of the scooter - /api/v1/orders")
    @Description("check that the color Gray or Black can be specified in the order, you can omit the color, you can specify two colors")
    public void orderCreatedTest() {
        Order order = new Order();
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
