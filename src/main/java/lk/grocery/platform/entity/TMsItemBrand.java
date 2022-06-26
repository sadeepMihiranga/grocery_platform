package lk.grocery.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_RF_ITEM_BRAND")
public class TMsItemBrand {

    @Id
    @GeneratedValue(generator = "ItemBrandSequence")
    @SequenceGenerator(name = "ItemBrandSequence", schema = "GROCERY_PLATFORM", sequenceName = "\"T_RF_ITEM_BRAND_ITBD_ID_seq\"", allocationSize = 1)
    @Column(name = "ITBD_ID")
    private Long itbdId;

    @Column(name = "ITBD_NAME")
    private String itbdName;

    @Column(name = "ITBD_DESCRIPTION")
    private String itbdDescription;

    @Column(name = "ITBD_STATUS")
    private String itbdStatus;
}
