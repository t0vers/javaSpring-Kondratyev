package org.example.module7.service;

import lombok.RequiredArgsConstructor;
import org.example.module7.domain.Task;
import org.example.module7.dto.TaskDTO;
import org.example.module7.repository.TaskRepository;
import org.example.module7.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper; // MapStruct mapper

    public Flux<Task> findAllWithUsers() {
        return taskRepository.findAll();
    }

    public Mono<Task> findByIdWithUsers(String id) {
        return taskRepository.findById(id);
    }

    public Mono<Task> create(TaskDTO taskDTO) {
        Task task = taskMapper.toTask(taskDTO,userRepository);
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());
        return taskRepository.save(task);
    }

    public Mono<Task> update(String id, TaskDTO taskDTO) {
        return taskRepository.findById(id)
            .flatMap(task -> {
                task.setName(taskDTO.name());
                task.setDescription(taskDTO.description());
                task.setStatus(taskDTO.status());
                task.setAssigneeId(taskDTO.assigneeId());
                task.setUpdatedAt(Instant.now());
                return taskRepository.save(task);
            })
            .switchIfEmpty(Mono.error(new RuntimeException("Task not found")));
    }


    public Mono<Task> addObserver(String taskId, String observerId) {
        return taskRepository.findById(taskId)
            .flatMap(task -> {
                task.getObserverIds().add(observerId);
                return taskRepository.save(task);
            })
            .switchIfEmpty(Mono.error(new RuntimeException("Task not found")));
    }

    public Mono<Boolean> deleteById(String id) {
        return taskRepository.deleteById(id)
            .thenReturn(true)
            .onErrorResume(e -> Mono.just(false));
    }
}
