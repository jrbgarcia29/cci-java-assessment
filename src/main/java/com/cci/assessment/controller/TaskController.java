package com.cci.assessment.controller;

import com.cci.assessment.exception.RecordNotFoundException;
import com.cci.assessment.model.Task;
import com.cci.assessment.model.User;
import com.cci.assessment.service.TaskService;
import com.cci.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @GetMapping("/users/{user_id}/tasks")
    public ResponseEntity<List<Task>> getTasks(@PathVariable("user_id") Long userId) throws RecordNotFoundException {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/users/{user_id}/tasks/{task_id}")
    public  ResponseEntity<Task> getTaskById(@PathVariable("user_id") Long userId, @PathVariable("task_id") Long taskId) throws RecordNotFoundException {
        return new ResponseEntity<>(taskService.getTaskByUserAndId(userId, taskId), HttpStatus.OK);
    }

    @PostMapping("/users/{user_id}/tasks")
    public ResponseEntity<Task> createTask(@PathVariable("user_id") Long userId, @RequestBody Task task) throws RecordNotFoundException {
        User user = userService.getUserById(userId);
        task.setUser(user);
        Task taskCreated = taskService.createTask(task);
        return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
    }

    @PutMapping("/users/{user_id}/tasks/{task_id}")
    public ResponseEntity<Task> updateUser(@PathVariable("user_id") Long userId,@PathVariable("task_id") Long taskId, @RequestBody Task task) throws RecordNotFoundException {
        User user = userService.getUserById(userId);
        task.setUser(user);
        taskService.updateTask(taskId, task);
        return new ResponseEntity<>(taskService.getTaskByUserAndId(userId, taskId), HttpStatus.OK);
    }

    @DeleteMapping("/users/{user_id}/tasks/{task_id}")
    public ResponseEntity<Object> deleteTask(@PathVariable("user_id") Long userId,@PathVariable("task_id") Long taskId) throws RecordNotFoundException {
        Task task =taskService.getTaskByUserAndId(userId, taskId);
        taskService.deleteTask(task);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
