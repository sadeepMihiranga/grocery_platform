package lk.grocery.platform.dto;

import lk.grocery.platform.security.JwtTokenUtil;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType = JwtTokenUtil.TOKEN_PREFIX_BEARER;

    public JwtAuthenticationResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
