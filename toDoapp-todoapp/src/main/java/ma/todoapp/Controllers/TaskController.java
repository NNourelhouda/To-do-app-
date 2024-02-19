package ma.todoapp.Controllers;

import lombok.AllArgsConstructor;
import ma.todoapp.Entities.Dtos.TaskRequestDto;
import ma.todoapp.Services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    // add CRUD methods
    // add ExceptionHandling
    // create custom exceptions NotFoundException, DataNotValidException, etc.
    // BONUS : Add Swagger documentation (OpenAPI)!
    @GetMapping("/")
    public ResponseEntity<?> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskRequestDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }

}

