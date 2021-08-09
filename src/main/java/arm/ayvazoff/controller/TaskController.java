package arm.ayvazoff.controller;

import arm.ayvazoff.domain.Task;
import arm.ayvazoff.repository.TaskRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("{id}")
    public Task getById(@PathVariable("id") Task task) {
        return task;
    }

    @PostMapping
    public void add(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Task taskFromDb, @RequestBody Task task) {
        BeanUtils.copyProperties(task, taskFromDb, "id");
        taskRepository.save(taskFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Task task) {
        taskRepository.delete(task);
    }


}
