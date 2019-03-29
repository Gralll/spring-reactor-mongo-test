package com.gralll.springreactortest.repository;

import com.gralll.springreactortest.model.Task;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TaskRepository extends ReactiveCrudRepository<Task, String> {

    @Query("{ 'isFinished' : ?0}")
    Flux<Task> findTasks(Boolean isFinished);
}
