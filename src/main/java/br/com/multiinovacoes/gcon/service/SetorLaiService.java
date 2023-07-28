package br.com.multiinovacoes.gcon.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.EncaminhamentoLai;
import br.com.multiinovacoes.gcon.model.dto.SetorLaiDto;
import br.com.multiinovacoes.gcon.model.mapper.SetorLaiMapper;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.SetorLaiRepository;

@Service
@Transactional
public class SetorLaiService {
	
	
	@Autowired
	SetorLaiMapper setorMapper;
	  
	@Autowired
	SetorLaiRepository setorRepository;
	
	@Autowired
	EncaminhamentoLaiRepository encaminhamentoRepository;

	public List<SetorLaiDto> getListaSetores(Long orgao){
		return Optional.ofNullable(setorMapper.fromResponseList(setorRepository.findByOrgaoOrderByDescricaoAsc(orgao))).orElse(Collections.emptyList());
	}

	public List<SetorLaiDto> getListaSetores(Long orgao, Long atendimento){
		List<EncaminhamentoLai> listaEnc = encaminhamentoRepository.findByAtendimentoAndStatus(atendimento, 0);
		List<Long> idSetores = new ArrayList<>(listaEnc.size());
		for (EncaminhamentoLai encaminhamento : listaEnc) {
			idSetores.add(encaminhamento.getSetorDestino());
		}
		if (listaEnc.isEmpty()) {
			return Optional.ofNullable(setorMapper.fromResponseList(setorRepository.findByOrgaoAndStatusAndTipoAcessoPerfilOrderByDescricaoAsc(orgao, 0, 1))).orElse(Collections.emptyList());
		}else {
			return Optional.ofNullable(setorMapper.fromResponseList(setorRepository.findByOrgaoAndStatusAndTipoAcessoPerfilAndIdNotInOrderByDescricaoAsc(orgao, 0, 1, idSetores))).orElse(Collections.emptyList());
		}
	}
	 
	public List<SetorLaiDto> getSetoresDestino(Long codigo) {
		List<EncaminhamentoLai> listaEnc = encaminhamentoRepository.findByAtendimentoAndStatus(codigo, 0);
		List<Long> idSetores = new ArrayList<>(listaEnc.size());
		for (EncaminhamentoLai encaminhamento : listaEnc) {
			idSetores.add(encaminhamento.getSetorDestino());
		}
		return Optional.ofNullable(setorMapper.fromResponseList(setorRepository.findByIdInOrderByDescricaoAsc(idSetores))).orElse(Collections.emptyList());
	}

	
	public SetorLaiDto getSetor(Long codigoSetor) {
		return setorMapper.toDto(setorRepository.getById(codigoSetor));
	}
	
	public SetorLaiDto salvarSetor(SetorLaiDto setorDto) {
		if (setorDto.getId() == null) {
			setorDto.setDataCriacao(LocalDateTime.now());
		}
		return setorMapper.toDto(setorRepository.save(setorMapper.toSetor(setorDto)));
	}
	
	public List<SetorLaiDto> getPesquisaSetorDescricao(Long orgao, String descricao){
		return Optional.ofNullable(setorMapper.fromResponseList(setorRepository.findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(orgao, descricao))).orElse(Collections.emptyList());
	}


}
