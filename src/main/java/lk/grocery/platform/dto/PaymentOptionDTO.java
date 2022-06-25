package lk.grocery.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOptionDTO {

    private Long paymentOptionId;
    private String paymentOptionName;
    private String description;
    private Short status;
}
