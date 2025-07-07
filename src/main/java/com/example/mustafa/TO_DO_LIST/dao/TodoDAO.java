package com.example.mustafa.TO_DO_LIST.dao;

import com.example.mustafa.TO_DO_LIST.model.TodoItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoDAO extends JpaRepository<TodoItems, Integer> {
}
