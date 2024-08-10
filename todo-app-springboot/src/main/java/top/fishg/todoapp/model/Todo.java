package top.fishg.todoapp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
