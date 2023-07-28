package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.AreaLai;


@Repository
public interface AreaLaiRepository extends JpaRepository<AreaLai, Long> {
	
	public List<AreaLai> findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(Long orgao, String descricao);
	
	public List<AreaLai> findByOrgaoOrderByDescricaoAsc(Long orgao);
	
	public List<AreaLai> findByOrgaoAndInstituicaoOrderByDescricaoAsc(Long orgao, Long instituicao);


}
