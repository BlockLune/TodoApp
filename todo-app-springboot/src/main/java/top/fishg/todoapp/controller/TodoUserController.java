package top.fishg.todoapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import top.fishg.todoapp.dto.AddOrUpdateTodoUserRequest;
import top.fishg.todoapp.model.TodoUser;
import top.fishg.todoapp.service.user.TodoUserService;

import java.net.URI;
import java.util.Set;

@Tag(name = "user", description = "The user API")
@RestController
@RequestMapping("/api/user")
public class TodoUserController {
    private final TodoUserService todoUserService;
    private final PasswordEncoder passwordEncoder;

    public TodoUserController(TodoUserService todoUserService, PasswordEncoder passwordEncoder) {
        this.todoUserService = todoUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(
            summary = "Get all users",
            description = "Only admin can get all users",
            tags = {"user"}
    )
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Iterable<TodoUser>> getAllUsers() {
        return ResponseEntity.ok(todoUserService.getAllUsers());
    }

    @Operation(
            summary = "Get user by its ID",
            description = "Only if the user is the same as the authenticated user, " +
                    "or the authenticated user is an admin, the user can be retrieved.",
            tags = {"user"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<TodoUser> getUserById(@Parameter @PathVariable("id") Long id) {
        var userOptional = todoUserService.getUserById(id);
        return userOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a user",
            tags = {"user"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created")
    })
    @PostMapping("/")
    public ResponseEntity<TodoUser> addUser(@RequestBody AddOrUpdateTodoUserRequest request) {
        var newUser = new TodoUser();
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setName(request.name());
        newUser.setRoles(Set.of("USER"));

        var savedUser = todoUserService.addOrUpdateUser(newUser);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @Operation(
            summary = "Update a user",
            description = "Only if the user is the same as the authenticated user, " +
                    "or the authenticated user is an admin, the user can be updated.",
            tags = {"user"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<TodoUser> updateUser(@Parameter @PathVariable("id") Long id, @RequestBody AddOrUpdateTodoUserRequest request) {
        var userOptional = todoUserService.getUserById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var user = userOptional.get();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setName(request.name());
        return ResponseEntity.ok(todoUserService.addOrUpdateUser(user));
    }

    @Operation(
            summary = "Delete a user by its ID",
            description = "Only if the user is the same as the authenticated user, " +
                    "or the authenticated user is an admin, the user can be deleted.",
            tags = {"user"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> deleteUserById(@Parameter @PathVariable("id") Long id) {
        todoUserService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
