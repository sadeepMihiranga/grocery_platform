package lk.grocery.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOptionDTO {

    private Long deliveryOptionId;
    private String deliveryOptionName;
    private String description;
    private Short status;
}
