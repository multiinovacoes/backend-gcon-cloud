package br.com.multiinovacoes.gcon.config.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import br.com.multiinovacoes.gcon.model.LogoTipo;
import br.com.multiinovacoes.gcon.repository.LogoTipoRepository;
import br.com.multiinovacoes.gcon.security.UsuarioSistema;

@SuppressWarnings("deprecation")
public class CustomTokenEnhancer implements TokenEnhancer { 
	
	@Autowired
	LogoTipoRepository logotipoRepository;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UsuarioSistema usuarioSistema = (UsuarioSistema) authentication.getPrincipal();
		LogoTipo logotipo = logotipoRepository.findByOrgao(usuarioSistema.getUsuario().getOrgao());
		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("nome", usuarioSistema.getUsuario().getNome());
		addInfo.put("id_orgao", usuarioSistema.getUsuario().getOrgao());
		addInfo.put("id_usuario", usuarioSistema.getUsuario().getId());
		addInfo.put("logo", logotipo.getNome());
		//addInfo.put("authorities2", usuarioSistema.getAuthorities());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}

}
