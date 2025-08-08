package io.github.marlonferreirab.tarefasapi.controller;

import io.github.marlonferreirab.tarefasapi.models.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.marlonferreirab.tarefasapi.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @GetMapping
    public List<TaskModel> getAllTask(){
        return taskService.getAllTasks();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskModel> getTaskById(@RequestParam Long id){
        Optional<TaskModel> taskModelOptional = taskService.getTaskById(id);
        return taskModelOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel taskModel){
        return ResponseEntity.ok(taskService.createTask(taskModel));
    }

    @PutMapping
    public ResponseEntity<TaskModel> updateTask(@PathVariable  Long id, @RequestBody TaskModel taskModel ){
        try{
            TaskModel updateTask = taskService.updateTask(id, taskModel);
            return ResponseEntity.ok(taskService.updateTask(id,taskModel));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
