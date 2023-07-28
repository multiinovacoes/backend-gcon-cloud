package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long>, AtendimentoQueries, RelatorioQueries{
		
	public Atendimento findByNumeroProtocolo(String numeroProtocolo);
	
	public List<Atendimento> findByOrgao(Long orgao);
	
	public List<Atendimento> findByOrgaoAndDescricaoContainingIgnoreCase(Long orgao, String descricao);
	
	public List<Atendimento> findByOrgaoAndNumeroDocumento(Long orgao, String numeroDocumento);
	
	public List<Atendimento> findByParametroHash(String parametroHash);
	
	public List<Atendimento> findByOrgaoAndNumeroProtocoloAndSenhaManifestante(Long orgao, String numeroProtocolo, String senhaManifestante);
	
	public List<Atendimento> findByOrgaoAndAnoAtendimentoAndSequencialOrgaoAndSenhaManifestante(Long orgao, Integer ano, Long numeroAtendimento, String senhaManifestante);

	@Query("SELECT coalesce(max(a.sequencialOrgao), 0) FROM Atendimento a where a.anoAtendimento = ?1 and a.orgao = ?2")
	public Long getMaxSequencialOrgao(Integer ano, Long orgao);
	
	@Query("SELECT coalesce(max(a.numeroAtendimento), 0) FROM Atendimento a where a.anoAtendimento = ?1")
	public Long getMaxNumeroAtendimento(Integer ano);

	@Query("SELECT coalesce(max(a.sequencialOrgao), 0) FROM Atendimento a where a.anoAtendimento = ?1 and a.orgao = ?2")
	public Long getMaxAnoAtendimento(Integer ano, Long orgao);

	@Query("SELECT count(a.id) FROM Atendimento a where a.orgao = ?1 and status <> 2 "
			+ "and statusAtendimento = 0 and (dataConclusao = '1969-12-31 21:00:00.000' or dataConclusao is null)"
			+ "and (select count(e.id) from Encaminhamento e where e.atendimento = a.id and e.status = 0) = 0")
	public Integer getNovosAtendimentos(Long orgao);

	@Query("SELECT count(a.id) FROM Atendimento a where a.orgao = ?1 and status <> 2 "
			+ "and statusAtendimento = 0 and (dataConclusao = '1969-12-31 21:00:00.000' or dataConclusao is null) and natureza = ?2")
	public Integer getCanalDenuncias(Long orgao, Long natureza);

	@Query("SELECT count(a.id) FROM Atendimento a where a.orgao = ?1 and a.status <> ?2 "
			+ "and month(a.dataCriacao) = ?3 and year(a.dataCriacao) = ?4")
	public Integer getQtdAtendimentosMes(Long orgao, Integer status, Integer mes, Integer ano);

	@Query("SELECT count(a.id) FROM Atendimento a where a.orgao = ?1 and a.status <> 2 "
			+ " and year(a.dataCriacao) = ?2 ")
	public Integer getQtdAtendimentosAno(Long orgao, Integer ano);

	@Query("SELECT count(a.id) FROM Atendimento a where a.orgao = ?1 and a.status = 1 "
			+ "and month(a.dataCriacao) = ?2 and year(a.dataCriacao) = ?3 and a.statusAtendimento = 1")
	public Integer getQtdAtendimentosMesConcluidos(Long orgao, Integer mes, Integer ano);

	@Query("SELECT count(a.id) FROM Atendimento a where a.orgao = ?1 and a.status = 1 "
			+ " and year(a.dataCriacao) = ?2 and a.statusAtendimento = 1")
	public Integer getQtdAtendimentosConcluidos(Long orgao, Integer ano);

	@Query("SELECT count(a.id) FROM Atendimento a where a.orgao = ?1 and a.status <> 2 "
			+ " and year(a.dataCriacao) = ?2 and (dataConclusao = '1969-12-31 21:00:00.000' or dataConclusao is null) ")
	public Integer getQtdAtendimentosAnoNaoConcluidos(Long orgao, Integer ano);

	@Query("SELECT count(a.id) FROM Atendimento a where a.orgao = ?1 and a.status <> ?2 "
			+ " and month(a.dataCriacao) = ?3 and year(a.dataCriacao) = ?4 and (dataConclusao = '1969-12-31 21:00:00.000' or dataConclusao is null) ")
	public Integer getQtdAtendimentosMesNaoConcluidos(Long orgao, Integer status, Integer mes, Integer ano);

	@Query("SELECT count(a.id) FROM Atendimento a where a.orgao = ?1 and status = 0 "
			+ "and statusAtendimento = 0 AND a.dataPrazo < DATEADD(day, DATEDIFF(day, 0, GETDATE()), 0)")
	public Integer getAtendimentosVencidos(Long orgao);
	
	@Query("SELECT count(a.id) FROM Atendimento a where a.orgao = ?1 and status = 0 "
			+ " AND a.dataPrazo >= DATEADD(day, DATEDIFF(day, 0, GETDATE()), 0) and DATEDIFF(day, getDate(), a.dataPrazo) <= ?2")
	public Integer getAtendimentosVencer(Long orgao, Integer dias);
	
	
	@Query("SELECT a FROM Atendimento a where a.orgao = ?1 and a.status <> 2 "
			+ "and a.statusAtendimento = 0 and (a.dataConclusao = '1969-12-31 21:00:00.000' or a.dataConclusao is null) and a.area is not null and a.assunto is not null "
			+ "and (select count(e.id) from Encaminhamento e where e.atendimento = a.id and e.status = 0) = 0")
	public List<Atendimento> getAtendimentosNaoEnviados(Long orgao);
	

}
