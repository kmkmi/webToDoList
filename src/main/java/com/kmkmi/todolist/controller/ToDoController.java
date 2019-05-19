package com.kmkmi.todolist.controller;

import com.kmkmi.todolist.model.ToDo;
import com.kmkmi.todolist.service.ToDoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "todo")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;



    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        ToDo toDo = new ToDo();
        modelMap.addAttribute("todo", toDo);
        return "addtodo";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addProcessing(@ModelAttribute("todo") ToDo toDo) {

        toDoService.saveToDo(toDo);

        return "redirect:/index";
    }

    @RequestMapping(value = "{toDoNo}/remove")
    public String deleteToDo(@PathVariable Long toDoNo) {



        toDoService.deleteToDo(toDoNo);


        return "redirect:/index";
    }

    @RequestMapping(value = "{toDoNo}/detail")
    public String toDoDetail(@PathVariable Long toDoNo, ModelMap modelMap) {
        ToDo toDo = toDoService.getToDo(toDoNo);

        modelMap.addAttribute("todo", toDo);

        return "tododetail";
    }

    @RequestMapping(value = "{toDoNo}/done")
    public String doneToDo(@PathVariable Long toDoNo) {

        toDoService.statToDone(toDoNo);

        return "redirect:/index";
    }

    @RequestMapping(value = "{toDoNo}/edit")
    public String editToDo(@PathVariable Long toDoNo, ModelMap modelMap) {
        ToDo exToDo = toDoService.getToDo(toDoNo);

        modelMap.addAttribute("extodo", exToDo);
        modelMap.addAttribute("todo", new ToDo());

        return "edittodo";
    }

    @RequestMapping(value = "{toDoNo}/fail")
    public String failToDo(@PathVariable Long toDoNo) {

        toDoService.statToFail(toDoNo);

        return "redirect:/index";
    }

    @RequestMapping(value = "{toDoNo}/editProcessing", method = RequestMethod.POST)
    public String editProcessing(@PathVariable Long toDoNo, @ModelAttribute ToDo toDo) {

        toDo.setNo(toDoNo);

        toDoService.updateToDo(toDo);

        return "redirect:/todo/" + toDoNo + "/detail";
    }
}
