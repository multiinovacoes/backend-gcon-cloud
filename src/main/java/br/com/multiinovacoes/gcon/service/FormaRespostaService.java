package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.FormaRespostaDto;
import br.com.multiinovacoes.gcon.model.mapper.FormaRespostaMapper;
import br.com.multiinovacoes.gcon.repository.FormaRespostaRepository;

@Service
@Transactional
public class FormaRespostaService {
	
	
	@Autowired
	FormaRespostaMapper formaRespostaMapper;
	
	@Autowired
	FormaRespostaRepository formaRespostaRepository;

	public List<FormaRespostaDto> getListaFormaRespostas(){
		return Optional.ofNullable(formaRespostaMapper.fromResponseList(formaRespostaRepository.findAll(Sort.by(Sort.Direction.ASC, "descricao")))).orElse(Collections.emptyList());
	}
	
	public FormaRespostaDto getFormaResposta(Long codigoFormaResposta) {
		return formaRespostaMapper.toDto(formaRespostaRepository.getById(codigoFormaResposta));
	}
	
	public FormaRespostaDto salvarFormaResposta(FormaRespostaDto formaRespostaDto) {
		return formaRespostaMapper.toDto(formaRespostaRepository.save(formaRespostaMapper.toFormaResposta(formaRespostaDto)));
	}
	
	public List<FormaRespostaDto> getPesquisaFormaRespostaDescricao(String descricao){
		return Optional.ofNullable(formaRespostaMapper.fromResponseList(formaRespostaRepository.findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(descricao))).orElse(Collections.emptyList());
	}


}
