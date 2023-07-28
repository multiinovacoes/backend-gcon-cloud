package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.dto.TipoManifestanteDto;
import br.com.multiinovacoes.gcon.model.mapper.TipoManifestanteMapper;
import br.com.multiinovacoes.gcon.repository.TipoManifestanteRepository;

@Service
@Transactional
public class TipoManifestanteService {
	
	
	@Autowired
	TipoManifestanteMapper tipoManifestanteMapper;
	
	@Autowired
	TipoManifestanteRepository tipoManifestanteRepository;

	public List<TipoManifestanteDto> getListaTipoManifestantes(Long orgao){
		return Optional.ofNullable(tipoManifestanteMapper.fromResponseList(tipoManifestanteRepository.findByOrgaoAndStatusOrderByDescricaoAsc(orgao, 0))).orElse(Collections.emptyList());
	}

	public List<TipoManifestanteDto> getListaTipoManifestantesSite(Long orgao){
		return Optional.ofNullable(tipoManifestanteMapper.fromResponseList(tipoManifestanteRepository.findByOrgaoAndStatusOrderByDescricaoAsc(orgao, 0))).orElse(Collections.emptyList());
	}

	public TipoManifestanteDto getTipoManifestante(Long codigoTipoManifestante) {
		return tipoManifestanteMapper.toDto(tipoManifestanteRepository.getById(codigoTipoManifestante));
	}
	
	public TipoManifestanteDto salvarTipoManifestante(TipoManifestanteDto tipoManifestanteDto) {
		if (tipoManifestanteDto.getId() == null) {
			tipoManifestanteDto.setId(tipoManifestanteRepository.getMaxId()+1);
			tipoManifestanteDto.setIdAntigo(0l);
		}
		return tipoManifestanteMapper.toDto(tipoManifestanteRepository.save(tipoManifestanteMapper.toTipoManifestante(tipoManifestanteDto)));
	}
	
	public List<TipoManifestanteDto> getPesquisaTipoManifestanteDescricao(Long orgao, String descricao){
		return Optional.ofNullable(tipoManifestanteMapper.fromResponseList(tipoManifestanteRepository.findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(orgao, descricao))).orElse(Collections.emptyList());
	}


}
