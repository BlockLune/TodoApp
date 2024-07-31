package top.fishg.todoapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import top.fishg.todoapp.model.Todo;
import top.fishg.todoapp.service.TodoService;

import java.util.List;

@Tag(name = "todo", description = "The todo API")
@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "Get all todos", tags = {"todo"})
    @GetMapping("/todo")
    public ResponseEntity<List<Todo>> getTodosByUserId() {
        var todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @Operation(summary = "Get todo by its ID",
            description = "Note that it's the ID of the todo, not the ID of user",
            tags = {"todo"})
    @GetMapping("/todo/{id}")
    public ResponseEntity<Todo> getTodoById(
            @Parameter(description = "ID of the todo") // the @Parameter annotation is optional
            @PathVariable("id") // the argument of @PathVariable is necessary
            Long id
    ) {
        var todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @Operation(summary = "Add or update a todo", tags = {"todo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo updated"),
            @ApiResponse(responseCode = "201", description = "Todo created")
    })
    @PostMapping("/todo")
    public ResponseEntity<Todo> addOrUpdateTodo(
            @Parameter(description = "Todo object added or updated")
            @RequestBody
            Todo todo
    ) {
        boolean newTodo = todo.getId() == null || todoService.getTodoById(todo.getId()) == null;
        todoService.addOrUpdateTodo(todo);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todo.getId())
                .toUri();

        if (newTodo) return ResponseEntity.created(location).body(todo);
        return ResponseEntity.ok(todo);
    }
}
