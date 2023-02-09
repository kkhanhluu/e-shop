package eshop.productservice.model;

import jakarta.validation.constraints.DecimalMin;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class CreateProductDTO {
    private String name;
    private String description;
    @DecimalMin("0.0")
    private BigDecimal price;
}