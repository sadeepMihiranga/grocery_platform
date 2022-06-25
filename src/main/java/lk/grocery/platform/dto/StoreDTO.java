package lk.grocery.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO implements Paginated {

    private Long storeId;
    @NotBlank(message = "Store Name is required")
    private String storeName;
    private String regNo;
    @NotBlank(message = "Address is required")
    private String address1;
    private String address2;
    private String address3;
    @NotBlank(message = "Contact Number is required")
    private String contactNo;
    private String email;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Short status;
    private LocalDateTime createdDate;
    private String createdUserCode;
    private LocalDateTime lastUpdatedDate;
    private String lastUpdatedUserCode;
}
