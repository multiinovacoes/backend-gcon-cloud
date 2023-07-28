package br.com.multiinovacoes.gcon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.multiinovacoes.gcon.model.RecursoLai;

public interface RecursoLaiRepository extends JpaRepository<RecursoLai, Long> {
	
	public List<RecursoLai> findByAtendimento(Long codigoAtendimento);
	
	public List<RecursoLai> findByAtendimentoAndStatus(Long codigoAtendimento, Integer status);
	
	public Optional<RecursoLai> findByAtendimentoAndTipo(Long codigoAtendimento, Integer tipo);
	
	@Query("SELECT r FROM RecursoLai r INNER JOIN AtendimentoLai a ON a.id = r.atendimento and a.orgao = ?1 and status <> 2 "
			+ "and a.statusAtendimento = 2 and r.status = ?2 and r.dataPrazo > getDate() ")
	public List<RecursoLai> getListaRecursoAberto(Long orgao, Integer statusRecurso);

	public RecursoLai findByParametro(String parametro);



}
