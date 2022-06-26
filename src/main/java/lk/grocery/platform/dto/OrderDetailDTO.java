package lk.grocery.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    private Long orderDetailId;
    private Long orderId;
    @NotNull(message = "Item id is Required.")
    private Long itemId;
    @NotNull(message = "Requested is Required.")
    private BigDecimal requestedQty;
    @NotEmpty(message = "Uom is Required.")
    private String uom;
    private Short showAltFlag;
    private Short status;
    private LocalDateTime createdDate;
    private String createdUserCode;
    private LocalDateTime lastUpdatedDate;
    private String lastUpdatedUserCode;
}
