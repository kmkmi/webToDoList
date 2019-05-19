package com.kmkmi.todolist.repository;

import com.kmkmi.todolist.model.ToDoStat;
import com.kmkmi.todolist.model.ToDo;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ToDoRepository extends PagingAndSortingRepository<ToDo, Long> {

    List<ToDo> findAllByToDoStatOrderByPriorityAsc(ToDoStat toDoStat, Sort sort);

    List<ToDo> findAllByToDoStatOrderByPriorityAsc(ToDoStat toDoStat);

}
