package arm.ayvazoff.controller;

import arm.ayvazoff.domain.Office;
import arm.ayvazoff.domain.Ticket;
import arm.ayvazoff.repository.OfficeRepository;
import arm.ayvazoff.service.OfficeService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
public class OfficeController {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private OfficeService officeService;

    @GetMapping
    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    @GetMapping("{id}")
    public Office getById(@PathVariable("id") Office office) {
        return office;
    }

    @PostMapping
    public void add(@RequestBody Office office) {
        officeRepository.save(office);
    }

    @PutMapping("/task")
    @ApiOperation(value = "Attach task to office",
            notes = "Provide task and office id to attach task to office",
            response = Office.class)
    public void attachTask(@RequestParam("taskId") String taskId,
                        @RequestParam("officeId") String officeId) {
        officeService.attachTask(taskId, officeId);
    }

    @DeleteMapping("/task")
    @ApiOperation(value = "Detach task from office",
            notes = "Provide task and office id to detach task from office",
            response = Office.class)
    public void detachTask(@RequestParam("taskId") String taskId,
                           @RequestParam("officeId") String officeId) {
        officeService.detachTask(taskId, officeId);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Office officeFromDb,
                       @RequestBody Office office) {
        BeanUtils.copyProperties(office, officeFromDb, "id");
        officeRepository.save(officeFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Office officeFromDb) {
        officeRepository.delete(officeFromDb);
    }

}
