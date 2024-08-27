package exercises;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lib.ApiCoreRequests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ex8Tokens {
//    private final ApiCoreRequests apiCoreRequests = new ApiCoreRequests();

    @Test
    public void ex8Tokens() throws InterruptedException {
        JsonPath responseWithoutToken = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String token = responseWithoutToken.get("token");
        int seconds = responseWithoutToken.get("seconds");

        JsonPath responseWithTokenBeforeJobIsDone = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String status = responseWithTokenBeforeJobIsDone.get("status");
        System.out.println(status);

        assertEquals("Job is NOT ready", status);

        Thread.sleep(seconds * 1000);

        JsonPath responseWithTokenAfterJobIsDone = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        status = responseWithTokenAfterJobIsDone.get("status");
        System.out.println(status);

        assertEquals("Job is ready", status);
    }
}
