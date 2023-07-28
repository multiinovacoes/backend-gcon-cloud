package br.com.multiinovacoes.gcon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.EncaminhamentoLai;


@Repository
public interface EncaminhamentoLaiRepository extends JpaRepository<EncaminhamentoLai, Long>, EncaminhamentoLaiQueries {
	
	
	public List<EncaminhamentoLai> findByAtendimento(Long atendimento);
	
	public List<EncaminhamentoLai> findByAtendimentoAndStatus(Long atendimento, Integer status);
	
	@Query("SELECT coalesce(max(e.id), 0) FROM EncaminhamentoLai e")
	public Long getMaxId();
	
	public EncaminhamentoLai findByAtendimentoAndSetorDestinoAndStatus(Long atendimento, Long setorDestino, Integer status);
	
	@Query("SELECT count(e.id) FROM EncaminhamentoLai e inner join AtendimentoLai a on a.id = e.atendimento "
			+ " left join EncaminhamentoRespostaLai r on e.id = r.encaminhamento "
			+ " where a.orgao = ?1 and a.status = 0 and e.status = 0"
			+ " and r.id is NOT null ")
	public Integer getEncaminhamentosRecebidos(Long orgao);
	
	@Query("SELECT count(e.id) FROM EncaminhamentoLai e inner join AtendimentoLai a on a.id = e.atendimento "
			+ " left join EncaminhamentoRespostaLai r on e.id = r.encaminhamento "
			+ " where a.orgao = ?1 and a.status = 0 and e.status = 0"
			+ " and r.id is null and e.dataPrazoResposta > getDate()")
	public Integer getEncaminhamentosEnviados(Long orgao);
	
	@Query("SELECT e FROM EncaminhamentoLai e inner join AtendimentoLai a on a.id = e.atendimento "
			+ " left join EncaminhamentoRespostaLai r on e.id = r.encaminhamento "
			+ " where a.orgao = ?1 and a.status = 0 and e.status = 0"
			+ " and r.id is null and e.dataPrazoResposta > getDate()")
	public List<EncaminhamentoLai> getListaEncaminhamentosEnviados(Long orgao); 

	@Query("SELECT count(e.id) FROM EncaminhamentoLai e inner join AtendimentoLai a on a.id = e.atendimento "
			+ " left join EncaminhamentoRespostaLai r on e.id = r.encaminhamento "
			+ " where a.orgao = ?1 and a.status = 0 and e.status = 0 and e.dataPrazoResposta < getDate()"
			+ " and r.id is null ")
	public Integer getEncaminhamentosEnviadosVencidos(Long orgao);


	public EncaminhamentoLai findByParametro(String parametro);
	
	@Query("SELECT e FROM EncaminhamentoLai e inner join AtendimentoLai a on a.id = e.atendimento "
			+ " left join EncaminhamentoRespostaLai r on e.id = r.encaminhamento "
			+ " where a.id = ?1 and a.status = 0 and e.status = 0")
	public Optional<EncaminhamentoLai> getVerificarEncaminhamentoAberto(Long codigoAtendimento);



}
