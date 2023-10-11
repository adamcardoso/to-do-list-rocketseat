package br.com.adam.todolist.controllers;

import br.com.adam.todolist.entities.task.Task;
import br.com.adam.todolist.repositories.TaskRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/")
    public Task create(@RequestBody Task taskEntity){
        var task = this.taskRepository.save(taskEntity);

        return task;
    }
}
