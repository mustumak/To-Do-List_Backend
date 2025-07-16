package com.example.mustafa.TO_DO_LIST.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class TodoItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotNull(message = "Description cannot be empty")
    @Size(max = 500, message = "Description too Long")
    private String description;
    private String status;
    private LocalDate createdAt;
    private LocalDate dueDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    protected void onCreate(){
        this.setCreatedAt(LocalDate.now());
        if(this.status == null || this.status.isEmpty())
            this.setStatus("Pending");
    }

}
