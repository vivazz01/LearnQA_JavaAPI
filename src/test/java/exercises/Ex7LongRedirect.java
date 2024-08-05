package exercises;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Ex7LongRedirect {

    @Test
    public void ex7LongRedirect() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(true)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            ex7LongRedirect();
        } else {
            System.out.println(statusCode);
        }
    }

}
