package com.pamirian.todo.repository;

import com.pamirian.todo.model.ToDo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ToDoRepository implements CommonRepository<ToDo>{
    private Map<String, ToDo> toDos = new HashMap<>();
    @Override
    public ToDo save(ToDo entity) {
        ToDo result = toDos.get(entity.getId());
        if(result!=null){
            // значит entity есть в базе и ее нужно просто обновить
            result.setModified(LocalDateTime.now());
            result.setDescription(entity.getDescription());
            result.setCompleted(entity.isCompleted());
            entity = result;
        }
        toDos.put(entity.getId(), entity);
        return toDos.get(entity.getId());
    }

    @Override
    public Iterable<ToDo> save(Collection<ToDo> entities) {
        entities.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(ToDo entity) {
        toDos.remove(entity.getId());
    }

    @Override
    public ToDo findById(String id) {
        return toDos.get(id);
    }

    @Override
    public Iterable<ToDo> findAll() {
        // TODO Потом сделать метод, который вернет коллекцию сущностей с сортировкой по дате создания
        return toDos.values();
    }
}
