package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.AreaLaiDto;
import br.com.multiinovacoes.gcon.model.mapper.AreaLaiMapper;
import br.com.multiinovacoes.gcon.repository.AreaLaiRepository;

@Service
@Transactional
public class AreaLaiService {
	
	
	@Autowired
	AreaLaiMapper areaLaiMapper;
	
	@Autowired
	AreaLaiRepository areaRepository;

	public List<AreaLaiDto> getListaAreas(Long orgao){
		return Optional.ofNullable(areaLaiMapper.fromResponseList(areaRepository.findByOrgaoOrderByDescricaoAsc(orgao))).orElse(Collections.emptyList());
	}

	public List<AreaLaiDto> getListaAreas(Long orgao, Long instituicao){
		return Optional.ofNullable(areaLaiMapper.fromResponseList(areaRepository.findByOrgaoAndInstituicaoOrderByDescricaoAsc(orgao, instituicao))).orElse(Collections.emptyList());
	}

	public AreaLaiDto getArea(Long codigoArea) {
		return areaLaiMapper.toDto(areaRepository.getById(codigoArea));
	}
	
	public AreaLaiDto salvarArea(AreaLaiDto areaDto) {
		return areaLaiMapper.toDto(areaRepository.save(areaLaiMapper.toAreaLai(areaDto)));
	}
	
	public List<AreaLaiDto> getPesquisaAreaDescricao(Long orgao, String descricao){
		return Optional.ofNullable(areaLaiMapper.fromResponseList(areaRepository.findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(orgao, descricao))).orElse(Collections.emptyList());
	}


}
