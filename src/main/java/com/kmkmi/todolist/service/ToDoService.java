package com.kmkmi.todolist.service;


import com.kmkmi.todolist.repository.ToDoRepository;
import com.kmkmi.todolist.model.ToDo;
import com.kmkmi.todolist.model.ToDoStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    public ToDo saveToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public List<ToDo> getToDoList(Sort sort) {
        return toDoRepository.findAllByToDoStatOrderByPriorityAsc(ToDoStat.TO_DO, sort);
    }

    public List<ToDo> getFailList(Sort sort) {
        return toDoRepository.findAllByToDoStatOrderByPriorityAsc(ToDoStat.FAIL, sort);
    }

    public List<ToDo> getDoneList( Sort sort) {
        return toDoRepository.findAllByToDoStatOrderByPriorityAsc(ToDoStat.DONE, sort);
    }

    public void deleteToDo(Long toDoNo) {
        toDoRepository.deleteById(toDoNo);
    }

    public ToDo getToDo(Long toDoNo) {
        return toDoRepository.findById(toDoNo).get();
    }

    public void updateToDo(ToDo exToDo) {
        toDoRepository.save(exToDo);
    }


    public void checker() {
        List<ToDo> toDoList = toDoRepository.findAllByToDoStatOrderByPriorityAsc(ToDoStat.TO_DO);
        Date now = new Date();
        for (ToDo toDo : toDoList) {
            if (toDo.getDeadLine().compareTo(now) <= 0) {
                toDo.setToDoStat(ToDoStat.FAIL);
                toDoRepository.save(toDo);
            } else {
                toDo.setRemained(calculate(toDo.getDeadLine()));
            }
        }
    }

    private long calculate(Date goalDate) {
        Calendar now = Calendar.getInstance();
        Calendar goal = Calendar.getInstance();
        goal.setTime(goalDate);

        long tday = goal.getTimeInMillis()/86400000;
        long day = now.getTimeInMillis()/86400000;
        long count = tday - day;

        return count+1;
    }

    public void statToDone(Long toDoNo) {
        ToDo toDo = toDoRepository.findById(toDoNo).get();
        toDo.setToDoStat(ToDoStat.DONE);

        toDoRepository.save(toDo);
}

    public void statToFail(Long toDoNo) {
        ToDo toDo = toDoRepository.findById(toDoNo).get();
        toDo.setToDoStat(ToDoStat.FAIL);

        toDoRepository.save(toDo);
    }

}
