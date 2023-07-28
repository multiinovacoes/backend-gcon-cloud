package br.com.multiinovacoes.gcon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.EncaminhamentoResposta;


@Repository
public interface EncaminhamentoRespostaRepository extends JpaRepository<EncaminhamentoResposta, Long>, EncaminhamentoRespostaQueries {
	
	
	public EncaminhamentoResposta findByEncaminhamento(Long encaminhamento);
	
	@Query("SELECT coalesce(max(er.id), 0) FROM EncaminhamentoResposta er")
	public Long getMaxId();
	
	public EncaminhamentoResposta findByEncaminhamentoAndSatisfaz(Long encaminhamento, Integer satisfaz);



}
