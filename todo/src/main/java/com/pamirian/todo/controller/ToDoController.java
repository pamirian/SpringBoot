package com.pamirian.todo.controller;

import com.pamirian.todo.model.ToDo;
import com.pamirian.todo.repository.CommonRepository;
import com.pamirian.todo.validation.ToDoValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ToDoController {
    private CommonRepository<ToDo> repository;

    @Autowired
    public ToDoController(CommonRepository<ToDo> repository){
        this.repository = repository;
    }

    @GetMapping("/todo")   //  get (.../api/todo)
    public ResponseEntity<Iterable<ToDo>> getToDos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable String id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @PostMapping("/todo")
    public ResponseEntity<ToDo> createTodo(@RequestBody ToDo toDo){
        ToDo result = repository.save(toDo);
        return ResponseEntity.ok(result);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception){
        return new ToDoValidationError((exception.getMessage()));

    }
}
