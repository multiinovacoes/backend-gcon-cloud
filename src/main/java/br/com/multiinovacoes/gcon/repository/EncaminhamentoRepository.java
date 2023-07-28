package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.Encaminhamento;


@Repository
public interface EncaminhamentoRepository extends JpaRepository<Encaminhamento, Long>, EncaminhamentoQueries {
	
	
	public List<Encaminhamento> findByAtendimento(Long atendimento);
	
	public List<Encaminhamento> findByAtendimentoAndStatusAndCancelado(Long atendimento, Integer status, Integer cancelado);
	
	@Query("SELECT coalesce(max(e.id), 0) FROM Encaminhamento e")
	public Long getMaxId();
	
	public Encaminhamento findByAtendimentoAndSetorDestinoAndStatusAndCancelado(Long atendimento, Long setorDestino, Integer status, Integer cancelado);
	
	@Query("SELECT count(e.id) FROM Encaminhamento e inner join Atendimento a on a.id = e.atendimento left join EncaminhamentoResposta r "
			+ "on (e.id = r.encaminhamento) where a.orgao = ?1 and a.status = 0 "
			+ "and (a.dataConclusao = '1969-12-31 21:00:00.000' or a.dataConclusao = '1969-12-31 00:00:00.000' or a.dataConclusao is null)"
			+ "and e.status = 0 and e.cancelado = 0 and r.id is not null ")
	public Integer getEncaminhamentosRecebidos(Long orgao);

	public Encaminhamento findByParametro(String parametro);
	
	
	@Query("SELECT count(e.id) FROM Encaminhamento e inner join Atendimento a on a.id = e.atendimento "
			+ " left join EncaminhamentoResposta r on e.id = r.encaminhamento "
			+ " where a.orgao = ?1 and a.status = 0 and e.status = 0 and e.dataPrazo < DATEADD(day, DATEDIFF(day, 0, GETDATE()), 0)"
			+ " and r.id is null ")
	public Integer getEncaminhamentosVencidos(Long orgao);
	
	@Query("SELECT count(e.id) FROM Encaminhamento e inner join Atendimento a on a.id = e.atendimento "
			+ " left join EncaminhamentoResposta r on e.id = r.encaminhamento "
			+ " where a.orgao = ?1 and a.status = 0 and e.status = 0 and e.dataPrazo > DATEADD(day, DATEDIFF(day, 0, GETDATE()), 0)"
			+ " and r.id is null and DATEDIFF(day, GETDATE(), DAPRAZOENCAMINHAMENTO) = (SELECT qtdDiasVencer FROM Configuracao c WHERE c.orgao = ?1)")
	public Integer getEncaminhamentosVencer(Long orgao);
	
	
	@Query("SELECT e FROM Encaminhamento e inner join Atendimento a on a.id = e.atendimento "
			+ " left join EncaminhamentoResposta r on e.id = r.encaminhamento "
			+ " where a.orgao = ?1 and a.status = 0 and e.status = 0"
			+ " and r.id is null and e.dataPrazo > DATEADD(day, DATEDIFF(day, 0, GETDATE()), 0)")
	public List<Encaminhamento> getListaEncaminhamentosEnviados(Long orgao); 
	
	



}
