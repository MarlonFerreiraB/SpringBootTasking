package io.github.marlonferreirab.tarefasapi.service;

import io.github.marlonferreirab.tarefasapi.models.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.marlonferreirab.tarefasapi.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskModel> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<TaskModel> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public TaskModel createTask(TaskModel taskModel){
        return taskRepository.save(taskModel);
    }

    public TaskModel updateTask(Long id, TaskModel taskModel){
        TaskModel task = taskRepository.findById(id).orElseThrow(() ->  new RuntimeException("Tarefa não encontrada"));
        task.setDescricao(taskModel.getDescricao());
        task.setCompleted(taskModel.isCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
