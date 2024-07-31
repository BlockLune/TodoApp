package top.fishg.todoapp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "todo")
@Schema(description = "A todo item")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The ID of the todo item")
    private Long id;

    @Column(name = "user_id", nullable = false)
    @Schema(description = "The ID of the user who owns the todo item")
    private Long userId;

    @Column(name = "title", nullable = false)
    @Schema(description = "The title of the todo item")
    private String title;

    @Column(name = "description")
    @Schema(description = "The description of the todo item")
    private String description;

    @Column(name = "target_date", nullable = false)
    @Schema(description = "The target date of the todo item. Must be in the format of yyyy-MM-dd")
    private LocalDate targetDate;

    @Column(name = "is_done", nullable = false)
    @Schema(description = "Whether the todo item is done")
    private boolean isDone;

    public Todo() {
    }

    public Todo(Long userId, String title, String description, LocalDate targetDate, boolean isDone) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }
}
