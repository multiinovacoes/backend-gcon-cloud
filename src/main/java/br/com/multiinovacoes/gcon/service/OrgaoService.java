package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.OrgaoDto;
import br.com.multiinovacoes.gcon.model.mapper.OrgaoMapper;
import br.com.multiinovacoes.gcon.repository.OrgaoRepository;

@Service
@Transactional
public class OrgaoService {
	
	@Autowired
	OrgaoMapper orgaoMapper;
	
	@Autowired
	OrgaoRepository orgaoRepository;

	public List<OrgaoDto> getListaOrgaos(){
		return Optional.ofNullable(orgaoMapper.fromResponseList(orgaoRepository.findAll(Sort.by(Sort.Direction.ASC, "descricao")))).orElse(Collections.emptyList());
	}

	public List<OrgaoDto> getListaOrgaosAtivos(Integer status){
		return Optional.ofNullable(orgaoMapper.fromResponseList(orgaoRepository.findByStatusOrderByDescricaoAsc(status))).orElse(Collections.emptyList());
	}

	public OrgaoDto getOrgao(Long codigoOrgao) {
		return orgaoMapper.toDto(orgaoRepository.getById(codigoOrgao));
	}
	
	public OrgaoDto salvarOrgao(OrgaoDto orgaoDto) {
		return orgaoMapper.toDto(orgaoRepository.save(orgaoMapper.toOrgao(orgaoDto)));
	}
	
	public List<OrgaoDto> getPesquisaOrgaoDescricao(String descricao){
		return Optional.ofNullable(orgaoMapper.fromResponseList(orgaoRepository.findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(descricao))).orElse(Collections.emptyList());
	}


}
