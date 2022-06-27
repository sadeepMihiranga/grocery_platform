package lk.grocery.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_MS_ORDER")
public class TMsOrder extends AuditModel {

    @Id
    @GeneratedValue(generator = "OrderSequence")
    @SequenceGenerator(name = "OrderSequence", schema = "GROCERY_PLATFORM", sequenceName = "\"T_MS_ORDER_ODER_ID_seq\"", allocationSize = 1)
    @Column(name = "ODER_ID")
    private Long oderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ODER_CUSTOMER_CODE", referencedColumnName = "PRTY_CODE", nullable = false)
    private TMsParty customer;

    @Column(name = "ODER_ORDERED_DATE")
    private LocalDateTime oderOrderedDate;

    @Column(name = "ODER_STATUS")
    private String oderStatus;

    @Column(name = "ODER_URGENCY_LEVEL")
    private Short oderUrgencyLevel;

    @Column(name = "ODER_ACTIVE_STATUS")
    private Short oderActiveStatus;
}
