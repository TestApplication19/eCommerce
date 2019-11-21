package ecommerce.domain.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
	@ApiModelProperty(readOnly = true)
    private Long id;
	@ApiModelProperty(required = true)
    private String name;
    private String description;
    @ApiModelProperty(example = "3.59")
    private BigDecimal price;
}
