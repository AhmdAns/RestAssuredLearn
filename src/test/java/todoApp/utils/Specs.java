package todoApp.utils;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import todoApp.data.Paths;

import static io.restassured.RestAssured.given;

public class Specs {
    public static String getBaseURL_ENV() {
        String environment = System.getProperty("env", "PROD");
        String baseURL;
        switch (environment) {
            case "PROD":
                baseURL = Paths.BASE_URL_PROD;
                break;
            case "LOCAL":
                baseURL = Paths.BASE_URL_LOCAL;
                break;
            default:
                throw new RuntimeException("environment is not supported");
        }
        return baseURL;
    }

    public static RequestSpecification getSpecs() {
        return given()
                .baseUri(getBaseURL_ENV())
                .contentType(ContentType.JSON)
                .log().all();
    }
}
