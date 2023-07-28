package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.TipoManifestante;

@Repository
public interface TipoManifestanteRepository extends JpaRepository<TipoManifestante, Long> {
	
	public List<TipoManifestante> findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(Long orgao, String descricao);
	
	public List<TipoManifestante> findByOrgaoAndStatusOrderByDescricaoAsc(Long orgao, Integer status);
	
	public List<TipoManifestante> findByOrgaoAndAtivoOrderByIdAsc(Long orgao, Integer ativo);
	
	@Query("SELECT t FROM TipoManifestante t where t.orgao = ?1 and t.status = ?2 and t.descricao <> 'Anônimo' and t.descricao <> 'Anonimo' order by t.descricao")
	public List<TipoManifestante> getTiposManifestantes(Long orgao, Integer status);
	
	public TipoManifestante findByOrgaoAndIdAndStatus(Long orgao, Long idTipoManifestante, Integer status);
	
	@Query("SELECT coalesce(max(t.id), 0) FROM TipoManifestante t")
	public Long getMaxId();


}
