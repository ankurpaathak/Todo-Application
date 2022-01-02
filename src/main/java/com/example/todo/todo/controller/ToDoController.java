package com.example.todo.todo.controller;

import com.example.todo.todo.data.TodoEntity;
import com.example.todo.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/todo")
public class ToDoController {
//    @GetMapping("/test")
//    public String test(String name, int age){
//        return  name + " " + age;
//    }

//    @PostMapping("test")
//    public String test1(String employee, int salary){
//        return employee + " " + salary;
//    }
//    @PostMapping(value = "test")
//    public List<Test> test(@RequestBody List<Test> test){
//        //return "Hello Student\n" + test.getName() +"\n"+ test.getAge() +"\n"+ test.getCourse();
//        return test;
//    }
    @Autowired
    TodoService todoService;

    @PostMapping(value = "/new")
    public Integer createNewTodo(@RequestBody TodoRequestDto todoRequestDto){
        return todoService.createTodo(todoRequestDto);
    }

    @GetMapping(value = "/get")
    public TodoResponseDto getTodo(Integer id) {
        return todoService.getTodo(id);
    }

    @GetMapping(value = "/findAll/get")
    public List<TodoResponseDto> getAlltodo(){
        return todoService.getAllTodo();
    }

    @DeleteMapping(value = "/delete")
    public String deleteTodo(Integer id){
        return todoService.deleteTodoById(id);
    }

    @PutMapping(value = "/update")
    public String updateTodoById(Integer id, @RequestBody TodoRequestDto todoRequestDto){
        return todoService.updateTodoById(id, todoRequestDto);
    }


}
