package alex.com.store.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRequest {

    private long id;
    private double totalPrice;

    private LocalDateTime date = LocalDateTime.now();

    @NotBlank(message = "Empty field not allowed")
    private String firstName;

    @NotBlank(message = "Empty field not allowed")
    private String lastName;


    @NotBlank(message = "Empty field not allowed")
    private String city;


    @NotBlank(message = "Empty field not allowed")
    private String address;


    @Email(message = "Incorrect Email")
    private String email;


    @NotBlank(message = "Empty field not allowed")
    private String phoneNumber;


    @Min(value = 5, message = "Incorrect code")
    private Integer postIndex;
}
