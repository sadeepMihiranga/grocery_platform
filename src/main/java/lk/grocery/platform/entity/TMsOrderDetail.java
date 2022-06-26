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
@Table(name="T_MS_ORDER_DETAIL")
public class TMsOrderDetail extends AuditModel {

    @Id
    @GeneratedValue(generator = "OrderDetailSequence")
    @SequenceGenerator(name = "OrderDetailSequence", schema = "GROCERY_PLATFORM", sequenceName = "\"T_MS_ORDER_DETAIL_ODDT_ID_seq\"", allocationSize = 1)
    @Column(name = "ODDT_ID")
    private Long oddtId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ODDT_ORDER_ID", referencedColumnName = "ODER_ID", nullable = false)
    private TMsOrder order;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ODDT_ITEM_ID", referencedColumnName = "ITEM_ID", nullable = false)
    private TMsItem item;

    @Column(name = "ODDT_REQUESTED_QTY")
    private BigDecimal oddtRequestedQty;

    @Column(name = "ODDT_UOM")
    private String oddtUom;

    @Column(name = "ODDT_SHOW_ALT_FLAG")
    private Short oddtShowAltFlag;

    @Column(name = "ODDT_STATUS")
    private Short oddtStatus;
}
