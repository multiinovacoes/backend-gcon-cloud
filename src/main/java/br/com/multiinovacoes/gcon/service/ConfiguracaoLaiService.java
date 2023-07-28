package br.com.multiinovacoes.gcon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.ConfiguracaoLaiDto;
import br.com.multiinovacoes.gcon.model.mapper.ConfiguracaoLaiMapper;
import br.com.multiinovacoes.gcon.repository.ConfiguracaoLaiRepository;

@Service
@Transactional
public class ConfiguracaoLaiService {
	
	
	@Autowired
	ConfiguracaoLaiMapper configuracaoMapper;
	
	@Autowired
	ConfiguracaoLaiRepository configuracaoRepository;
	
	public ConfiguracaoLaiDto getConfiguracao(Long codigoConfiguracao) {
		return configuracaoMapper.toDto(configuracaoRepository.getById(codigoConfiguracao));
	}

	public ConfiguracaoLaiDto getConfiguracaoOrgao(Long orgao) {
		return configuracaoMapper.toDto(configuracaoRepository.findByOrgao(orgao));
	}

	
	public ConfiguracaoLaiDto salvarConfiguracao(ConfiguracaoLaiDto configuracaoDto) {
		return configuracaoMapper.toDto(configuracaoRepository.save(configuracaoMapper.toConfiguracaoLai(configuracaoDto)));
	}
	


}
