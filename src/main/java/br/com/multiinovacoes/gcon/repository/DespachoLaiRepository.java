package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.DespachoLai;


@Repository
public interface DespachoLaiRepository extends JpaRepository<DespachoLai, Long>, DespachoLaiQueries {
	
	public List<DespachoLai> findByAtendimentoOrderById(Long atendimento);
	
	@Query("SELECT coalesce(max(d.id), 0) FROM DespachoLai d")
	public Long getMaxId();


}
