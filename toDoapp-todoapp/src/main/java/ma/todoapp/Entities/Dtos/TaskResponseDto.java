package ma.todoapp.Entities.Dtos;
import lombok.*;
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
    private String status;
    private Date dueDate;
    private Date updatedAt;
}
