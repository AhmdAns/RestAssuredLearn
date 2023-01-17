package todoApp.pojoClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskModeling {
    public TaskModeling() {
    }

    public TaskModeling(String item, Boolean isCompleted) {
        this.item = item;
        this.isCompleted = isCompleted;
    }

    public TaskModeling(String item) {
        this.item = item;
    }

    public TaskModeling(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    private Boolean isCompleted;
    @JsonProperty("_id")
    private String id;
    private String item;
    private String userID;
    private String createdAt;
    @JsonProperty("__v")
    private String v;

    @JsonProperty("isCompleted")
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    @JsonProperty("isCompleted")
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("__v")
    public String getVersion() {
        return v;
    }

    @JsonProperty("__v")
    public void setVersion(String version) {
        this.v = version;
    }
}
