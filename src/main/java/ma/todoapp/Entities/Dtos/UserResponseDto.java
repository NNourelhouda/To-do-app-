package ma.todoapp.Entities.Dtos;

import lombok.Data;
@Data
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;
}
