package lk.grocery.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="T_RF_DELIVERY_OPTION")
public class TRfDeliveryOption {

    @Id
    @GeneratedValue(generator = "DeliveryOptionSequence")
    @SequenceGenerator(name = "DeliveryOptionSequence", schema = "GROCERY_PLATFORM", sequenceName = "\"T_RF_DELIVERY_OPTION_DVOP_ID_seq\"", allocationSize = 1)
    @Column(name = "DVOP_ID")
    private Long dvopId;

    @Column(name = "DVOP_NAME")
    private String dvopName;

    @Column(name = "DVOP_DESCRIPTION")
    private String dvopDescription;

    @Column(name = "DVOP_STATUS")
    private Short dvopStatus;
}
