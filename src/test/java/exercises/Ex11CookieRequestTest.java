package exercises;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Ex11CookieRequestTest {

    @Test
    public void ex11CookieRequestTest() {
        Response responseCheckCookie = RestAssured
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        Map<String, String> cookies = responseCheckCookie.getCookies();
        assertFalse(cookies.isEmpty(), "Cookies is empty");
        System.out.println(cookies);
    }
}
