package com.example.mustafa.TO_DO_LIST.service;

import com.example.mustafa.TO_DO_LIST.dao.TodoDAO;
import com.example.mustafa.TO_DO_LIST.model.TodoItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoDAO todoDAO;

    public List<TodoItems> getAllTodos() {

        List<TodoItems> todoList = new ArrayList<>();
        try{
            todoList = todoDAO.findAll();

            if(todoList.isEmpty()){
                System.out.println("No todo items to show");
            }
            return todoList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addTodoItem(TodoItems todoItems) {
        todoDAO.save(todoItems);
    }
}
