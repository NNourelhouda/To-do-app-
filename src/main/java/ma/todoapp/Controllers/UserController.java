package ma.todoapp.Controllers;

import lombok.AllArgsConstructor;
import ma.todoapp.Controllers.exception.UserNotFound;
import ma.todoapp.Entities.Dtos.UserRequestDto;
import ma.todoapp.Entities.Dtos.UserResponseDto;
import ma.todoapp.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userDto) {
        try { // Validation with Function interface
            // Call the createUser method in the service
            return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handle validation error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the user.");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById( @PathVariable Long id) throws UserNotFound {
        try {
            return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
        } catch (UserNotFound e) {
            return new ResponseEntity<>("User Not Found!",HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userDto) {
        try {
            UserResponseDto updatedUser = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }catch (Exception e) {
            return null;
    }
        }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) throws UserNotFound {
        try {
            userService.deleteUser(id);
            return "User id: "+id+" deleted!";
        } catch (UserNotFound e) {
            return "\nUser Not Found!";
        }
    }
}
