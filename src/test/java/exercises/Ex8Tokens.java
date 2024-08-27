package exercises;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.ApiCoreRequests;
import org.junit.jupiter.api.Test;

public class Ex8Tokens {
    private final ApiCoreRequests apiCoreRequests = new ApiCoreRequests();

    @Test
    public void ex8Tokens() throws InterruptedException {
        Response responseWithoutToken = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .andReturn();

        System.out.println(responseWithoutToken.asString());

        Thread.sleep(16000);

        Response responseWithToken = RestAssured
                .given()
                .queryParam("token", responseWithoutToken.getHeader("token"))
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .andReturn();


        System.out.println(responseWithToken.asString());
    }

}
