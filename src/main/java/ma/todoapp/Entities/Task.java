package ma.todoapp.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.todoapp.Entities.Enums.TaskStatus;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;


@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Check if null
    @Column(name = "title",nullable = false)
    private String title;


    @Column(name = "description")
    private String description;

    //Change to enum
    //Check if null
    // Change the type of the status field to StatusEnums.TaskStatus
    @Column(name = "status",nullable = false)
    private TaskStatus status;

    //Check if null
    //Check  date is valid (due date in the future)
    @NotNull
    @Future(message = "Due date must be in the future")
    @Column(name = "due_date", nullable = false)
    private Date dueDate;


    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

