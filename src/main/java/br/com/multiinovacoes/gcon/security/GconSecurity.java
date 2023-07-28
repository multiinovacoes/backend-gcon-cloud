package br.com.multiinovacoes.gcon.security;

import java.text.ParseException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;

@SuppressWarnings("deprecation")
@Component
public class GconSecurity {
	
	
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
//	public Long getIdUsuario() {
//		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
//		
//		return jwt.getClaim("id_usuario");
//	}
//	
//	public Long getOrgao() {
//		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
//		
//		return jwt.getClaim("id_orgao");
//	}

	public OAuth2AuthenticationDetails getAuthenticationDetail() {
		return (OAuth2AuthenticationDetails) getAuthentication().getDetails();
	}
	
	public JWT getJWT() {
		try {
		return JWTParser.parse(getAuthenticationDetail().getTokenValue());
		}catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
	
	public Long getOrgao() {
		try {
		return getJWT().getJWTClaimsSet().getLongClaim("id_orgao");
		}catch (ParseException e) {
			e.getMessage();
		}
		return null;
	}

	public Long getIdUsuario() {
		try {
		return getJWT().getJWTClaimsSet().getLongClaim("id_usuario");
		}catch (ParseException e) {
			e.getMessage();
		}
		return null;
	}


	public JWT getJJwt(String token) {
		try {
		return JWTParser.parse(token.substring(7));
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return null;
	}

}
