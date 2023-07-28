package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.AssuntoLai;


@Repository
public interface AssuntoLaiRepository extends JpaRepository<AssuntoLai, Long> {
	
	public List<AssuntoLai> findByOrgaoAndDescricaoContainingIgnoreCase(Long orgao, String descricao);
	
	public List<AssuntoLai> findByAreaOrderByDescricaoAsc(Long area);
	
	public List<AssuntoLai> findByOrgao(Long orgao);
	
	public List<AssuntoLai> findByOrgaoAndAreaAndStatusOrderByDescricaoAsc(Long orgao, Long area, Integer status);
	
	public List<AssuntoLai> findByOrgaoAndAreaOrderByDescricaoAsc(Long orgao, Long area);
}
