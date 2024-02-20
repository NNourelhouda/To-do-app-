package ma.todoapp.Services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.todoapp.Entities.Dtos.UserRequestDto;
import ma.todoapp.Entities.Dtos.UserResponseDto;
import ma.todoapp.Entities.Enums.TaskStatus;
import ma.todoapp.Repository.UserRepo;
import ma.todoapp.Utils.MappingProfile;
import org.springframework.stereotype.Service;
import ma.todoapp.Controllers.exception.UserNotFound;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
    @AllArgsConstructor
    public class UserService {
        private final UserRepo userRepo;

        public List<UserResponseDto> getAllUsers() {
            return userRepo.findAll()
                    .stream()
                    .map(MappingProfile::mapToUserDto)
                    .collect(Collectors.toList());
        }


@Transactional
        public UserResponseDto createUser(@Valid UserRequestDto userDto) {
            if (!userDto.isValidEmail(userDto.getEmail())) {
        throw new IllegalArgumentException("Invalid email address");
    }
            var user = MappingProfile.mapToUserEntity(userDto);
            return MappingProfile.mapToUserDto(userRepo.save(user));
        }

    public Object getUserById(Long id) throws UserNotFound {
        var user = userRepo.findById(id).orElseThrow(UserNotFound::new);
        return new Object(){
            public Long id = user.getId();
            public String fullName = user.getLastName().toUpperCase() + ", " + user.getFirstName();
            public String email = user.getEmail();

            public List<Object> tasks = Collections.singletonList(user.getTasks().stream().map(task -> new Object() {
                public Long id = task.getId();
                public String title = task.getTitle();
                public String description = task.getDescription();
                public TaskStatus status = task.getStatus();
                public String dueDate = task.getDueDate().toString();
                public String createdAt = task.getCreatedAt().toString();
                public String updatedAt = task.getUpdatedAt().toString();
            }).toList());
        };
    }

        public UserResponseDto addUser(@Valid UserRequestDto userDto) {
            var user = MappingProfile.mapToUserEntity(userDto);
            return MappingProfile.mapToUserDto(userRepo.save(user));
        }

        public UserResponseDto updateUser(Long id, UserRequestDto userDto) throws UserNotFound {
            var user = userRepo.findById(id).orElseThrow(UserNotFound::new);
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            return MappingProfile.mapToUserDto(userRepo.save(user));
        }
    public void deleteUser(Long id) throws UserNotFound{
        var user = userRepo.findById(id).orElseThrow(UserNotFound::new);
        userRepo.delete(user);
    }

}


