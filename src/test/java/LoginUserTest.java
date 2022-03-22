import support.UserClient;
import io.restassured.response.Response;
import model.User;
import model.UserCredentials;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.HttpURLConnection;

import static org.junit.Assert.*;

public class LoginUserTest extends BaseTest {
    private static UserClient userClient;
    private User user;

    @BeforeClass
    public static void SetUp() {
        userClient = new UserClient();
    }


    @Test
    public void userLoginSuccessWithValidData() {
        user = User.getRandom();
        userClient.createSuccess(user);
        boolean isLogged = userClient.loginSuccess(UserCredentials.from(user));
        assertTrue(isLogged);
    }

    @Test
    public void userLoginFailWithInvalidData() {
        user = User.getRandom();
        Response responseLogged = userClient.loginFailed(UserCredentials.from(user));
        int actualStatusCode = responseLogged.statusCode();
        int expectedStatusCode = HttpURLConnection.HTTP_UNAUTHORIZED;
        boolean isLogged = responseLogged.path("success");
        String actualErrorMessage = responseLogged.path("message");
        String expectedErrorMessage = "email or password are incorrect";
        assertEquals(expectedStatusCode, actualStatusCode);
        assertFalse(isLogged);
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

}
