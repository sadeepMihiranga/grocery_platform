package lk.grocery.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="T_RF_ITEM_CATEGORY")
public class TRfItemCategory {

    @Id
    @GeneratedValue(generator = "ItemCategorySequence")
    @SequenceGenerator(name = "ItemCategorySequence", schema = "GROCERY_PLATFORM", sequenceName = "\"T_RF_ITEM_CATEGORY_ITCT_ID_seq\"", allocationSize = 1)
    @Column(name = "ITCT_ID")
    private Long itctId;

    @Column(name = "ITCT_NAME")
    private String itctName;

    @Column(name = "ITCT_CATEGORY_TYPE")
    private String itctCategoryType;

    @Column(name = "ITCT_DESCRIPTION")
    private String itctDescription;

    @Column(name = "ITCT_STATUS")
    private Short itctStatus;
}
