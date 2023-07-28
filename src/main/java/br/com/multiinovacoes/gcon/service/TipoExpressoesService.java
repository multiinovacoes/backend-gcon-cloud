package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.TipoExpressoesDto;
import br.com.multiinovacoes.gcon.model.mapper.TipoExpressoesMapper;
import br.com.multiinovacoes.gcon.repository.TipoExpressoesRepository;

@Service
@Transactional
public class TipoExpressoesService {
	
	
	@Autowired
	TipoExpressoesMapper tipoExpressoesMapper;
	
	@Autowired
	TipoExpressoesRepository tipoExpressoesRepository;
	
	public List<TipoExpressoesDto> getListaTipoExpressoess(Long orgao){
		return Optional.ofNullable(tipoExpressoesMapper.fromResponseList(tipoExpressoesRepository.findByRelatorioGeralOrderByDescricaoAsc(0))).orElse(Collections.emptyList());
	}
	
	public TipoExpressoesDto getTipoExpressoes(Long codigoTipoExpressoes) {
		return tipoExpressoesMapper.toDto(tipoExpressoesRepository.getById(codigoTipoExpressoes));
	}
	
	public TipoExpressoesDto salvarTipoExpressoes(TipoExpressoesDto tipoExpressoesDto) {
		return tipoExpressoesMapper.toDto(tipoExpressoesRepository.save(tipoExpressoesMapper.toTipoExpressoes(tipoExpressoesDto)));
	}
	
	public List<TipoExpressoesDto> getPesquisaTipoExpressoesDescricao(Long orgao, String descricao){
		return Optional.ofNullable(tipoExpressoesMapper.fromResponseList(tipoExpressoesRepository.findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(descricao))).orElse(Collections.emptyList());
	}


}
