package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.LogAtendimento;


@Repository
public interface LogAtendimentoRepository extends JpaRepository<LogAtendimento, Long> {
	
	
	@Query("SELECT coalesce(max(l.id), 0) FROM LogAtendimento l")
	public Long getMaxId();
	
	public List<LogAtendimento> findByAtendimento(Long atendimento);


}
