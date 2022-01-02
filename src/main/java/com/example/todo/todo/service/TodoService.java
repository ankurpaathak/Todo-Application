package com.example.todo.todo.service;

import com.example.todo.todo.controller.TodoRequestDto;
import com.example.todo.todo.controller.TodoResponseDto;
import com.example.todo.todo.data.TodoEntity;
import com.example.todo.todo.data.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public Integer createTodo(TodoRequestDto todoRequestDto){
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(todoRequestDto.getTitle());
        todoEntity.setDescription(todoRequestDto.getDescription());
        todoRepository.save(todoEntity);
        Integer id = todoEntity.getId();
        return id;

    }

    public TodoResponseDto getTodo(Integer id){
        Optional<TodoEntity> todoEntityOptional = todoRepository.findById(id);
        TodoEntity todoEntity = todoEntityOptional.get();
        TodoResponseDto todoResponseDto = new TodoResponseDto();
        todoResponseDto.setId(todoEntity.getId());
        todoResponseDto.setTitle(todoEntity.getTitle());
        todoResponseDto.setDescription(todoEntity.getDescription());
        return todoResponseDto;
    }

    public List<TodoResponseDto> getAllTodo(){
        List<TodoEntity> todoEntities = todoRepository.findAll();
        List<TodoResponseDto> todoResponseDtoList = new ArrayList<>();
        for(int i = 0; i < todoEntities.size(); i++){
            TodoEntity todoEntity = todoEntities.get(i);
            TodoResponseDto todoResponseDto = new TodoResponseDto();
            todoResponseDto.setId(todoEntity.getId());
            todoResponseDto.setTitle(todoEntity.getTitle());
            todoResponseDto.setDescription(todoEntity.getDescription());
            todoResponseDto.setCreatedAt(todoEntity.getCreatedAt());
            todoResponseDto.setUpdatedAt(todoEntity.getUpdatedAt());
            todoResponseDtoList.add(todoResponseDto);
        }
        return todoResponseDtoList;
    }

    public String deleteTodoById(Integer id){
        todoRepository.deleteById(id);
        return "Successfully deleted";
    }

    public String updateTodoById(Integer id, TodoRequestDto todoRequestDto){
        Optional<TodoEntity> todoEntityOptional = todoRepository.findById(id);
        TodoEntity todoEntity = todoEntityOptional.get();
        todoEntity.setTitle(todoRequestDto.getTitle());
        todoEntity.setDescription(todoRequestDto.getDescription());
        todoRepository.save(todoEntity);
        return "Successfully Updated";

    }

}
