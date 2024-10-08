package top.fishg.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.fishg.todoapp.model.TodoUser;

import java.util.Optional;

@Repository
public interface TodoUserRepository extends JpaRepository<TodoUser, Long> {
    Optional<TodoUser> findByEmail(String email);
    void deleteByEmail(String email);
}
