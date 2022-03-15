
import support.UserClient;
import model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpdateUserTest extends BaseTest {
    private static UserClient userClient;
    private static User user;
    private static String accessToken;


    @BeforeClass
    public static void SetUp () {
        userClient  = new UserClient();
        user = User.getRandom();
        accessToken = userClient.createSuccessAndGetAccessToken(user);
    }


    @Test
    public void userUpdateSuccessWithAuthAndWithoutPasswordField() {
        user = User.getRandomWithoutPassword();

        boolean isUpdated = userClient.updateWithAuthorization(user, accessToken);

        assertTrue(isUpdated);

    }

    @Test
    public void userUpdateSuccessWithAuthAndWithoutNameField() {
        user = User.getRandomWithoutName();

        boolean isUpdated = userClient.updateWithAuthorization(user, accessToken);

        assertTrue(isUpdated);

    }

    @Test
    public void userUpdateSuccessWithAuthAndWithoutEmailField() {
        user = User.getRandomWithoutEmail();

        boolean isUpdated = userClient.updateWithAuthorization(user, accessToken);

        assertTrue(isUpdated);

    }

    @Test
    public void userUpdateFailedWithoutAuthAndWithoutPasswordField() {
        user = User.getRandomWithoutPassword();

        String actualErrorMessage = userClient.updateWithoutAuthorization(user);
        String expectedErrorMessage = "You should be authorised";

        assertEquals(expectedErrorMessage, actualErrorMessage);

    }

    @Test
    public void userUpdateFailedWithoutAuthAndWithoutNameField() {
        user = User.getRandomWithoutName();

        String actualErrorMessage = userClient.updateWithoutAuthorization(user);
        String expectedErrorMessage = "You should be authorised";

        assertEquals(expectedErrorMessage, actualErrorMessage);

    }

    @Test
    public void userUpdateFailedWithoutAuthAndWithoutEmailField() {
        user = User.getRandomWithoutEmail();

        String actualErrorMessage = userClient.updateWithoutAuthorization(user);
        String expectedErrorMessage = "You should be authorised";

        assertEquals(expectedErrorMessage, actualErrorMessage);

    }

}
