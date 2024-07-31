package top.fishg.todoapp.service;

import org.springframework.stereotype.Service;
import top.fishg.todoapp.model.Todo;
import top.fishg.todoapp.repository.TodoJpaRepository;

import java.util.List;

@Service
public class TodoService {
    private final TodoJpaRepository todoJpaRepository;

    public TodoService(TodoJpaRepository todoJpaRepository) {
        this.todoJpaRepository = todoJpaRepository;
    }

    public void addOrUpdateTodo(Todo todo) {
        todoJpaRepository.save(todo);
    }

    public void deleteTodoById(Long id) {
        todoJpaRepository.deleteById(id);
    }

    public Todo getTodoById(Long id) {
        return todoJpaRepository.findById(id).orElse(null);
    }

    public List<Todo> getAllTodos() {
        return todoJpaRepository.findAll();
    }

    public List<Todo> getTodosByUserId(Long userId) {
        return todoJpaRepository.findByUserId(userId);
    }

    public void deleteTodosByUserId(Long userId) {
        todoJpaRepository.deleteByUserId(userId);
    }

}
