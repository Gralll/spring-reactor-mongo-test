package com.gralll.springreactortest.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private List<Task> tasks;

}
