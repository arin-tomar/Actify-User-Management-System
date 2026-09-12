package com.actify.ums.service;

import com.actify.ums.dto.TaskRequest;
import com.actify.ums.entity.Task;
import com.actify.ums.entity.User;
import com.actify.ums.repository.TaskRepository;
import com.actify.ums.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(TaskRequest request) {
        User user = userRepository.findById(request.getAssignedToUserId())
                .orElseThrow(() -> new IllegalArgumentException("Assigned user not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setAssignedTo(user);

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getUserTasks(User user) {
        return taskRepository.findByAssignedTo(user);
    }
}
