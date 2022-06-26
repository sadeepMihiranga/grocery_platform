package lk.grocery.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_MS_ITEM")
public class TMsItem extends AuditModel {

    @Id
    @GeneratedValue(generator = "ItemSequence")
    @SequenceGenerator(name = "ItemSequence", schema = "GROCERY_PLATFORM", sequenceName = "\"T_MS_ITEM_ITEM_ID_seq\"", allocationSize = 1)
    @Column(name = "ITEM_ID")
    private Long itemId;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ITEM_CATEGORY", referencedColumnName = "ITCT_ID", nullable = false)
    private TMsItemCategory itemCategory;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ITEM_BRAND", referencedColumnName = "ITBD_ID", nullable = false)
    private TMsItemBrand itemBrand;

    @Column(name = "ITEM_QTY_OR_WEIGHT")
    private BigDecimal itemQTyOrWeight;

    @Column(name = "ITEM_UOM")
    private String itemUom;

    @Column(name = "ITEM_IMAGE_URL")
    private String itemImageUrl;

    @Column(name = "ITEM_STATUS")
    private String itemStatus;
}
