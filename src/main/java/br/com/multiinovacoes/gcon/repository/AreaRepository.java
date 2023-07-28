package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.Area;


@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
	
	public List<Area> findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(Long orgao, String descricao);
	
	public List<Area> findByOrgaoOrderByDescricaoAsc(Long orgao);
	
	@Query("SELECT coalesce(max(a.id), 0) FROM Area a")
	public Long getMaxId();



}
