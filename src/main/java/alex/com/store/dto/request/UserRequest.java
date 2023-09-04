package alex.com.store.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRequest {

    @Email(message = "INCORRECT_EMAIL")
    @NotBlank(message = "EMAIL_CANNOT_BE_EMPTY")
    private String email;

    @NotBlank(message = "EMPTY_FIRST_NAME")
    private String firstName;

    @Size(min = 6, max = 16, message = "PASSWORD_CHARACTER_LENGTH")
    private String password;

    @Size(min = 6, max = 16, message = "PASSWORD2_CHARACTER_LENGTH")
    private String password2;
}
