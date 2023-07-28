package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.NaturezaDto;
import br.com.multiinovacoes.gcon.model.mapper.NaturezaMapper;
import br.com.multiinovacoes.gcon.repository.NaturezaRepository;

@Service
@Transactional
public class NaturezaService {
	
	@Autowired
	NaturezaMapper naturezaMapper;
	
	@Autowired
	NaturezaRepository naturezaRepository;

	public List<NaturezaDto> getListaNaturezas(Long orgao){
		return Optional.ofNullable(naturezaMapper.fromResponseList(naturezaRepository.findByOrgaoOrderByDescricaoAsc(orgao))).orElse(Collections.emptyList());
	}
	
	public NaturezaDto getNatureza(Long codigoNatureza) {
		return naturezaMapper.toDto(naturezaRepository.getById(codigoNatureza));
	}
	
	public NaturezaDto salvarNatureza(NaturezaDto naturezaDto) {
		if (naturezaDto.getId() == null) {
			naturezaDto.setId(naturezaRepository.getMaxId()+1);
		}
		return naturezaMapper.toDto(naturezaRepository.save(naturezaMapper.toNatureza(naturezaDto)));
	}
	
	public List<NaturezaDto> getPesquisaNaturezaDescricao(String descricao){
		return Optional.ofNullable(naturezaMapper.fromResponseList(naturezaRepository.findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(descricao))).orElse(Collections.emptyList());
	}


}
