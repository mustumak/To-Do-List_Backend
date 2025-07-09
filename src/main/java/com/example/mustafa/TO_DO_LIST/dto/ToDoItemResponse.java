package com.example.mustafa.TO_DO_LIST.dto;

import com.example.mustafa.TO_DO_LIST.model.TodoItems;
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
    private UserInfoDTO user;

    public ToDoItemResponse(TodoItems todoItems){
        this.title = todoItems.getTitle();
        this.description = todoItems.getDescription();
        this.id = todoItems.getId();
        this.status = todoItems.getStatus();
        this.createdAt = todoItems.getCreatedAt();
        this.dueDate = todoItems.getDueDate();
        this.user = new UserInfoDTO(todoItems.getUser());
    }
}
