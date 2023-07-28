package br.com.multiinovacoes.gcon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.EncaminhamentoRespostaLai;


@Repository
public interface EncaminhamentoRespostaLaiRepository extends JpaRepository<EncaminhamentoRespostaLai, Long>, EncaminhamentoRespostaLaiQueries {
	
	
	public EncaminhamentoRespostaLai findByEncaminhamento(Long encaminhamento);
	
	public EncaminhamentoRespostaLai findByEncaminhamentoAndSatisfaz(Long encaminhamento, Integer satisfaz);



}
