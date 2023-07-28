package br.com.multiinovacoes.gcon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.Configuracao;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {
	
	public Configuracao findByOrgao(Long orgao);
	
	@Query("SELECT coalesce(max(c.id), 0) FROM Configuracao c")
	public Long getMaxId();



}
