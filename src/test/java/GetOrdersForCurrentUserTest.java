import support.OrderClient;
import support.UserClient;
import io.restassured.response.Response;
import model.Order;
import model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetOrdersForCurrentUserTest extends BaseTest {
    private static String accessToken;
    private static OrderClient orderClient;


    @BeforeClass
    public static void SetUp() {
        UserClient userClient = new UserClient();
        User user = User.getRandom();
        accessToken = userClient.createSuccessAndGetAccessToken(user);
        orderClient = new OrderClient();
    }


    @Test
    public void getOrdersOfCurrentUserWithAuthorization() {
       Order order = Order.getIngredients();

        orderClient.createOrderWithAuthorization(order, accessToken);
        Response responseOrdersOfCurrentUser = orderClient.getOrdersForCurrentUserWithAuthorization(accessToken);

        int actualStatusCode = responseOrdersOfCurrentUser.getStatusCode();
        boolean actualResult = responseOrdersOfCurrentUser.path("success");
        int expectedStatusCode = HttpURLConnection.HTTP_OK;

        assertEquals(expectedStatusCode, actualStatusCode);
        assertTrue("", actualResult);

    }

    @Test
    public void createOrderFailWithoutIngredients() {
        String actualMessageError = orderClient.getOrdersForCurrentUserWithoutAuthorization();
        String expectedMessageError = "You should be authorised";

        assertEquals(expectedMessageError, actualMessageError);
    }

}
