package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long>, SetorQueries {

	public List<Setor> findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(Long orgao, String descricao);

	public List<Setor> findByOrgaoAndStatusAndIdNotInOrderByDescricaoAsc(Long orgao, Integer status, List<Long> idList);

	public List<Setor> findByIdInOrderByDescricaoAsc(List<Long> idList);
	
	public List<Setor> findByOrgaoOrderByDescricaoAsc(Long orgao);
	
	public List<Setor> findByOrgaoAndStatusOrderByDescricaoAsc(Long orgao, Integer status);
	
	@Query("SELECT coalesce(max(s.id), 0) FROM Setor s")
	public Long getMaxId();


}
