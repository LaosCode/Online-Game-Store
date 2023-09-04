package alex.com.store.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private Long id;

    @NotBlank(message = "Empty field not allowed")
    private String productTitle;

    @NotBlank(message = "Empty field not allowed")
    private String producer;

    @Min(value = 2000, message = "Year shall start from 2000")
    private Integer year;

    private String category;

    @NotBlank(message = "Empty field not allowed")
    @Size(max = 255, message = "Only 255 characters allowed")
    private String description;

    @Size(max = 255, message = "Only 255 characters allowed")
    private String notes1;
    @Size(max = 255, message = "Only 255 characters allowed")
    private String notes2;

    @NotBlank(message = "Empty field not allowed")
    private String videoLink;

    private String image;

    private MultipartFile multipartFile;

    @NotNull(message = "Empty field not allowed")
    private BigDecimal price;

    @Min(value = 1, message = "Rating should be from 1 to 5")
    @Max(value = 5, message = "Rating should be from 1 to 5")
    private Integer rating;

    public String getImagePath() {
        return "\\images\\"+image;
    }

}
