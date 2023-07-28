package br.com.multiinovacoes.gcon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.multiinovacoes.gcon.model.AtendimentoLai;

public interface AtendimentoLaiRepository extends JpaRepository<AtendimentoLai, Long>, AtendimentoLaiQueries {
	
	
	@Query("SELECT count(a.id) FROM AtendimentoLai a where a.orgao = ?1 and status <> 2 "
			+ "and statusAtendimento = 0 and (dataConclusao = '1969-12-31 21:00:00.000' or dataConclusao is null)"
			+ "and (select count(e.id) from EncaminhamentoLai e where e.atendimento = a.id and e.status = 0) = 0")
	public Integer getNovosAtendimentosLai(Long orgao);
	
	@Query("SELECT count(a.id) FROM AtendimentoLai a INNER JOIN RecursoLai r ON a.id = r.atendimento and a.orgao = ?1 and status <> 2 "
			+ "and a.statusAtendimento = 1 and r.status = ?2 and r.tipo = ?3 and r.dataPrazo > getDate() ")
	public Integer getAtendimentosLaiRecursoAberto(Long orgao, Integer statusRecurso, Integer tipoRecurso);

	@Query("SELECT count(a.id) FROM AtendimentoLai a where a.orgao = ?1 and status <> 2 "
			+ "and statusAtendimento = 0 and dataPrazo < getDate() and (dataConclusao = '1969-12-31 21:00:00.000' or dataConclusao is null)")
	public Integer getAtendimentosVencidosLai(Long orgao);

	@Query("SELECT coalesce(max(a.id), 0) FROM AtendimentoLai a")
	public Long getMaxId();
	
	@Query("SELECT count(a.id) FROM AtendimentoLai a INNER JOIN RecursoLai r ON a.id = r.atendimento and a.orgao = ?1 and status <> 2 "
			+ " and r.dataPrazo < getDate() and r.status = ?2 and r.tipo = ?3")
	public Integer getAtendimentosRecursosVencidosLai(Long orgao, Integer statusRecurso, Integer tipoRecurso);



}
