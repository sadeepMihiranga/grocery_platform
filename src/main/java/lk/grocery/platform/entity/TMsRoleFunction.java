package lk.grocery.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="T_MS_ROLE_FUNCTION")
public class TMsRoleFunction {

    @javax.persistence.Id
    @GeneratedValue(generator = "RoleFunctionSequence")
    //@SequenceGenerator(name = "RoleFunctionSequence", schema = "GROCERY_PLATFORM", sequenceName = "\"T_MS_ROLE_FUNCTION_ROFU_Id_seq\"", allocationSize = 1)
    @SequenceGenerator(name = "RoleFunctionSequence", schema = "public", sequenceName = "\"T_MS_ROLE_FUNCTION_ROFU_Id_seq\"", allocationSize = 1)
    @Column(name = "ROFU_ID")
    private Long rofuId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ROFU_ROLE_ID", referencedColumnName = "ROLE_ID", nullable = false)
    private TMsRole role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ROFU_FUNC_ID", referencedColumnName = "FUNC_ID", nullable = false)
    private TMsFunction function;

    @Column(name = "ROFU_STATUS")
    private Short rofuStatus;
}
