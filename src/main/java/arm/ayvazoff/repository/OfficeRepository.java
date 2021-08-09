package arm.ayvazoff.repository;

import arm.ayvazoff.domain.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, String> {
}
