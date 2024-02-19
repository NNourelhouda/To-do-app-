package ma.todoapp.Services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.todoapp.Entities.Dtos.TaskRequestDto;
import ma.todoapp.Entities.Dtos.TaskResponseDto;
import ma.todoapp.Entities.Enums.TaskStatus;
import ma.todoapp.Repository.TaskRepo;
import ma.todoapp.Utils.MappingProfile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TaskServiceImpl implements TaskService{
    private TaskRepo taskRepo;

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepo.findAll().stream()
                .map(MappingProfile::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskDto) {
        var task = MappingProfile.mapToEntity(taskDto);
        return MappingProfile.mapToDto(taskRepo.save(task));
    }
    @Override
    public TaskResponseDto getTaskById(Long id) throws Exception {
        var task = taskRepo.findById(id).orElseThrow(() -> new Exception("Task not found"));
        return MappingProfile.mapToDto(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskDto) throws Exception {
        var task = taskRepo.findById(id).orElseThrow(() -> new Exception("Task not found"));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setId(taskDto.getId());
        task.setStatus(TaskStatus.valueOf(taskDto.getStatus()));
        task.setDueDate(task.getDueDate());
        return MappingProfile.mapToDto(taskRepo.save(task));
    }
    @Override
    public void deleteTask(Long id) throws Exception {
        var task = taskRepo.findById(id).orElseThrow(() -> new Exception("Task not found"));
        taskRepo.delete(task);
    }
}