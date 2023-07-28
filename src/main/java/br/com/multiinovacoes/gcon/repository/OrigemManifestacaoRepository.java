package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.OrigemManifestacao;

@Repository
public interface OrigemManifestacaoRepository extends JpaRepository<OrigemManifestacao, Long> {
	
	public List<OrigemManifestacao> findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(String descricao);
	
	public List<OrigemManifestacao> findByOrgaoOrderByDescricaoAsc(Long orgao);
	
	@Query("SELECT coalesce(max(o.id), 0) FROM OrigemManifestacao o")
	public Long getMaxId();


}
