package br.com.multiinovacoes.gcon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.PesquisaSatisfacao;

@Repository
public interface PesquisaSatisfacaoRepository extends JpaRepository<PesquisaSatisfacao, Long> {
	
	@Query("SELECT coalesce(max(p.id), 0) FROM PesquisaSatisfacao p")
	public Long getMaxId();



}
