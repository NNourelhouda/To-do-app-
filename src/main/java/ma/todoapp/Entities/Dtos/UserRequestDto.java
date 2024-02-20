package ma.todoapp.Entities.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
public boolean isValidEmail(String email) {
    String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(regex);
    return pattern.matcher(email).matches();
}
}

