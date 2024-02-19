package ma.todoapp.Services;

import ma.todoapp.Entities.Dtos.TaskRequestDto;
import ma.todoapp.Entities.Dtos.TaskResponseDto;

import java.util.List;

public interface TaskService {
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto createTask(TaskRequestDto taskDto);
    TaskResponseDto getTaskById(Long id) throws Exception;
    TaskResponseDto updateTask(Long id, TaskRequestDto taskDto) throws Exception;
    void deleteTask(Long id) throws Exception;
}
