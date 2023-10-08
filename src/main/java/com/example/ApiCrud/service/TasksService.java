package com.example.ApiCrud.service;

import com.example.ApiCrud.dto.TasksDTORequest;
import com.example.ApiCrud.dto.TasksDTOResponse;
import com.example.ApiCrud.entity.TaskEntity;
import com.example.ApiCrud.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public TasksDTOResponse createTask(TaskEntity taskEntity) {
        TaskEntity savedTaskEntity = tasksRepository.save(taskEntity);

        TasksDTOResponse taskDTO = TasksDTOResponse.builder()
                .id(taskEntity.getId())
                .titulo(taskEntity.getTitulo())
                .descricao(taskEntity.getDescricao())
                .data(taskEntity.getData())
                .prioridades(taskEntity.getPrioridade())
                .build();

        return taskDTO;
    }

    public TasksDTOResponse readTaskById(Integer id) {
        Optional<TaskEntity> taskOptional = tasksRepository.findById(id);

        if (taskOptional.isEmpty()) {
            throw new RuntimeException("Id não cadastrado no banco de dados");
        }

        TasksDTOResponse taskDTO = buildDTOResponse(taskOptional.get());

        return taskDTO;
    }

    private TaskEntity getTaskById(Integer id) {
        return tasksRepository.findById(id).orElseThrow(() -> new RuntimeException("Id não existente no banco"));
    }

    public Collection<TasksDTOResponse> readAllTask() {
        List<TaskEntity> allTaskEntities = tasksRepository.findAll();

        Set<TasksDTOResponse> result = allTaskEntities.stream().map(t -> new TasksDTOResponse(t.getId(), t.getTitulo(),
                t.getDescricao(), t.getData(), t.getPrioridade())).collect(Collectors.toSet());

        return result;
    }

    public TasksDTOResponse updateTask(Integer id, TasksDTORequest request) {

        TaskEntity taskEntityFounded = getTaskById(id);

        taskEntityFounded.setTitulo(request.getTitulo());
        taskEntityFounded.setDescricao(request.getDescricao());
        taskEntityFounded.setData(request.getData());
        taskEntityFounded.setPrioridade(request.getPrioridade());

        TaskEntity savedTaskEntity = tasksRepository.save(taskEntityFounded);

        TasksDTOResponse dtoResponse = buildDTOResponse(savedTaskEntity);

        return dtoResponse;
    }

    public void deleteTask(Integer id) {
        tasksRepository.delete(getTaskById(id));
    }

    private TasksDTOResponse buildDTOResponse(TaskEntity taskEntity) {
        return TasksDTOResponse.builder()
                .id(taskEntity.getId())
                .titulo(taskEntity.getTitulo())
                .descricao(taskEntity.getDescricao())
                .data(taskEntity.getData())
                .prioridades(taskEntity.getPrioridade()).build();
    }

}
