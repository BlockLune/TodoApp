package top.fishg.todoapp.dto;

public record AddOrUpdateTodoUserRequest(String email, String password, String name) {
}
