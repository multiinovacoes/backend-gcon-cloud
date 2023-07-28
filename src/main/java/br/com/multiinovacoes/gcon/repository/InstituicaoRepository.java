package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.Instituicao;


@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
	
	public List<Instituicao> findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(Long orgao, String descricao);
	
	public List<Instituicao> findByOrgaoOrderByDescricaoAsc(Long orgao);
	
	@Query("SELECT coalesce(max(i.id), 0) FROM Instituicao i")
	public Long getMaxId();



}
