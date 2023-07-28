package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.RespostaParcial;

@Repository
public interface RespostaParcialRepository extends JpaRepository<RespostaParcial, Long>, RespostaParcialQueries {
	
	public List<RespostaParcial> findByAtendimentoAndCancelado(Long atendimento, Integer cancelado);
	
	@Query("SELECT coalesce(max(r.id), 0) FROM RespostaParcial r")
	public Long getMaxId();
	
	public List<RespostaParcial> findByCodigoAtendimentoAndAnoAtendimentoAndOrgao(Long codigoAtendimento, String ano, Long orgao);


}
