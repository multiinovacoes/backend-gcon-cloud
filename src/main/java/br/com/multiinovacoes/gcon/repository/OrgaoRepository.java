package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.Orgao;

@Repository
public interface OrgaoRepository extends JpaRepository<Orgao, Long>, OrgaoQueries {
	
	public List<Orgao> findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(String descricao);
	
	public List<Orgao> findByStatusOrderByDescricaoAsc(Integer status);

}
