package com.gralll.springreactortest.rest;

import com.gralll.springreactortest.model.Task;
import com.gralll.springreactortest.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class ReactController {

    private final TaskRepository taskRepository;

    @GetMapping("/tasks")
    Flux<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{isFinished}")
    Flux<Task> getFinishedTasks(@PathVariable Boolean isFinished) {
        return taskRepository.findTasks(isFinished);
    }
}
