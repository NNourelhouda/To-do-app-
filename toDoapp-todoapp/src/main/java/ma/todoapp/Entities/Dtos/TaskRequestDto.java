package ma.todoapp.Entities.Dtos;


import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {
        private Long id;
        private String title;
        private String description;
        private Long userId;
        private String status;
        private Date dueDate;
    }

