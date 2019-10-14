package ecommerce.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDto implements Serializable {
    @JsonProperty("id")
    private long id;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("products")
    private List<OrderedProductDto> products;
}
