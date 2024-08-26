package exercises;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.ApiCoreRequests;
import lib.Assertions;
import lib.BaseTestCase;
import lib.DataGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

public class Ex15UserRegisterTest extends BaseTestCase {
    private final ApiCoreRequests apiCoreRequests = new ApiCoreRequests();

    @Test
    public void testCreateUserWithExistingEmail() {
        String email = "vinkotov@example.com";

        Map<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData = DataGenerator.getRegistrationData(userData);

        Response responseCreateAuth = RestAssured
                .given()
                .body(userData)
                .post("https://playground.learnqa.ru/api/user/")
                .andReturn();

        Assertions.assertResponseCodeEquals(responseCreateAuth, 400);
        Assertions.assertResponseTextEquals(responseCreateAuth, "Users with email '" + email + "' already exists");
    }

    @Test
    public void testCreateUserSuccessfully() {
        String email = DataGenerator.getRandomEmail();

        Map<String, String> userData = DataGenerator.getRegistrationData();

        Response responseCreateAuth = RestAssured
                .given()
                .body(userData)
                .post("https://playground.learnqa.ru/api/user/")
                .andReturn();

        Assertions.assertResponseCodeEquals(responseCreateAuth, 200);
        Assertions.assertJsonHasField(responseCreateAuth, "id");
    }

    @Test
    public void testCreateUserWithInvalidEmail() {
        String email = "invalidmail.com";


        Map<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData = DataGenerator.getRegistrationData(userData);

        Response responseGetAuth = apiCoreRequests
                .makePostRequest("https://playground.learnqa.ru/api/user/", userData);
        Assertions.assertJsonHasNotField(responseGetAuth, "id");
    }

    @ParameterizedTest
    @ValueSource(strings = {"email", "password", "username", "firstName", "lastName"})
    public void testCreateUserWithoutField(String field) {
        Map<String, String> userData = new HashMap<>();
        if (field.equals("email")) {
            userData.put("email", "");
            userData.put("password", "123");
            userData.put("username", "learnqa");
            userData.put("firstName", "learnqa");
            userData.put("lastName", "learnqa");
            Response responseGetAuth = apiCoreRequests
                    .makePostRequest("https://playground.learnqa.ru/api/user/", userData);
            Assertions.assertJsonHasNotField(responseGetAuth, "id");
        } else if (field.equals("password")) {
            userData.put("email", DataGenerator.getRandomEmail());
            userData.put("password", "");
            userData.put("username", "learnqa");
            userData.put("firstName", "learnqa");
            userData.put("lastName", "learnqa");
            Response responseGetAuth = apiCoreRequests
                    .makePostRequest("https://playground.learnqa.ru/api/user/", userData);
            Assertions.assertJsonHasNotField(responseGetAuth, "id");
        } else if (field.equals("username")) {
            userData.put("email", DataGenerator.getRandomEmail());
            userData.put("password", "123");
            userData.put("username", "");
            userData.put("firstName", "learnqa");
            userData.put("lastName", "learnqa");
            Response responseGetAuth = apiCoreRequests
                    .makePostRequest("https://playground.learnqa.ru/api/user/", userData);
            Assertions.assertJsonHasNotField(responseGetAuth, "id");
        } else if (field.equals("firstName")) {
            userData.put("email", DataGenerator.getRandomEmail());
            userData.put("password", "123");
            userData.put("username", "learnqa");
            userData.put("firstName", "");
            userData.put("lastName", "learnqa");
            Response responseGetAuth = apiCoreRequests
                    .makePostRequest("https://playground.learnqa.ru/api/user/", userData);
            Assertions.assertJsonHasNotField(responseGetAuth, "id");
        } else if (field.equals("lastName")) {
            userData.put("email", DataGenerator.getRandomEmail());
            userData.put("password", "123");
            userData.put("username", "learnqa");
            userData.put("firstName", "learnqa");
            userData.put("lastName", "");
            Response responseGetAuth = apiCoreRequests
                    .makePostRequest("https://playground.learnqa.ru/api/user/", userData);
            Assertions.assertJsonHasNotField(responseGetAuth, "id");
        }
    }

    @Test
    public void testCreateUserWithShortName() {
        String firstName = String.valueOf(DataGenerator.getRandomStringLength(1));

        Map<String, String> userData = new HashMap<>();
        userData.put("firstName", firstName);
        userData = DataGenerator.getRegistrationData(userData);

        Response responseGetAuth = apiCoreRequests
                .makePostRequest("https://playground.learnqa.ru/api/user/", userData);
        Assertions.assertJsonHasNotField(responseGetAuth, "id");
    }

    @Test
    public void testCreateUserWithLongName() {
        String firstName = String.valueOf(DataGenerator.getRandomStringLength(251));

        Map<String, String> userData = new HashMap<>();
        userData.put("firstName", firstName);
        userData = DataGenerator.getRegistrationData(userData);

        Response responseGetAuth = apiCoreRequests
                .makePostRequest("https://playground.learnqa.ru/api/user/", userData);
        Assertions.assertJsonHasNotField(responseGetAuth, "id");
    }
}
