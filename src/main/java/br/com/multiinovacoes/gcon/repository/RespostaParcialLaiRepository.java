package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.RespostaParcialLai;

@Repository
public interface RespostaParcialLaiRepository extends JpaRepository<RespostaParcialLai, Long>, RespostaParcialLaiQueries {
	
	public List<RespostaParcialLai> findByAtendimentoAndStatus(Long atendimento, Integer status);
	
	public List<RespostaParcialLai> findByAtendimento(Long atendimento);


}
