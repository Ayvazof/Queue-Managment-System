package arm.ayvazoff.repository;

import arm.ayvazoff.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
