package com.gralll.springreactortest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private String id;

    private String subject;

    private String description;

    private LocalDateTime creationDate;

    private LocalDateTime finishDate;

    private Boolean isFinished;
}
