package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.AssuntoLaiDto;
import br.com.multiinovacoes.gcon.model.mapper.AssuntoLaiMapper;
import br.com.multiinovacoes.gcon.repository.AssuntoLaiRepository;

@Service
@Transactional
public class AssuntoLaiService {
	
	
	@Autowired
	AssuntoLaiMapper assuntoLaiMapper;
	
	@Autowired
	AssuntoLaiRepository assuntoLaiRepository;
	

	public List<AssuntoLaiDto> getListaAssuntosOrgao(Long orgao){
		return Optional.ofNullable(assuntoLaiMapper.fromResponseList(assuntoLaiRepository.findByOrgao(orgao))).orElse(Collections.emptyList());
	}

	public List<AssuntoLaiDto> getListaAssuntos(Long orgao, Long area, Integer status){
		return Optional.ofNullable(assuntoLaiMapper.fromResponseList(assuntoLaiRepository.findByOrgaoAndAreaAndStatusOrderByDescricaoAsc(orgao, area, status))).orElse(Collections.emptyList());
	}

	public List<AssuntoLaiDto> getListaAssuntos(Long orgao, Long area){
		return Optional.ofNullable(assuntoLaiMapper.fromResponseList(assuntoLaiRepository.findByOrgaoAndAreaOrderByDescricaoAsc(orgao, area))).orElse(Collections.emptyList());
	}

	public AssuntoLaiDto getAssunto(Long codigoAssunto) {
		return assuntoLaiMapper.toDto(assuntoLaiRepository.getById(codigoAssunto));
	}
	
	public AssuntoLaiDto salvarAssunto(AssuntoLaiDto assuntoDto) {
		return assuntoLaiMapper.toDto(assuntoLaiRepository.save(assuntoLaiMapper.toAssuntoLai(assuntoDto)));
	}
	
	public List<AssuntoLaiDto> getPesquisaAssuntoDescricao(String descricao, Long orgao){
		return Optional.ofNullable(assuntoLaiMapper.fromResponseList(assuntoLaiRepository.findByOrgaoAndDescricaoContainingIgnoreCase(orgao, descricao))).orElse(Collections.emptyList());
	}


}
