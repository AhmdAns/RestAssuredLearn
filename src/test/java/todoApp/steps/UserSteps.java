package todoApp.steps;

import todoApp.apis.UserAPI;
import com.github.javafaker.Faker;
import todoApp.pojoClasses.UserModeling;
import io.restassured.response.Response;

public class UserSteps {
    public static UserModeling generateUser() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fakedEmail = faker.internet().emailAddress();
        String password = "anasZh2aan";
        return new UserModeling(fakedEmail, password, firstName, lastName);
    }

    public static UserModeling getRegisteredUser() {
        UserModeling user = generateUser();
        UserAPI.register(user);
        return user;
    }

    public static String returnUserToken() {
        UserModeling  user = UserSteps.generateUser();
        Response response = UserAPI.register(user);
        return response.body().path("access_token");
    }
}
