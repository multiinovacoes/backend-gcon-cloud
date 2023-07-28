package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.RespostaSatisfacao;

@Repository
public interface RespostaSatisfacaoRepository extends JpaRepository<RespostaSatisfacao, Long> {
	
	public List<RespostaSatisfacao> findByPerguntaSatisfacaoAndStatus(Long pergunta, Integer status);


}
