package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.EmpresaDto;
import br.com.multiinovacoes.gcon.model.mapper.EmpresaMapper;
import br.com.multiinovacoes.gcon.repository.EmpresaRepository;

@Service
@Transactional
public class EmpresaService {
	
	
	@Autowired
	EmpresaMapper empresaMapper;
	
	@Autowired
	EmpresaRepository empresaRepository;

	public List<EmpresaDto> getListaEmpresas(Long orgao){
		return Optional.ofNullable(empresaMapper.fromResponseList(empresaRepository.findByOrgaoOrderByDescricaoAsc(orgao))).orElse(Collections.emptyList());
	}
	
	public EmpresaDto getEmpresa(Long codigoEmpresa) {
		return empresaMapper.toDto(empresaRepository.getById(codigoEmpresa));
	}
	
	public EmpresaDto salvar(EmpresaDto empresaDto) {
		return empresaMapper.toDto(empresaRepository.save(empresaMapper.toEmpresa(empresaDto)));
	}
	


}
