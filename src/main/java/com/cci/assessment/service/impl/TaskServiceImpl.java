package com.cci.assessment.service.impl;

import com.cci.assessment.exception.RecordNotFoundException;
import com.cci.assessment.model.Task;
import com.cci.assessment.repository.TaskRepository;
import com.cci.assessment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getTasksByUserId(Long userId) throws RecordNotFoundException {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findTasksByUser(userId).forEach(tasks::add);
        if(tasks.size() > 0) return tasks;
        else throw new RecordNotFoundException("No task record found for user_id = " + userId);
    }

    @Override
    public Task getTaskByUserAndId(Long userId, Long taskId) throws RecordNotFoundException {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findTasksByUserAndId(userId, taskId).forEach(tasks::add);
        if(tasks.size() > 0) return tasks.get(0);
        else throw new RecordNotFoundException("Task record not found");
    }


    @Override
    public Task getTaskById(Long id) throws RecordNotFoundException {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()) return task.get();
        else throw new RecordNotFoundException("Task record not found");
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, Task task) throws RecordNotFoundException {
        Optional<Task> existingTask = taskRepository.findById(id);
        if(existingTask.isPresent()){
            if(task.getName() != null) existingTask.get().setName(task.getName());
            if(task.getDescription() != null) existingTask.get().setDescription(task.getDescription());
            if(task.getDateTime() != null) existingTask.get().setDateTime(task.getDateTime());
            taskRepository.save(existingTask.get());
        }else {
            throw new RecordNotFoundException("No task has been updated. Record not found");
        }

    }

    @Override
    public void deleteTask(Task task) throws RecordNotFoundException {
        if(task == null) throw new RecordNotFoundException("No Task can be deleted.");
        taskRepository.delete(task);
    }

    @Override
    public void updateLapsedTasks(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskRepository.updateLapsedTasks(sdf.format(new Date()));
    }
}
