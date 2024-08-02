package top.fishg.todoapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import top.fishg.todoapp.model.TodoUser;

@Repository
public interface UserJpaRepository extends JpaRepository<TodoUser, Long> {
    Optional<TodoUser> findByEmail(String email);
}
