package org.example.module7.service;

import org.example.module7.domain.Task;
import org.example.module7.domain.User;
import org.example.module7.dto.TaskDTO;
import org.example.module7.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
    public Task toTask(TaskDTO taskDTO, UserRepository userRepository) {
        if (taskDTO == null) {
            return null;
        }
        User author = userRepository.findById(taskDTO.authorId()).block();
        User assignee = userRepository.findById(taskDTO.assigneeId()).block();
        Set<User> observers = new HashSet<>();
        if (taskDTO.observerIds() != null && !taskDTO.observerIds().isEmpty()) {
            observers = taskDTO.observerIds().stream()
                .map(id -> userRepository.findById(id).block())
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        }
        return new Task(
            taskDTO.id(),
            taskDTO.name(),
            taskDTO.description(),
            taskDTO.createdAt(),
            taskDTO.updatedAt(),
            taskDTO.status(),
            taskDTO.authorId(),
            taskDTO.assigneeId(),
            taskDTO.observerIds(),
            author,
            assignee,
            observers
        );
    }
}