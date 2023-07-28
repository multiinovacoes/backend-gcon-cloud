package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.TipoRespostaDto;
import br.com.multiinovacoes.gcon.model.mapper.TipoRespostaMapper;
import br.com.multiinovacoes.gcon.repository.TipoRespostaRepository;

@Service
@Transactional
public class TipoRespostaService {
	
	@Autowired
	TipoRespostaMapper tipoRespostaMapper;
	
	@Autowired
	TipoRespostaRepository tipoRespostaRepository;

	public List<TipoRespostaDto> getListaTipoRespostas(Long orgao){
		return Optional.ofNullable(tipoRespostaMapper.fromResponseList(tipoRespostaRepository.findByOrgaoOrderByDescricaoAsc(orgao))).orElse(Collections.emptyList());
	}
	
	public TipoRespostaDto getTipoResposta(Long codigoTipoResposta) {
		return tipoRespostaMapper.toDto(tipoRespostaRepository.getById(codigoTipoResposta));
	}
	
	public TipoRespostaDto salvarTipoResposta(TipoRespostaDto tipoRespostaDto) {
		if (tipoRespostaDto.getId() == null) {
			tipoRespostaDto.setId(tipoRespostaRepository.getMaxId()+1);
		}
		return tipoRespostaMapper.toDto(tipoRespostaRepository.save(tipoRespostaMapper.toTipoResposta(tipoRespostaDto)));
	}
	
	public List<TipoRespostaDto> getPesquisaTipoRespostaDescricao(String descricao){
		return Optional.ofNullable(tipoRespostaMapper.fromResponseList(tipoRespostaRepository.findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(descricao))).orElse(Collections.emptyList());
	}


}
