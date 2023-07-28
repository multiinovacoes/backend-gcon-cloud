package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.BairroDto;
import br.com.multiinovacoes.gcon.model.mapper.BairroMapper;
import br.com.multiinovacoes.gcon.repository.BairroRepository;

@Service
@Transactional
public class BairroService {
	
	
	@Autowired
	BairroMapper bairroMapper;
	
	@Autowired
	BairroRepository bairroRepository;

	public List<BairroDto> getListaBairros(Long orgao){
		return Optional.ofNullable(bairroMapper.fromResponseList(bairroRepository.findByOrgaoOrderByDescricaoAsc(orgao))).orElse(Collections.emptyList());
	}
	
	public BairroDto getBairro(Long codigoBairro) {
		return bairroMapper.toDto(bairroRepository.getById(codigoBairro));
	}
	
	public BairroDto salvar(BairroDto bairroDto) {
		return bairroMapper.toDto(bairroRepository.save(bairroMapper.toBairro(bairroDto)));
	}
	


}
