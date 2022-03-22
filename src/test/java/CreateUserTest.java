import support.UserClient;
import model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateUserTest extends BaseTest {
    private static UserClient userClient;

    @BeforeClass
    public static void SetUp() {
        userClient = new UserClient();
    }

    @Test
    public void userCanBeCreatedWithValidData() {
        User user = User.getRandom();

        boolean isUserCreated = userClient.createSuccess(user);

        assertTrue("User not created", isUserCreated);
    }

    @Test
    public void userCannotBeCreatedWithRetryData() {
        User user = User.getRandom();

        userClient.createSuccess(user);
        String actualErrorMessage = userClient.createFailed(user);
        String expectedErrorMessage = "User already exists";

        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void userCannotBeCreatedWithoutEmail() {
        User user = User.getRandomWithoutEmail();

        String actualErrorMessage = userClient.createFailed(user);
        String expectedErrorMessage = "Email, password and name are required fields";

        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void userCannotBeCreatedWithoutPassword() {
        User user = User.getRandomWithoutPassword();

        String actualErrorMessage = userClient.createFailed(user);
        String expectedErrorMessage = "Email, password and name are required fields";

        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void userCannotBeCreatedWithoutName() {
        User user = User.getRandomWithoutName();

        String actualErrorMessage = userClient.createFailed(user);
        String expectedErrorMessage = "Email, password and name are required fields";

        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

}
