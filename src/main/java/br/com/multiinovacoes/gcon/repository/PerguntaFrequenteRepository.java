package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.PerguntaFrequente;


@Repository
public interface PerguntaFrequenteRepository extends JpaRepository<PerguntaFrequente, Long> {
	
	public List<PerguntaFrequente> findByOrgaoOrderByPerguntaAsc(Long orgao);

	@Query("SELECT coalesce(max(a.id), 0) FROM PerguntaFrequente a")
	public Long getMaxSequencialId();


}
