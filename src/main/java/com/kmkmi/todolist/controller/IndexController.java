package com.kmkmi.todolist.controller;


import com.kmkmi.todolist.model.ToDo;
import com.kmkmi.todolist.service.ToDoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private ToDoService toDoService;

    @RequestMapping(value = "/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping(value = "index")
    public String index(ModelMap modelMap, @SortDefault(sort = {"deadLine"}) Sort sort) {

        toDoService.checker();
        List<ToDo> toDoList = toDoService.getToDoList(sort);
        List<ToDo> failList = toDoService.getFailList(sort);
        Sort descSort = new Sort(Sort.Direction.DESC, new String[]{"deadLine"});
        List<ToDo> doneList = toDoService.getDoneList(descSort);

        modelMap.addAttribute("toDoList", toDoList);
        modelMap.addAttribute("failList", failList);
        modelMap.addAttribute("doneList", doneList);
        return "index";
    }
}