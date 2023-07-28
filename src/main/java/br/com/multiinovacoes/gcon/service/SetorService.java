package br.com.multiinovacoes.gcon.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.Encaminhamento;
import br.com.multiinovacoes.gcon.model.dto.SetorDto;
import br.com.multiinovacoes.gcon.model.mapper.SetorMapper;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoRepository;
import br.com.multiinovacoes.gcon.repository.SetorRepository;

@Service
@Transactional
public class SetorService {
	
	
	@Autowired
	SetorMapper setorMapper;
	  
	@Autowired
	SetorRepository setorRepository;
	
	@Autowired
	EncaminhamentoRepository encaminhamentoRepository;

	public List<SetorDto> getListaSetores(Long orgao){
		return Optional.ofNullable(setorMapper.fromResponseList(setorRepository.findByOrgaoOrderByDescricaoAsc(orgao))).orElse(Collections.emptyList());
	}

	public List<SetorDto> getListaSetores(Long orgao, Long atendimento){
		List<Encaminhamento> listaEnc = encaminhamentoRepository.findByAtendimentoAndStatusAndCancelado(atendimento, Encaminhamento.STATUS_ABERTO, 0);
		List<Long> idSetores = new ArrayList<>(listaEnc.size());
		for (Encaminhamento encaminhamento : listaEnc) {
			idSetores.add(encaminhamento.getSetorDestino());
		}
		if (listaEnc.isEmpty()) {
			return Optional.ofNullable(setorMapper.fromResponseList(setorRepository.findByOrgaoAndStatusOrderByDescricaoAsc(orgao, 0))).orElse(Collections.emptyList());
		}else {
			return Optional.ofNullable(setorMapper.fromResponseList(setorRepository.findByOrgaoAndStatusAndIdNotInOrderByDescricaoAsc(orgao, 0, idSetores))).orElse(Collections.emptyList());
		}
	}
	 
	public List<SetorDto> getSetoresDestino(Long codigo) {
		List<Encaminhamento> listaEnc = encaminhamentoRepository.findByAtendimentoAndStatusAndCancelado(codigo, Encaminhamento.STATUS_ABERTO, 0);
		List<Long> idSetores = new ArrayList<>(listaEnc.size());
		for (Encaminhamento encaminhamento : listaEnc) {
			idSetores.add(encaminhamento.getSetorDestino());
		}
		return Optional.ofNullable(setorMapper.fromResponseList(setorRepository.findByIdInOrderByDescricaoAsc(idSetores))).orElse(Collections.emptyList());
	}

	
	public SetorDto getSetor(Long codigoSetor) {
		return setorMapper.toDto(setorRepository.getById(codigoSetor));
	}
	
	public SetorDto salvarSetor(SetorDto setorDto) {
		if (setorDto.getId() == null) {
			setorDto.setId(setorRepository.getMaxId()+1);
			setorDto.setDataCriacao(LocalDateTime.now());
		}
		return setorMapper.toDto(setorRepository.save(setorMapper.toSetor(setorDto)));
	}
	
	public List<SetorDto> getPesquisaSetorDescricao(Long orgao, String descricao){
		return Optional.ofNullable(setorMapper.fromResponseList(setorRepository.findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(orgao, descricao))).orElse(Collections.emptyList());
	}


}
