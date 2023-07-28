package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.ModeloDocumentoLai;

@Repository
public interface ModeloDocumentoLaiRepository extends JpaRepository<ModeloDocumentoLai, Long> {
	
	public List<ModeloDocumentoLai> findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(Long orgao, String descricao);
	
	public List<ModeloDocumentoLai> findByOrgaoOrderByDescricaoAsc(Long orgao);
	
	public List<ModeloDocumentoLai> findByOrgaoAndTipoOrderByDescricaoAsc(Long orgao, Integer tipo);


}
