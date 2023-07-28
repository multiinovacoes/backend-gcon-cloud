package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.Despacho;


@Repository
public interface DespachoRepository extends JpaRepository<Despacho, Long>, DespachoQueries {
	
	public List<Despacho> findByAtendimentoOrderById(Long atendimento);
	
	@Query("SELECT coalesce(max(d.id), 0) FROM Despacho d")
	public Long getMaxId();


}
