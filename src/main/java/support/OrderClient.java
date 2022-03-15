package support;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Order;
import org.apache.commons.lang3.RandomUtils;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;


public class OrderClient {
    @Step ("Success create order with authorization")
    public boolean createOrderWithAuthorization(Order order, String accessToken) {
        return given()
                .headers("authorization", accessToken)
                .body(order)
                .when()
                .post(EndPoints.CREATE_ORDER)
                .then()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
    }

    @Step ("Success create order without authorization")
    public boolean createOrderWithoutAuthorization(Order order) {
        return given()
                .body(order)
                .when()
                .post(EndPoints.CREATE_ORDER)
                .then()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
    }

    @Step ("Failed create order without ingredients")
    public String createOrderWithoutIngredients(Order order) {
        return given()
                .body(order)
                .when()
                .post(EndPoints.CREATE_ORDER)
                .then()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");
    }

    @Step ("Failed create order without correct hash of ingredients")
    public int createOrderWithoutCorrectHashOfIngredients(Order order) {
        return given()
                .body(order)
                .when()
                .post(EndPoints.CREATE_ORDER)
                .then()
                .extract()
                .statusCode();
    }

    @Step ("Get orders for current user  with authorization")
    public Response getOrdersForCurrentUserWithAuthorization(String accessToken) {
        return given()
                .headers("authorization", accessToken)
                .when()
                .get(EndPoints.GET_ORDERS_FOR_CURRENT_USER);
    }

    @Step ("Get orders for current user  without authorization")
    public String getOrdersForCurrentUserWithoutAuthorization() {
        return given()
                .when()
                .get(EndPoints.GET_ORDERS_FOR_CURRENT_USER)
                .then()
                .extract()
                .path("message");
    }

    public String getRandomHashOfIngredients() {
        int random = RandomUtils.nextInt(0,14);
        return given()
                .when()
                .get(EndPoints.INGREDIENTS_GET)
                .then()
                .extract()
                .path("data[" + random + "]._id");
    }
}
