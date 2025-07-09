package com.example.mustafa.TO_DO_LIST.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ToDoItemResponse {
    private int id;
    private String title;
    private String description;
    private String status;
    private LocalDate createdAt;
    private LocalDate dueDate;
}
