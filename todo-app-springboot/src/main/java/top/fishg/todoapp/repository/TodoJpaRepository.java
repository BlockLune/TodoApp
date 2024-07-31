package top.fishg.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.fishg.todoapp.model.Todo;

import java.util.List;

@Repository
public interface TodoJpaRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
