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
@Table(name="T_MS_STORE")
public class TMsStore extends AuditModel {

    @Id
    @GeneratedValue(generator = "StoreSequence")
    @SequenceGenerator(name = "StoreSequence", schema = "GROCERY_PLATFORM", sequenceName = "\"T_MS_STORE_STOR_ID_seq\"", allocationSize = 1)
    @Column(name = "STOR_ID")
    private Long storId;

    @Column(name = "STOR_NAME")
    private String storName;

    @Column(name = "STOR_REG_NO")
    private String storRegNo;

    @Column(name = "STOR_ADDRESS_1")
    private String storAddress1;

    @Column(name = "STOR_ADDRESS_2")
    private String storAddress2;

    @Column(name = "STOR_ADDRESS_3")
    private String storAddress3;

    @Column(name = "STOR_CONTACT_NO")
    private String storContactNo;

    @Column(name = "STOR_EMAIL")
    private String storEmail;

    @Column(name = "STOR_LONGITUDE")
    private BigDecimal storLongitude;

    @Column(name = "STOR_LATITUDE")
    private BigDecimal storLatitude;

    @Column(name = "STOR_STATUS")
    private Short storStatus;
}
