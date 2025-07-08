package com.example.mustafa.TO_DO_LIST.dao;

import com.example.mustafa.TO_DO_LIST.model.TodoItems;
import com.example.mustafa.TO_DO_LIST.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoDAO extends JpaRepository<TodoItems, Integer> {

    List<TodoItems> findByUser(User user);
}
