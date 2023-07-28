package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.PerguntaFrequenteDto;
import br.com.multiinovacoes.gcon.model.mapper.PerguntaFrequenteMapper;
import br.com.multiinovacoes.gcon.repository.PerguntaFrequenteRepository;

@Service
@Transactional
public class PerguntaFrequenteService {
	
	
	@Autowired
	PerguntaFrequenteMapper perguntaFrequenteMapper;
	
	@Autowired
	PerguntaFrequenteRepository perguntaFrequenteRepository;

	public List<PerguntaFrequenteDto> getListaPerguntaFrequente(Long orgao){
		return Optional.ofNullable(perguntaFrequenteMapper.fromResponseList(perguntaFrequenteRepository.findByOrgaoOrderByPerguntaAsc(orgao))).orElse(Collections.emptyList());
	}
	
	public PerguntaFrequenteDto getPerguntaFrequente(Long codigoPergunta) {
		return perguntaFrequenteMapper.toDto(perguntaFrequenteRepository.getById(codigoPergunta));
	}
	
	public PerguntaFrequenteDto salvar(PerguntaFrequenteDto perguntaFrequenteDto) {
		return perguntaFrequenteMapper.toDto(perguntaFrequenteRepository.save(perguntaFrequenteMapper.toPerguntaFrequente(perguntaFrequenteDto)));
	}
	


}
