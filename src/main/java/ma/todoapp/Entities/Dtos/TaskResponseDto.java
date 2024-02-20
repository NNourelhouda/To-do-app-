package ma.todoapp.Entities.Dtos;
import lombok.*;
import ma.todoapp.Entities.Enums.TaskStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private TaskStatus status;
    private Date dueDate;
    private Date updatedAt;
}
