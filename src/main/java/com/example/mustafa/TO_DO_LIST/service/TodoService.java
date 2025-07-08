package com.example.mustafa.TO_DO_LIST.service;

import com.example.mustafa.TO_DO_LIST.dao.TodoDAO;
import com.example.mustafa.TO_DO_LIST.dao.UserDAO;
import com.example.mustafa.TO_DO_LIST.model.TodoItems;
import com.example.mustafa.TO_DO_LIST.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoDAO todoDAO;

    @Autowired
    UserDAO userDAO;

    public List<TodoItems> getAllTodos() {

        List<TodoItems> todoList = new ArrayList<>();
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userDAO.findByUsername(username).orElseThrow();

            todoList = todoDAO.findByUser(user);

            if(todoList.isEmpty()){
                System.out.println("No todo items to show");
            }
            return todoList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addTodoItem(TodoItems todoItems) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findByUsername(username).orElseThrow();
        todoItems.setUser(user);

        todoDAO.save(todoItems);
    }
}
