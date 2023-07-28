package br.com.multiinovacoes.gcon.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.FeriadoDto;
import br.com.multiinovacoes.gcon.model.mapper.FeriadoMapper;
import br.com.multiinovacoes.gcon.repository.FeriadoRepository;

@Service
@Transactional
public class FeriadoService {
	
	
	@Autowired
	FeriadoMapper feriadoMapper;
	
	@Autowired
	FeriadoRepository feriadoRepository;

	public List<FeriadoDto> getListaFeriados(Long orgao){
		return Optional.ofNullable(feriadoMapper.fromResponseList(feriadoRepository.findByOrgaoOrderByDescricaoAsc(orgao))).orElse(Collections.emptyList());
	}
	
	public FeriadoDto getFeriado(Long codigoFeriado) {
		return feriadoMapper.toDto(feriadoRepository.getById(codigoFeriado));
	}
	
	public FeriadoDto salvarFeriado(FeriadoDto feriadoDto) {
		if (feriadoDto.getId() == null) {
			feriadoDto.setId(feriadoRepository.getMaxId()+1);
			feriadoDto.setDataCriacao(LocalDateTime.now());
		}
		return feriadoMapper.toDto(feriadoRepository.save(feriadoMapper.toFeriado(feriadoDto)));
	}
	
	public List<FeriadoDto> getPesquisaFeriadoDescricao(Long orgao, String descricao){
		return Optional.ofNullable(feriadoMapper.fromResponseList(feriadoRepository.findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(orgao, descricao))).orElse(Collections.emptyList());
	}


}
