package alex.com.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EditUserRequest {

    @NotBlank(message = "Empty field not allowed")
    private String firstName;
    @NotBlank(message = "Empty field not allowed")
    private String lastName;
    @NotBlank(message = "Empty field not allowed")
    private String city;
    @NotBlank(message = "Empty field not allowed")
    private String address;
    @NotBlank(message = "Empty field not allowed")
    private String phoneNumber;
    @NotBlank(message = "Empty field not allowed")
    private String postIndex;

}
