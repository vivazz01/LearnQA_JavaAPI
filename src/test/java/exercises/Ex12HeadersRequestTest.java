package exercises;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class Ex12HeadersRequestTest {

    @Test
    public void ex12HeadersRequestTest() {
        Response responseCheckHeaders = RestAssured
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        Headers headers = responseCheckHeaders.getHeaders();
        assertFalse(headers.toString().isEmpty(), "Headers is empty");
        System.out.println(headers);
    }
}
