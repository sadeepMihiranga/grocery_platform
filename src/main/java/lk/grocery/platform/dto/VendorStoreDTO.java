package lk.grocery.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorStoreDTO {

    private Long vendorStoreId;
    private String vendorCode;
    private String vendorName;
    private String vendorType;
    private Long storeId;
    private Short status;
    private LocalDateTime createdDate;
    private String createdUserCode;
    private LocalDateTime lastUpdatedDate;
    private String lastUpdatedUserCode;
}
