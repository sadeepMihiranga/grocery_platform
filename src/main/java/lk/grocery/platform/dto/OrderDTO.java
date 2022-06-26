package lk.grocery.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long oderId;
    @NotEmpty(message = "Customer Code is Required.")
    private String customerCode;
    private LocalDateTime orderedDate;
    private String status;
    private Short urgencyLevel;
    private LocalDateTime createdDate;
    private String createdUserCode;
    private LocalDateTime lastUpdatedDate;
    private String lastUpdatedUserCode;
    private List<OrderDetailDTO> goodsList;
}
