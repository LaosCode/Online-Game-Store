package alex.com.store.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.Collection;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {
    private static final double PRICE_MULTIPLIER = 1.15;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", initialValue = 109, allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String productTitle;

    @Column(name = "producer")
    private String producer;

    @Column(name = "year")
    private Integer year;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "notes1")
    private String notes1;

    @Column(name = "notes2")
    private String notes2;

    @Column(name = "video_link")
    private String videoLink;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "rating")
    private Integer rating;

    public String getImagePath() {
        return "\\images\\"+image;
    }

    public BigDecimal getIncreasedPrice() {
        return price.multiply(BigDecimal.valueOf(PRICE_MULTIPLIER)).setScale(2,BigDecimal.ROUND_DOWN);
    }
}
