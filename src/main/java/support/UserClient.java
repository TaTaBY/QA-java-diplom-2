package support;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.User;
import model.UserCredentials;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;


public class UserClient {
    @Step ("Success create user {user}")
    public boolean createSuccess (User user) {
        return given()
                .body(user)
                .when()
                .post(EndPoints.CREATE_USER)
                .then()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
    }

    @Step ("Success create user {user}")
    public String createSuccessAndGetAccessToken (User user) {
        return given()
                .body(user)
                .when()
                .post(EndPoints.CREATE_USER)
                .then()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("accessToken");
    }

    @Step ("Failed create user {user}")
    public String createFailed (User user) {
        return given()
                .body(user)
                .when()
                .post(EndPoints.CREATE_USER)
                .then()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .extract()
                .path("message");
    }

    @Step ("Success login as {userCredentials}")
    public boolean loginSuccess (UserCredentials userCredentials) {
        return given()
                .body(userCredentials)
                .when()
                .post(EndPoints.USER_LOGIN)
                .then()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
    }

    @Step ("Fail login as non-exist user - {userCredentials}")
    public Response loginFailed (UserCredentials userCredentials) {
        return  given()
                .body(userCredentials)
                .when()
                .post(EndPoints.USER_LOGIN);
    }

    @Step ("Success update {user} with authorization")
    public boolean updateWithAuthorization(User user, String accessToken) {
        return given()
                .headers("authorization", accessToken)
                .body(user)
                .when()
                .patch(EndPoints.GET_UPDATE_USER)
                .then()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
    }

    @Step ("Failed update {user} without authorization")
    public String updateWithoutAuthorization(User user) {
        return given()
                .body(user)
                .when()
                .patch(EndPoints.GET_UPDATE_USER)
                .then()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .extract()
                .path("message");
    }

}
