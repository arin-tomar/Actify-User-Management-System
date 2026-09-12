package com.actify.ums.repository;

import com.actify.ums.entity.Task;
import com.actify.ums.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedTo(User user);
}
