package top.fishg.todoapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import top.fishg.todoapp.model.Todo;
import top.fishg.todoapp.service.TodoService;

import java.util.Objects;

@Tag(name = "user-todo", description = "The todo API for a specific user")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api")
public class UserTodoController {
    private final TodoService todoService;

    public UserTodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "Get all todos of a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All todos retrieved. If there's no todo, an empty list will be returned."
            )
    })
    @GetMapping("/user/{userId}/todo")
    @PreAuthorize("#userId == authentication.principal.id")
    public ResponseEntity<Iterable<Todo>> getTodosByUserId(@Parameter @PathVariable("userId") Long userId) {
        var todos = todoService.getTodosByUserId(userId);
        return ResponseEntity.ok(todos);
    }

    @Operation(summary = "Add a new todo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Todo created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/user/{userId}/todo")
    @PreAuthorize("#userId == authentication.principal.id")
    public ResponseEntity<Todo> addTodo(
            @Parameter @PathVariable("userId") Long userId,
            @Parameter @RequestBody Todo todo
    ) {
        if (!Objects.equals(todo.getUserId(), userId)) {
            return ResponseEntity.badRequest().build();
        }
        var savedTodo = todoService.addOrUpdateTodo(todo);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTodo.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedTodo);
    }

    @Operation(summary = "Get todo by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo retrieved"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    @GetMapping("/user/{userId}/todo/{id}")
    @PreAuthorize("#userId == authentication.principal.id")
    @PostAuthorize("returnObject.todoUser.id == authentication.principal.id")
    public ResponseEntity<Todo> getTodoById(
            @Parameter @PathVariable("userId") Long userId,
            @Parameter @PathVariable("id") Long id
    ) {
        var todo = todoService.getTodoById(id);
        if (todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(todo);
    }

    @Operation(summary = "Update an existing todo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping("/user/{userId}/todo/{id}")
    @PreAuthorize("#userId == authentication.principal.id && #userId == #todo.todoUser.id")
    public ResponseEntity<Todo> updateTodo(
            @Parameter @PathVariable("userId") Long userId,
            @Parameter @PathVariable("id") Long id,
            @Parameter @RequestBody Todo todo
    ) {
        if (!Objects.equals(todo.getId(), id) || !Objects.equals(todo.getUserId(), userId)) {
            return ResponseEntity.badRequest().build();
        }
        todoService.addOrUpdateTodo(todo);
        return ResponseEntity.ok(todo);
    }

    @Operation(summary = "Delete a todo by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Todo deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/user/{userId}/todo/{id}")
    @PreAuthorize("#userId == authentication.principal.id")
    public ResponseEntity<Void> deleteTodoById(
            @Parameter @PathVariable("userId") Long userId,
            @Parameter @PathVariable("id") Long id
    ) {
        todoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }
}
