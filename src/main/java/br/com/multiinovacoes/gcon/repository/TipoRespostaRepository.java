package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.TipoResposta;

@Repository
public interface TipoRespostaRepository extends JpaRepository<TipoResposta, Long> {
	
	public List<TipoResposta> findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(String descricao);
	
	public List<TipoResposta> findByOrgaoOrderByDescricaoAsc(Long orgao);
	
	@Query("SELECT coalesce(max(t.id), 0) FROM TipoResposta t")
	public Long getMaxId();


}
