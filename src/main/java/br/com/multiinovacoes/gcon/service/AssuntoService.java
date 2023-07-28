package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.AssuntoDto;
import br.com.multiinovacoes.gcon.model.mapper.AssuntoMapper;
import br.com.multiinovacoes.gcon.repository.AssuntoRepository;

@Service
@Transactional
public class AssuntoService {
	
	
	@Autowired
	AssuntoMapper assuntoMapper;
	
	@Autowired
	AssuntoRepository assuntoRepository;
	

	public List<AssuntoDto> getListaAssuntosOrgao(Long orgao){
		return Optional.ofNullable(assuntoMapper.fromResponseList(assuntoRepository.findByOrgao(orgao))).orElse(Collections.emptyList());
	}

	public List<AssuntoDto> getListaAssuntos(Long orgao, Long area, Integer status){
		return Optional.ofNullable(assuntoMapper.fromResponseList(assuntoRepository.findByOrgaoAndAreaAndStatusOrderByDescricaoAsc(orgao, area, status))).orElse(Collections.emptyList());
	}

	public AssuntoDto getAssunto(Long codigoAssunto, Long orgao) {
		return assuntoMapper.toDto(assuntoRepository.findByOrgaoAndId(orgao, codigoAssunto));
	}
	
	public AssuntoDto salvarAssunto(AssuntoDto assuntoDto) {
		if (assuntoDto.getId() == null) {
			assuntoDto.setId(assuntoRepository.getMaxId()+1);
			assuntoDto.setIdAntigo(0l);
			assuntoDto.setIdAreaAntigo(0l);
		}
		return assuntoMapper.toDto(assuntoRepository.save(assuntoMapper.toAssunto(assuntoDto)));
	}
	
	public List<AssuntoDto> getPesquisaAssuntoDescricao(String descricao, Long orgao){
		return Optional.ofNullable(assuntoMapper.fromResponseList(assuntoRepository.findByOrgaoAndDescricaoContainingIgnoreCase(orgao, descricao))).orElse(Collections.emptyList());
	}


}
