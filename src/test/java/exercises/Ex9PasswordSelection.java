package exercises;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

public class Ex9PasswordSelection {

    @ParameterizedTest
    @ValueSource(strings = {
            "123456",
            "123456789",
            "qwerty",
            "password",
            "1234567",
            "12345678",
            "12345",
            "iloveyou",
            "111111",
            "123123",
            "abc123",
            "qwerty123",
            "1q2w3e4r",
            "admin",
            "qwertyuiop",
            "654321",
            "555555",
            "lovely",
            "7777777",
            "welcome",
            "888888",
            "princess",
            "dragon",
            "password1",
            "123qwe"
    })
    public void ex9PasswordSelection (String password) {
        Map<String, String> data = new HashMap<>();
        data.put("login", "super_admin");
        data.put("password", password);

        Response response = RestAssured
                .given()
                .body(data)
                .get("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                .andReturn();

        String authCookie = response.getCookie("auth_cookie");
//        System.out.println(authCookie);

        Response responseWithCookie = RestAssured
                .given()
                .cookie(authCookie)
                .get("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                .andReturn();

        String answer = responseWithCookie.asString();
        System.out.println(answer);
    }
}
