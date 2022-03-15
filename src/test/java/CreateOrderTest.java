import support.OrderClient;
import support.UserClient;
import model.Order;
import model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.HttpURLConnection;

import static org.junit.Assert.*;

public class CreateOrderTest extends BaseTest {
    private static UserClient userClient;
    private static User user;
    private static String accessToken;
    private static Order order;
    private static OrderClient orderClient;


    @BeforeClass
    public static void SetUp () {
        userClient  = new UserClient();
        user = User.getRandom();
        accessToken = userClient.createSuccessAndGetAccessToken(user);
        orderClient = new OrderClient();
    }

    @Test
    public void createOrderSuccessWithAuthorization() {
        order = Order.getIngredients();

        boolean isCreatedOrder = orderClient.createOrderWithAuthorization(order, accessToken);

        assertTrue(isCreatedOrder);

    }

    @Test
    public void createOrderSuccessWithoutAuthorization() {
        order = Order.getIngredients();

        boolean isCreatedOrder = orderClient.createOrderWithoutAuthorization(order);

        assertTrue(isCreatedOrder);

    }

    @Test
    public void createOrderFailWithoutIngredients() {
        order = Order.getEmptyIngredients();

        String actualMessageError = orderClient.createOrderWithoutIngredients(order);
        String expectedMessageError = "Ingredient ids must be provided";

        assertEquals(expectedMessageError, actualMessageError);
    }

    @Test
    public void createOrderFailWithIncorrectHashOfIngredients() {
        order = Order.getIncorrectIngredients();

        int actualStatusCode = orderClient.createOrderWithoutCorrectHashOfIngredients(order);
        int expectedStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR;

        assertEquals(expectedStatusCode, actualStatusCode);
    }
}
