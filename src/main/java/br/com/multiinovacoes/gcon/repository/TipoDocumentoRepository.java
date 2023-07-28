package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.TipoDocumento;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
	
	public List<TipoDocumento> findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(String descricao);
	
	public List<TipoDocumento> findByOrgao(Long orgao);
	
	public TipoDocumento findByOrgaoAndId(Long orgao, Long id);
	
		@Query("SELECT coalesce(max(t.id), 0) FROM TipoDocumento t")
	public Long getMaxId();


}
