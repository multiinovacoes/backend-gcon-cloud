package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.OrigemManifestacaoDto;
import br.com.multiinovacoes.gcon.model.mapper.OrigemManifestacaoMapper;
import br.com.multiinovacoes.gcon.repository.OrigemManifestacaoRepository;

@Service
@Transactional
public class OrigemManifestacaoService {
	
	
	@Autowired
	OrigemManifestacaoMapper origemManifestacaoMapper;
	
	@Autowired
	OrigemManifestacaoRepository origemManifestacaoRepository;

	public List<OrigemManifestacaoDto> getListaOrigemManifestacaos(Long orgao){
		return Optional.ofNullable(origemManifestacaoMapper.fromResponseList(origemManifestacaoRepository.findByOrgaoOrderByDescricaoAsc(orgao))).orElse(Collections.emptyList());
	}
	
	public OrigemManifestacaoDto getOrigemManifestacao(Long codigoOrigemManifestacao) {
		return origemManifestacaoMapper.toDto(origemManifestacaoRepository.getById(codigoOrigemManifestacao));
	}
	
	public OrigemManifestacaoDto salvarOrigemManifestacao(OrigemManifestacaoDto origemManifestacaoDto) {
		if (origemManifestacaoDto.getId() == null) {
			origemManifestacaoDto.setId(origemManifestacaoRepository.getMaxId()+1);
		}
		return origemManifestacaoMapper.toDto(origemManifestacaoRepository.save(origemManifestacaoMapper.toOrigemManifestacao(origemManifestacaoDto)));
	}
	
	public List<OrigemManifestacaoDto> getPesquisaOrigemManifestacaoDescricao(String descricao){
		return Optional.ofNullable(origemManifestacaoMapper.fromResponseList(origemManifestacaoRepository.findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(descricao))).orElse(Collections.emptyList());
	}


}
