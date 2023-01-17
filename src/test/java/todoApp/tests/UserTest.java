package todoApp.tests;

import todoApp.data.ErrorMessages;
import todoApp.apis.UserAPI;
import todoApp.pojoClasses.ErrorsModeling;
import todoApp.pojoClasses.UserModeling;
import todoApp.steps.UserSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTest {
    @Test(description = "user should be able to register")
    public void validRegister() {
        UserModeling user = UserSteps.generateUser();
        Response response =UserAPI.register(user);
        UserModeling getResponseData = response.body().as(UserModeling.class);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(getResponseData.getFirstName(), equalTo(user.getFirstName()));
    }

    @Test(description = "user should not be able to register")
    public void notValidRegister() {
        UserModeling user = UserSteps.getRegisteredUser();
        Response response = UserAPI.register(user);
        ErrorsModeling returnedError=response.body().as(ErrorsModeling.class);
        assertThat(response.statusCode(), equalTo(400));
        assertThat(returnedError.getMessage(), equalTo(ErrorMessages.ALREADY_REGISTERED));
    }

    @Test(description = "user should be able to login")
    public void validLogin() {
        UserModeling user = UserSteps.getRegisteredUser();
        UserModeling loginCredentials = new UserModeling(user.getEmail(),user.getPassword());
        Response response = UserAPI.login(loginCredentials);
        UserModeling returnedData=response.body().as(UserModeling.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedData.getAccessToken(), not(equalTo(null)));
        assertThat(returnedData.getFirstName(),equalTo(user.getFirstName()));
    }

    @Test(description = "user should not be able to login")
    public void inValidLogin() {
        UserModeling user = UserSteps.getRegisteredUser();
        UserModeling loginCredentials = new UserModeling(user.getEmail(),user.getPassword()+1);
        Response response = UserAPI.login(loginCredentials);
        ErrorsModeling returnedError=response.body().as(ErrorsModeling.class);
        assertThat(response.statusCode(), equalTo(401));
        assertThat(returnedError.getMessage(), equalTo(ErrorMessages.INVALID_CREDENTIALS));
    }
}
