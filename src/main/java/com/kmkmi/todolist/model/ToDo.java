package com.kmkmi.todolist.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(name = "title")
    private String title;

    @Column(name = "priority")
    private Priority priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "dead_line")
    private Date deadLine;

    @Column(name = "text")
    private String text;

    @Column(name = "todo_stat", length = 30, columnDefinition = "varchar(30) default 'TO_DO'")
    @Enumerated(EnumType.STRING)
    private ToDoStat toDoStat = ToDoStat.TO_DO;

    @Transient
    private long remained;

}
