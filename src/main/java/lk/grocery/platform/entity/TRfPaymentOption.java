package lk.grocery.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="T_RF_PAYMENT_OPTION")
public class TRfPaymentOption {

    @Id
    @GeneratedValue(generator = "PaymentOptionSequence")
    @SequenceGenerator(name = "PaymentOptionSequence", schema = "GROCERY_PLATFORM", sequenceName = "\"T_RF_PAYMENT_OPTION_PYOP_ID_seq\"", allocationSize = 1)
    @Column(name = "PYOP_ID")
    private Long pyopId;

    @Column(name = "PYOP_NAME")
    private String pyopName;

    @Column(name = "PYOP_DESCRIPTION")
    private String pyopDescription;

    @Column(name = "PYOP_STATUS")
    private Short pyopStatus;
}
