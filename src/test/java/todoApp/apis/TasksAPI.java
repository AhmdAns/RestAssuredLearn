package todoApp.apis;

import todoApp.data.Paths;
import todoApp.pojoClasses.TaskModeling;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import todoApp.utils.Specs;

import static io.restassured.RestAssured.given;

public class TasksAPI {
    public static Response addTask(TaskModeling task, String token){
        return  given()
                .spec(Specs.getSpecs())
                .auth().oauth2(token)
                .body(task)
                .when().post(Paths.TASK_ENDPOINT)
                .then().log().all().extract().response();
    }
    public static Response getAllTasks(String token){
        return given()
                .spec(Specs.getSpecs())
                .auth().oauth2(token)
                .when().get(Paths.TASK_ENDPOINT)
                .then().log().all().extract().response();
    }
    public static Response getTaskDetails(String token,String taskID){
        return  given()
                .spec(Specs.getSpecs())
                .auth().oauth2(token)
                .when().get(Paths.TASK_ENDPOINT + taskID)
                .then().log().all().extract().response();
    }
    public static Response updateTask(TaskModeling task,String token,String taskID){
        return given()
                .spec(Specs.getSpecs())
                .body(task)
                .auth().oauth2(token)
                .when().put(Paths.TASK_ENDPOINT + taskID)
                .then().log().all().extract().response();
    }
    public static Response deleteTask(TaskModeling task,String token,String taskIDToBeDeleted){
        return given()
                .spec(Specs.getSpecs())
                .body(task)
                .auth().oauth2(token)
                .when().delete(Paths.TASK_ENDPOINT + taskIDToBeDeleted)
                .then().log().all().extract().response();
    }
}
