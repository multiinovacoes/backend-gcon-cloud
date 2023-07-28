package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.PerguntaSatisfacao;

@Repository
public interface PerguntaSatisfacaoRepository extends JpaRepository<PerguntaSatisfacao, Long> {
	
	public List<PerguntaSatisfacao> findByOrgaoAndStatus(Long orgao, Integer status);


}
