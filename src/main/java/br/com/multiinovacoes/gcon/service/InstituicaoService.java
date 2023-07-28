package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.InstituicaoDto;
import br.com.multiinovacoes.gcon.model.mapper.InstituicaoMapper;
import br.com.multiinovacoes.gcon.repository.InstituicaoRepository;

@Service
@Transactional
public class InstituicaoService {
	
	
	@Autowired
	InstituicaoMapper instituicaoMapper;
	
	@Autowired
	InstituicaoRepository instituicaoRepository;

	public List<InstituicaoDto> getListaInstituicoes(Long orgao){
		return Optional.ofNullable(instituicaoMapper.fromResponseList(instituicaoRepository.findByOrgaoOrderByDescricaoAsc(orgao))).orElse(Collections.emptyList());
	}
	
	public InstituicaoDto getInstituicao(Long codigoInstituicao) {
		return instituicaoMapper.toDto(instituicaoRepository.getById(codigoInstituicao));
	}
	
	public InstituicaoDto salvarInstituicao(InstituicaoDto instituicaoDto) {
		return instituicaoMapper.toDto(instituicaoRepository. save(instituicaoMapper.toInstituicao(instituicaoDto)));
	}
	
	public List<InstituicaoDto> getPesquisaInstituicaoDescricao(Long orgao, String descricao){
		return Optional.ofNullable(instituicaoMapper.fromResponseList(instituicaoRepository.findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(orgao, descricao))).orElse(Collections.emptyList());
	}


}
