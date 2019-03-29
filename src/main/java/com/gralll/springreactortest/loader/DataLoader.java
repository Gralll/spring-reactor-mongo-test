package com.gralll.springreactortest.loader;

import com.gralll.springreactortest.model.Task;
import com.gralll.springreactortest.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Count: " + taskRepository.count().block());
        List<Task> tasks = Arrays.asList(
                Task.builder()
                    .id("1")
                    .subject("Make dinner")
                    .description("Make dinner for family")
                    .creationDate(now())
                    .finishDate(now().plus(2L, DAYS))
                    .isFinished(false)
                    .build(),
                Task.builder()
                    .id("2")
                    .subject("Read book")
                    .description("Read java 8 book")
                    .creationDate(now().minus(3L, DAYS))
                    .finishDate(now().minus(1L, DAYS))
                    .isFinished(true)
                    .build(),
                Task.builder()
                    .id("3")
                    .subject("Visit theater")
                    .description("Go to the comedy")
                    .creationDate(now().minus(1L, DAYS))
                    .finishDate(now())
                    .isFinished(false)
                    .build(),
                Task.builder()
                    .id("4")
                    .subject("Do homework")
                    .description("Do english homework")
                    .creationDate(now().minus(1L, DAYS))
                    .finishDate(now())
                    .isFinished(false)
                    .build(),
                Task.builder()
                    .id("5")
                    .subject("Watch movie")
                    .description("Watch new movie")
                    .creationDate(now().minus(1L, DAYS))
                    .finishDate(now())
                    .isFinished(false)
                    .build(),
                Task.builder()
                    .id("6")
                    .subject("Watch movie")
                    .description("Watch one more new movie")
                    .creationDate(now().minus(1L, DAYS))
                    .finishDate(now())
                    .isFinished(false)
                    .build(),
                Task.builder()
                    .id("7")
                    .subject("Watch youtube")
                    .description("Watch new video")
                    .creationDate(now().minus(1L, DAYS))
                    .finishDate(now())
                    .isFinished(false)
                    .build());

        taskRepository.saveAll(tasks).subscribe(task -> log.info("Loaded: {}", task),
                throwable -> log.error("Error: ", throwable),
                () -> log.info("Count: " + taskRepository.count().block()));
    }
}
