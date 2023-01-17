package todoApp.apis;

import todoApp.data.Paths;
import todoApp.pojoClasses.UserModeling;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import todoApp.utils.Specs;

import static io.restassured.RestAssured.given;

public class UserAPI {

    public static Response register(UserModeling user){
        return given()
                .spec(Specs.getSpecs())
                .body(user)
                .when().post(Paths.REGISTER_ENDPOINT)
                .then().log().all()
                .extract().response();
    }
    public static Response login(UserModeling userModeling){
        return given()
                .spec(Specs.getSpecs())
                .body(userModeling)
                .when().post(Paths.LOGIN_ENDPOINT)
                .then().log().all().extract().response();
    }
}
