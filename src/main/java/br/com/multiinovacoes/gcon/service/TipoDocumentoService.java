package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.TipoDocumentoDto;
import br.com.multiinovacoes.gcon.model.mapper.TipoDocumentoMapper;
import br.com.multiinovacoes.gcon.repository.TipoDocumentoRepository;

@Service
@Transactional
public class TipoDocumentoService {
	
	
	@Autowired
	TipoDocumentoMapper tipoDocumentoMapper;
	
	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	public List<TipoDocumentoDto> getListaTipoDocumentos(Long orgao){
		return Optional.ofNullable(tipoDocumentoMapper.fromResponseList(tipoDocumentoRepository.findByOrgao(orgao))).orElse(Collections.emptyList());
	}
	
	public TipoDocumentoDto getTipoDocumento(Long orgao, Long codigoTipoDocumento) {
		return tipoDocumentoMapper.toDto(tipoDocumentoRepository.findByOrgaoAndId(orgao, codigoTipoDocumento));
	}
	
	public TipoDocumentoDto salvarTipoDocumento(TipoDocumentoDto tipoDocumentoDto) {
		if (tipoDocumentoDto.getId() == null) {
			tipoDocumentoDto.setId(tipoDocumentoRepository.getMaxId()+1);
			tipoDocumentoDto.setMascara("");
			tipoDocumentoDto.setIdAntigo(0l);
		}
		return tipoDocumentoMapper.toDto(tipoDocumentoRepository.save(tipoDocumentoMapper.toTipoDocumento(tipoDocumentoDto)));
	}
	
	public List<TipoDocumentoDto> getPesquisaTipoDocumentoDescricao(String descricao){
		return Optional.ofNullable(tipoDocumentoMapper.fromResponseList(tipoDocumentoRepository.findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(descricao))).orElse(Collections.emptyList());
	}


}
