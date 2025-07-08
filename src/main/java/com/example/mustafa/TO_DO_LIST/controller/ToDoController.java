package com.example.mustafa.TO_DO_LIST.controller;

import com.example.mustafa.TO_DO_LIST.dao.UserDAO;
import com.example.mustafa.TO_DO_LIST.model.TodoItems;
import com.example.mustafa.TO_DO_LIST.model.User;
import com.example.mustafa.TO_DO_LIST.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    @Autowired
    TodoService todoService;

    @GetMapping("todo")
    public List<TodoItems> getAllTodos(){
        try{
            return todoService.getAllTodos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("todo")
    public ResponseEntity<String> addTodoItem(@Valid @RequestBody TodoItems todoItems){
        try{
            todoService.addTodoItem(todoItems);
            return new ResponseEntity<>("ToDo item added Successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while adding ToDo item",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
