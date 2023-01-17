package todoApp.tests;

import todoApp.data.ErrorMessages;
import todoApp.steps.TaskSteps;
import todoApp.apis.TasksAPI;
import todoApp.pojoClasses.ErrorsModeling;
import todoApp.pojoClasses.TaskModeling;
import todoApp.steps.UserSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaskTest {
    String token = UserSteps.returnUserToken();

    @Test(description = "user should be able to add task")
    public void addTask() {
        TaskModeling taskModeling = TaskSteps.generateTask();
        Response response = TasksAPI.addTask(taskModeling, token);
        TaskModeling returnedData=response.body().as(TaskModeling.class);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(returnedData.getItem(), equalTo(taskModeling.getItem()));
        assertThat(returnedData.getIsCompleted(),equalTo(taskModeling.getIsCompleted()));
    }

    @Test(description = "user shouldn't be able to add task when item not provided")
    public void invalidAddTask() {
        TaskModeling taskModeling = new TaskModeling(false);
        Response response = TasksAPI.addTask(taskModeling,token);
        ErrorsModeling returnedError=response.body().as(ErrorsModeling.class);
        assertThat(response.statusCode(), equalTo(400));
        assertThat(returnedError.getMessage(), equalTo(ErrorMessages.IS_COMPLETED_REQUIRED));
    }

    @Test(description = "user should see list of tasks when send get all tasks request")
    public void getAllTasks() {
        String token = UserSteps.returnUserToken();
        TaskModeling task = TaskSteps.generateTask();
        TasksAPI.addTask(task, token);
        Response response = TasksAPI.getAllTasks(token);
        assertThat(response.path("tasks.size()"), equalTo(1));
        assertThat(response.path("tasks.item[0]"), equalTo(task.getItem()));
    }

    @Test(description = "user should be able to see task details")
    public void getTaskDetails() {
        TaskModeling task = TaskSteps.generateTask();
        String taskID = TaskSteps.getTaskID(task,token);
        Response response =TasksAPI.getTaskDetails(token,taskID);
        TaskModeling returnedData=response.body().as(TaskModeling.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedData.getIsCompleted(), equalTo(task.getIsCompleted()));
        assertThat(returnedData.getItem(), equalTo(task.getItem()));
    }

    @Test(description = "user should be able to update a task")
    public void updateTaskDetails() {
        TaskModeling task = TaskSteps.generateTask();
        String taskID = TaskSteps.getTaskID(task,token);
        Response response=TasksAPI.updateTask(task,token,taskID);
        TaskModeling returnedData=response.body().as(TaskModeling.class);
        assertThat(response.statusCode(),equalTo(200));
        assertThat(returnedData.getIsCompleted(), equalTo(task.getIsCompleted()));
        assertThat(returnedData.getItem(),equalTo(task.getItem()));
    }
    @Test(description = "user should be able to delete a task")
    public void deleteTask() {
        TaskModeling task = TaskSteps.generateTask();
        String taskID = TaskSteps.getTaskID(task,token);
        Response response=TasksAPI.deleteTask(task,token,taskID);
        TaskModeling returnedData=response.body().as(TaskModeling.class);
        assertThat(response.statusCode(),equalTo(200));
        assertThat(returnedData.getIsCompleted(), equalTo(task.getIsCompleted()));
        assertThat(returnedData.getItem(),equalTo(task.getItem()));
    }
}
