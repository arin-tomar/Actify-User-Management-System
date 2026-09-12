package com.actify.ums.controller;

import com.actify.ums.entity.Task;
import com.actify.ums.entity.User;
import com.actify.ums.service.TaskService;
import com.actify.ums.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserByEmail(authentication.getName()));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getMyTasks(Authentication authentication) {
        return ResponseEntity.ok(taskService.getTasksForUser(authentication.getName()));
    }
}
