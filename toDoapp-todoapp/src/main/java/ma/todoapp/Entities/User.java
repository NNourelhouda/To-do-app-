package ma.todoapp.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Check if null
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    //Check if null
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    //Check if null & validate email & unique
    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

}
