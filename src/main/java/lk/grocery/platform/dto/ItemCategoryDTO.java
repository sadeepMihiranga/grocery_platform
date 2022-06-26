package lk.grocery.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryDTO {

    private Long itemCategoryId;
    private String itemCategoryName;
    private String itemCategoryType;
    private String itemCategoryTypeName;
    private String description;
    private Short status;
}
