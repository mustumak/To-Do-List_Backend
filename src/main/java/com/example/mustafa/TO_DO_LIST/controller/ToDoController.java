package com.example.mustafa.TO_DO_LIST.controller;

import com.example.mustafa.TO_DO_LIST.dto.ToDoItemResponse;
import com.example.mustafa.TO_DO_LIST.model.TodoItems;
import com.example.mustafa.TO_DO_LIST.service.TodoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    TodoService todoService;

    @GetMapping("todo")
    public List<ToDoItemResponse> getAllTodos(){
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

    @PutMapping("todo/{id}")
    public ResponseEntity<String> updateTodoItems(@Valid @RequestBody TodoItems todoItems, @PathVariable int id){
        try{
            todoService.updateTodoItems(todoItems,id);
            return new ResponseEntity<>("ToDo item updated Successfully.",HttpStatus.CREATED);
        }catch (AccessDeniedException e) {
            return new ResponseEntity<>("Forbidden: " + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("ToDo not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
