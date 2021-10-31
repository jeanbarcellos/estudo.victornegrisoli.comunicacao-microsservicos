package br.com.cursoudemy.productapi.modules.jwt.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private Integer id;

    private String name;

    private String email;

    public static JwtResponse getUser(Claims jwtClaims) {
        try {
            return JwtResponse
                .builder()
                .id((Integer) jwtClaims.get("id"))
                .name((String) jwtClaims.get("name"))
                .email((String) jwtClaims.get("email"))
                .build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
