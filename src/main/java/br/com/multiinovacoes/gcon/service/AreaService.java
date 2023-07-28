package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.AreaDto;
import br.com.multiinovacoes.gcon.model.mapper.AreaMapper;
import br.com.multiinovacoes.gcon.repository.AreaRepository;

@Service
@Transactional
public class AreaService {
	
	
	@Autowired
	AreaMapper areaMapper;
	
	@Autowired
	AreaRepository areaRepository;

	public List<AreaDto> getListaAreas(Long orgao){
		return Optional.ofNullable(areaMapper.fromResponseList(areaRepository.findByOrgaoOrderByDescricaoAsc(orgao))).orElse(Collections.emptyList());
	}
	
	public AreaDto getArea(Long codigoArea) {
		return areaMapper.toDto(areaRepository.getById(codigoArea));
	}
	
	public AreaDto salvarArea(AreaDto areaDto) {
		if (areaDto.getId() == null) {
			areaDto.setId(areaRepository.getMaxId()+1);
			areaDto.setIdAntigo(0l);
		}
		return areaMapper.toDto(areaRepository.save(areaMapper.toArea(areaDto)));
	}
	
	public List<AreaDto> getPesquisaAreaDescricao(Long orgao, String descricao){
		return Optional.ofNullable(areaMapper.fromResponseList(areaRepository.findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(orgao, descricao))).orElse(Collections.emptyList());
	}


}
