package br.com.multiinovacoes.gcon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.LogoTipoDto;
import br.com.multiinovacoes.gcon.model.mapper.LogoTipoMapper;
import br.com.multiinovacoes.gcon.repository.LogoTipoRepository;

@Service
@Transactional
public class LogoTipoService {
	
	
	@Autowired
	LogoTipoMapper logoTipoMapper;
	
	@Autowired
	LogoTipoRepository logoTipoRepository;
	
	public LogoTipoDto getLogoTipo(Long codigoLogoTipo) {
		return logoTipoMapper.toDto(logoTipoRepository.getById(codigoLogoTipo));
	}

	public LogoTipoDto getLogoTipoOrgao(Long orgao) {
		return logoTipoMapper.toDto(logoTipoRepository.findByOrgao(orgao));
	}

	
	public LogoTipoDto salvarLogoTipo(LogoTipoDto logoTipoDto) {
		return logoTipoMapper.toDto(logoTipoRepository.save(logoTipoMapper.toLogoTipo(logoTipoDto)));
	}
	


}
