package br.com.multiinovacoes.gcon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.DescricaoOuvidoria;
import br.com.multiinovacoes.gcon.model.dto.DescricaoOuvidoriaDto;
import br.com.multiinovacoes.gcon.model.mapper.DescricaoOuvidoriaMapper;
import br.com.multiinovacoes.gcon.repository.DescricaoOuvidoriaRepository;

@Service
@Transactional
public class DescricaoOuvidoriaService {
	
	
	@Autowired
	DescricaoOuvidoriaMapper descricaoOuvidoriaMapper;
	
	@Autowired
	DescricaoOuvidoriaRepository descricaoOuvidoriaRepository;
	
	public DescricaoOuvidoriaDto getDescricaoOuvidoriaOrgao(Long orgao) {
		DescricaoOuvidoria descricaoOuvidoria = descricaoOuvidoriaRepository.findByOrgao(orgao);
		return descricaoOuvidoriaMapper.toDto(descricaoOuvidoria == null ? new DescricaoOuvidoria() : descricaoOuvidoria);
	}

	
	public DescricaoOuvidoriaDto salvarDescricaoOuvidoria(DescricaoOuvidoriaDto descricaoOuvidoriaDto) {
		if (descricaoOuvidoriaDto.getId() == null) {
			descricaoOuvidoriaDto.setId(descricaoOuvidoriaRepository.getMaxId()+1);
			descricaoOuvidoriaDto.setStatus(0);
		}
		return descricaoOuvidoriaMapper.toDto(descricaoOuvidoriaRepository.save(descricaoOuvidoriaMapper.toDescricaoOuvidoria(descricaoOuvidoriaDto)));
	}
	


}
