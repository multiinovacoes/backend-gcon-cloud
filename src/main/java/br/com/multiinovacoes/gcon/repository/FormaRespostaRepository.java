package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.FormaResposta;

@Repository
public interface FormaRespostaRepository extends JpaRepository<FormaResposta, Long> {
	
	public List<FormaResposta> findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(String descricao);


}
