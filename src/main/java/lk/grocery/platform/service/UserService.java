package lk.grocery.platform.service;

import lk.grocery.platform.dto.PaginatedEntity;
import lk.grocery.platform.dto.UserDTO;
import lk.grocery.platform.entity.TMsRole;
import lk.grocery.platform.entity.TMsRoleFunction;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();

    PaginatedEntity userPaginatedSearch(String username, String partyCode, Integer page, Integer size);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByPartyCode(String partyCode);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<TMsRoleFunction> getPermissionsByRole(Long roleId);

    Long removeUserById(Long userId);

    Boolean removeUserByPartyCode(String partyCode);

    Boolean assignRoleToUser(Long userId, List<String> roles);

    TMsRole createRole(TMsRole role);

    UserDTO updateUser(Long userId, UserDTO userDTO);


}
