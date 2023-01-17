package todoApp.steps;

import todoApp.apis.TasksAPI;
import com.github.javafaker.Faker;
import todoApp.pojoClasses.TaskModeling;
import io.restassured.response.Response;

public class TaskSteps {
    public static TaskModeling generateTask() {
        Faker faker = new Faker();
        String item = faker.name().name();
        Boolean isCompleted = false;
        return new TaskModeling(item, isCompleted);
    }

    public static String getTaskID(TaskModeling task, String token) {
        Response response = TasksAPI.addTask(task, token);
        return response.body().path("_id");
    }

}
