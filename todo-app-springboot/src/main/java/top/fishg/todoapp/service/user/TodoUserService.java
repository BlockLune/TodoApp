package top.fishg.todoapp.service.user;

import org.springframework.stereotype.Service;
import top.fishg.todoapp.model.TodoUser;
import top.fishg.todoapp.repository.TodoUserRepository;

import java.util.Optional;

@Service
public class TodoUserService {
    private final TodoUserRepository todoUserRepository;

    public TodoUserService(TodoUserRepository todoUserRepository) {
        this.todoUserRepository = todoUserRepository;
    }

    public TodoUser addOrUpdateUser(TodoUser user) {
        return todoUserRepository.save(user);
    }

    public void deleteUserById(Long id) {
        todoUserRepository.deleteById(id);
    }

    public void deleteUserByEmail(String email) {
        todoUserRepository.deleteByEmail(email);
    }

    public Optional<TodoUser> getUserById(Long id) {
        return todoUserRepository.findById(id);
    }

    public Optional<TodoUser> getUserByEmail(String email) {
        return todoUserRepository.findByEmail(email);
    }

    public Iterable<TodoUser> getAllUsers() {
        return todoUserRepository.findAll();
    }
}
