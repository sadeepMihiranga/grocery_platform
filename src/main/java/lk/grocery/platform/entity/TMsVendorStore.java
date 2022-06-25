package lk.grocery.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_MS_VENDOR_STORE")
public class TMsVendorStore extends AuditModel {

    @Id
    @GeneratedValue(generator = "VendorStoreSequence")
    @SequenceGenerator(name = "VendorStoreSequence", schema = "GROCERY_PLATFORM", sequenceName = "\"T_MS_VENDOR_STORE_VNST_ID_seq\"", allocationSize = 1)
    @Column(name = "VNST_ID")
    private Long vnstId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VNST_VENDOR_CODE", referencedColumnName = "PRTY_CODE", nullable = false)
    private TMsParty vendor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VNST_STORE_ID", referencedColumnName = "STOR_ID", nullable = false)
    private TMsStore store;

    @Column(name = "VNST_STATUS")
    private Short vnstStatus;
}
