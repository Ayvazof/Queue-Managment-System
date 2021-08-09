package arm.ayvazoff.repository;

import arm.ayvazoff.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByName(String username);
    User findByLogin(String login);
}
