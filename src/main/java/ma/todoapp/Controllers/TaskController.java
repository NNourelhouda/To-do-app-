package ma.todoapp.Controllers;

import lombok.AllArgsConstructor;
import ma.todoapp.Controllers.exception.NotFoundException;
import ma.todoapp.Entities.Dtos.TaskRequestDto;
import ma.todoapp.Entities.Dtos.TaskResponseDto;
import ma.todoapp.Services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ma.todoapp.Entities.Dtos.UserRequestDto;
import ma.todoapp.Entities.Dtos.UserResponseDto;


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
    public ResponseEntity<?> addTask(@RequestBody TaskRequestDto taskDto) {
        try {
            taskService.createTask(taskDto);
            return ResponseEntity.ok("Task added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage() + " " + e.getCause() + " " + e.getStackTrace());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the task");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById( @PathVariable Long id) throws NotFoundException {

        try {
            return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//@PathVariable is used to extract data from the URI, particularly for dynamic values like IDs.
//@RequestBody is used to extract data from the request body, typically used for complex objects or data sent in JSON or XML format.
        @PutMapping("/{id}")
        public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto taskDto) {
            try {
                TaskResponseDto updatedTask = taskService.updateTask(id,taskDto);
                return ResponseEntity.ok(updatedTask);
            } catch (Exception e) {
                return null;
            }
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteTask(@PathVariable Long id) throws NotFoundException {
            taskService.deleteTask(id);
            return ResponseEntity.ok("Task with ID " + id + " deleted successfully");
        }

    }

