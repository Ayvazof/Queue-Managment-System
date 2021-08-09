package arm.ayvazoff.service;

import arm.ayvazoff.domain.Office;
import arm.ayvazoff.repository.OfficeRepository;
import arm.ayvazoff.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private TaskRepository taskRepository;

    public void attachTask(String taskId, String officeId) {
        Office office = officeRepository.getOne(officeId);
        office.attachTask(taskRepository.getOne(taskId));
        officeRepository.save(office);
    }

    public void detachTask(String taskId, String officeId) {
        Office office = officeRepository.getOne(officeId);
        office.detachTask(taskRepository.getOne(taskId));
        officeRepository.save(office);
    }
}
