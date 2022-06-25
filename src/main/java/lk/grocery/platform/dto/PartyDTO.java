package lk.grocery.platform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyDTO implements Paginated {

    private String partyCode;
    private String name;
    @NotBlank(message = "First Name is required")
    private String firstName;
    private String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @NotBlank(message = "Address is required")
    private String address1;
    private String address2;
    private String address3;
    private String gender;
    private String genderName;
    private String nic;
    private String type;
    private String managedBy;
    private String managedByName;
    private Short status;
    private LocalDateTime createdDate;
    private String createdUserCode;
    private LocalDateTime lastUpdatedDate;
    private String lastUpdatedUserCode;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private List<PartyContactDTO> contactList;
    private List<UserDTO> users;
}
