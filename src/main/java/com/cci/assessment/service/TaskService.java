package com.cci.assessment.service;

import com.cci.assessment.exception.RecordNotFoundException;
import com.cci.assessment.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasksByUserId(Long userId) throws RecordNotFoundException;

    Task getTaskByUserAndId(Long userId, Long taskId) throws RecordNotFoundException;

    Task getTaskById(Long id) throws RecordNotFoundException;

    Task createTask(Task task);

    void updateTask(Long id, Task task) throws RecordNotFoundException;

    void deleteTask(Task task) throws RecordNotFoundException;

    void updateLapsedTasks();


}
