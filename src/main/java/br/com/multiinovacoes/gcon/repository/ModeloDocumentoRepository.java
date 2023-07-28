package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.ModeloDocumento;

@Repository
public interface ModeloDocumentoRepository extends JpaRepository<ModeloDocumento, Long> {
	
	public List<ModeloDocumento> findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(Long orgao, String descricao);
	
	public List<ModeloDocumento> findByOrgaoOrderByDescricaoAsc(Long orgao);
	
	public List<ModeloDocumento> findByOrgaoAndTipoOrderByDescricaoAsc(Long orgao, Integer tipo);
	
	@Query("SELECT coalesce(max(m.id), 0) FROM ModeloDocumento m")
	public Long getMaxId();



}
