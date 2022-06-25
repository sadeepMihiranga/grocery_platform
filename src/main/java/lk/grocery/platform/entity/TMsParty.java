package lk.grocery.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="T_MS_PARTY")
public class TMsParty extends AuditModel {

    @Id
    @NotEmpty(message = "Party Code is Required.")
    @Column(name = "PRTY_CODE")
    private String prtyCode;

    @Column(name = "PRTY_NAME")
    private String prtyName;

    @Column(name = "PRTY_FIRST_NAME")
    private String prtyFirstName;

    @Column(name = "PRTY_LAST_NAME")
    private String prtyLastName;

    @Column(name = "PRTY_ADDRESS_1")
    private String prtyAddress1;

    @Column(name = "PRTY_ADDRESS_2")
    private String prtyAddress2;

    @Column(name = "PRTY_ADDRESS_3")
    private String prtyAddress3;

    @Column(name = "PRTY_GENDER")
    private String prtyGender;

    @Column(name = "PRTY_NIC")
    private String prtyNic;

    @Column(name = "PRTY_DOB")
    private LocalDate prtyDob;

    @Column(name = "PRTY_TYPE")
    private String prtyType;

    @Column(name = "PRTY_MANAGED_BY")
    private String prtyManagedBy;

    @Column(name = "PRTY_STATUS")
    private Short prtyStatus;

    @Column(name = "PRTY_LONGITUDE")
    private BigDecimal prtyLongitude;

    @Column(name = "PRTY_LATITUDE")
    private BigDecimal prtyLatitude;
}
