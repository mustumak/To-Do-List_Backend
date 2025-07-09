package com.example.mustafa.TO_DO_LIST.service;

import com.example.mustafa.TO_DO_LIST.dao.TodoDAO;
import com.example.mustafa.TO_DO_LIST.dao.UserDAO;
import com.example.mustafa.TO_DO_LIST.dto.ToDoItemResponse;
import com.example.mustafa.TO_DO_LIST.model.TodoItems;
import com.example.mustafa.TO_DO_LIST.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoDAO todoDAO;

    @Autowired
    UserDAO userDAO;

    public List<ToDoItemResponse> getAllTodos() {

        List<TodoItems> todoList = new ArrayList<>();
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userDAO.findByUsername(username).orElseThrow();

            todoList = todoDAO.findByUser(user);

//            List<ToDoItemResponse> resp = new ArrayList<>();
//            for(TodoItems todoItem: todoList){
//                resp.add(new ToDoItemResponse(todoItem.getId(),todoItem.getTitle(),todoItem.getDescription(),
//                        todoItem.getStatus(),todoItem.getCreatedAt(),todoItem.getDueDate()));
//            }

            List<ToDoItemResponse> responses = todoList.stream()
                    .map(ToDoItemResponse::new)
                    .toList();

            if(todoList.isEmpty()){
                System.out.println("No todo items to show");
            }
            return responses;

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

    public void updateTodoItems(@Valid TodoItems todoItems, int id) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userDAO.findByUsername(username).orElseThrow();
            TodoItems todo = todoDAO.findById(id).orElseThrow();

            if(user.getId() == todo.getUser().getId()){
                todo = updateToDo(todo,todoItems);
                todoDAO.save(todo);
            }else {
                throw new AccessDeniedException("You can only update your specific ToDo Items");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private TodoItems updateToDo(TodoItems oldTodo, @Valid TodoItems newTodo) {
        oldTodo.setTitle(newTodo.getTitle());
        oldTodo.setDescription(newTodo.getDescription());
        oldTodo.setStatus(newTodo.getStatus());
        oldTodo.setDueDate(newTodo.getDueDate());

        return oldTodo;
    }

}
