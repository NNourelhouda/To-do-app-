package ma.todoapp.Services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.todoapp.Controllers.exception.NotFoundException;
import ma.todoapp.Entities.Dtos.TaskRequestDto;
import ma.todoapp.Entities.Dtos.TaskResponseDto;
import ma.todoapp.Entities.Enums.TaskStatus;
import ma.todoapp.Repository.TaskRepo;
import ma.todoapp.Utils.MappingProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class TaskServiceImpl implements TaskService{
    @Autowired
    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo){
        this.taskRepo = taskRepo;
    }
    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepo.findAll().stream()
                .map(MappingProfile::mapToDto)
                .collect(Collectors.toList());
    }



    @Override
    public TaskResponseDto createTask(TaskRequestDto taskDto) {
        if (!taskDto.isValidDueDate()) {
            throw new IllegalArgumentException("Due date must be in the future");
        }
        var task = MappingProfile.mapToEntity(taskDto);
        return MappingProfile.mapToDto(taskRepo.save(task));
    }

    @Override
    public TaskResponseDto getTaskById(Long id) throws NotFoundException {
        var task = taskRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + id));
        return MappingProfile.mapToDto(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskDto) throws NotFoundException {
        var task = taskRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + id));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(task.getDueDate());
        return MappingProfile.mapToDto(taskRepo.save(task));
    }
    @Override
    public void deleteTask(Long id) throws NotFoundException {
        var task = taskRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + id));
        taskRepo.delete(task);
    }
}