import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

public class BaseTest {

    @BeforeClass
    static public void setUpBase () {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://stellarburgers.nomoreparties.site/api/")
                .setContentType(ContentType.JSON)
                .build();

        RestAssured.requestSpecification = requestSpec;
    }
}
