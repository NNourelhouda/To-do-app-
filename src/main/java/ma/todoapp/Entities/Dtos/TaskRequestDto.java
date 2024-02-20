package ma.todoapp.Entities.Dtos;


import lombok.*;
import ma.todoapp.Entities.Enums.TaskStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {
        private Long id;
        private String title;
        private String description;
        private Long userId;
        private TaskStatus status;
        private Date dueDate;
public boolean isValidDueDate() {
        Date currentDate = new Date();
        return dueDate.after(currentDate);
} }

