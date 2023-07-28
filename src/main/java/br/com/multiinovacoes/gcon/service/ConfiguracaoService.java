package br.com.multiinovacoes.gcon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.Configuracao;
import br.com.multiinovacoes.gcon.model.dto.ConfiguracaoDto;
import br.com.multiinovacoes.gcon.model.mapper.ConfiguracaoMapper;
import br.com.multiinovacoes.gcon.repository.ConfiguracaoRepository;

@Service
@Transactional
public class ConfiguracaoService {
	
	
	@Autowired
	ConfiguracaoMapper configuracaoMapper;
	
	@Autowired
	ConfiguracaoRepository configuracaoRepository;
	
	public ConfiguracaoDto getConfiguracao(Long codigoConfiguracao) {
		return configuracaoMapper.toDto(configuracaoRepository.getById(codigoConfiguracao));
	}

	public ConfiguracaoDto getConfiguracaoOrgao(Long orgao) {
		Configuracao conf = configuracaoRepository.findByOrgao(orgao);
		return configuracaoMapper.toDto(conf == null ? new Configuracao() : conf);
	}

	
	public ConfiguracaoDto salvarConfiguracao(ConfiguracaoDto configuracaoDto) {
		if (configuracaoDto.getId() == null) {
			configuracaoDto.setId(configuracaoRepository.getMaxId()+1);
		}
		return configuracaoMapper.toDto(configuracaoRepository.save(configuracaoMapper.toConfiguracao(configuracaoDto)));
	}
	


}
