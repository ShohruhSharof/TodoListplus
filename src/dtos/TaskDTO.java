package dtos;

import enums.TaskStatus;

import java.time.LocalDateTime;

public class TaskDTO {
    private Integer id;
    private String title;
    private String content;
    private TaskStatus status;
    private LocalDateTime created_date;//createdDate
    private LocalDateTime finished_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public LocalDateTime getFinished_date() {
        return finished_date;
    }

    public void setFinished_date(LocalDateTime finished_date) {
        this.finished_date = finished_date;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createDate=" + created_date +
                ", finishedDate=" + finished_date +
                '}';
    }


}
