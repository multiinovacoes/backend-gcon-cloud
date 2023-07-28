package br.com.multiinovacoes.gcon.repository.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.Encaminhamento;
import br.com.multiinovacoes.gcon.report.DadosEncaminhamento;
import br.com.multiinovacoes.gcon.report.DadosEncaminhamentoTratar;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoQueries;

public class EncaminhamentoRepositoryImpl implements EncaminhamentoQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public List<Encaminhamento> consultaAtendimento(Long codigoAtendimento) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Encaminhamento> criteria = builder.createQuery(Encaminhamento.class);
		Root<Encaminhamento> root = criteria.from(Encaminhamento.class);
		root.fetch("usuario", JoinType.LEFT);
		//root.fetch("setorOrigem", JoinType.LEFT);
		//root.fetch("setorDestino", JoinType.LEFT);
		Predicate predicates = builder.equal(root.get("atendimento"), codigoAtendimento);
		criteria.where(predicates);
		TypedQuery<Encaminhamento> query = manager.createQuery(criteria);
		
		return query.getResultList().isEmpty() ? null : query.getResultList();
	}

	@Override
	public Encaminhamento consultaEncaminhamento(Long codigoEncaminhamento) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Encaminhamento> criteria = builder.createQuery(Encaminhamento.class);
		Root<Encaminhamento> root = criteria.from(Encaminhamento.class);
		root.fetch("usuario", JoinType.LEFT);
		//root.fetch("setorOrigem", JoinType.LEFT);
		//root.fetch("setorDestino", JoinType.LEFT);
		Predicate predicates = builder.equal(root.get("id"), codigoEncaminhamento);
		criteria.where(predicates);
		TypedQuery<Encaminhamento> query = manager.createQuery(criteria);
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}

	@Override
	public String consultar(String campo, Long id) {
		Query query =  manager.createNativeQuery("SELECT CONVERT(nvarchar(30), " + campo + ", 103) FROM OUVIDORIA_ENCAMINHAMENTO_ENVIO WHERE INATENDIMENTOID = ? ORDER BY INCODIGOENCAMINHAMENTOENVIO DESC");
		query.setParameter(1, id);
	    @SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
        if (!lista.isEmpty()) {
        	return (String )lista.get(0);
        }
        else
        	return "Não encontrado";
	}

	@Override
	public List<Encaminhamento> consultaEncaminhamentosRecebidos(Long orgao, Pageable pageable, Integer totalRegistros) {
		
		String q = " SELECT e FROM Encaminhamento e inner join Atendimento a on a.id = e.atendimento left join EncaminhamentoResposta r " + 
				" on (e.id = r.encaminhamento) where a.orgao = ?1 and a.status = 0 " + 
				" and (a.dataConclusao = '1969-12-31 21:00:00.000' or a.dataConclusao = '1969-12-31 00:00:00.000' or a.dataConclusao is null) " + 
				" and e.status = 0 and e.cancelado = 0 and r.id is not null ";
		
		TypedQuery<Encaminhamento> query = manager.createQuery(q, Encaminhamento.class);
		query.setParameter(1, orgao);
		adicionarRestricoesDePaginacao(query, pageable);
		return  query.getResultList();
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Encaminhamento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	@Override
	public List<Encaminhamento> consultaEncaminhadosVencidos(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT e FROM Encaminhamento e inner join Atendimento a on a.id = e.atendimento "
				+ " left join EncaminhamentoResposta r on e.id = r.encaminhamento "
				+ " where a.orgao = :orgao and a.status = 0 and e.status = 0"
				+ " and r.id is null and e.dataPrazo < getDate()";

		TypedQuery<Encaminhamento> query = manager.createQuery(q, Encaminhamento.class);
		query.setParameter("orgao", orgao);
		adicionarRestricoesDePaginacao(query, pageable);
		
		List<Encaminhamento> lista = query.getResultList();
		return lista;
	}

	@Override
	public List<Encaminhamento> consultaEncaminhamentosVencer(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT e FROM Encaminhamento e inner join Atendimento a on a.id = e.atendimento "
				+ " left join EncaminhamentoResposta r on e.id = r.encaminhamento "
				+ " where a.orgao = :orgao and a.status = 0 and e.status = 0"
				+ " and r.id is null and e.dataPrazo > getDate() and DATEDIFF(day, GETDATE(), e.dataPrazo) = (SELECT qtdDiasVencer FROM Configuracao c WHERE c.orgao = :orgao)";

		TypedQuery<Encaminhamento> query = manager.createQuery(q, Encaminhamento.class);
		query.setParameter("orgao", orgao);
		adicionarRestricoesDePaginacao(query, pageable);
		
		List<Encaminhamento> lista = query.getResultList();
		return lista;
	}
	
	
	@Override
	public List<DadosEncaminhamento> encaminhamentosNaoRespondidos(Long orgao, Long setor, Date dataInicial,Date dataFinal, String prazo) {
		String q = " SELECT DISTINCT(OE.INATENDIMENTOID), OA.VANUMPROTOCOLO, OA.INNUMEROATENDIMENTO , OA.SMANOATENDIMENTO, S.DESCRICAO, OA.INCODIGOASSUNTONOVO, A.VADESCRICAO, OA.INCODIGONATUREZA, OA.INCODIGOPRIORIZACAO, " + 
				   " CONVERT(varchar, OE.DAENCAMINHAMENTO, 103) as 'DATAENCAMINHADO', OE.INCODIGOENCAMINHAMENTOENVIO, CONVERT(varchar, OE.DAPRAZOENCAMINHAMENTO, 103) as 'DATAPRAZO', " +
				   " (select top 1 CONVERT(varchar, DADESPACHO, 103) from OUVIDORIA_DESPACHO where INATENDIMENTOID = OA.INATENDIMENTOID and INCODIGO_SETOR_DESTINO = ? order by DADESPACHO desc) AS 'DATA_DESPACHO', " +
				   " (select count(*) from OUVIDORIA_DESPACHO where INATENDIMENTOID = OA.INATENDIMENTOID and INCODIGO_SETOR_DESTINO = ?) AS 'QTD' " + 
				   " FROM OUVIDORIA_ATENDIMENTO OA (NOLOCK) " + 
				   " INNER JOIN OUVIDORIA_ASSUNTO A ON OA.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO AND A.INCODIGOORGAO = OA.INCODIGOORGAO " +
				   " INNER JOIN OUVIDORIA_ENCAMINHAMENTO_ENVIO OE (NOLOCK) ON (OA.INATENDIMENTOID = OE.INATENDIMENTOID)  LEFT JOIN OUVIDORIA_ENCAMINHAMENTO_RESPOSTA R (NOLOCK) " + 
				   " ON (OE.INCODIGOENCAMINHAMENTOENVIO = R.INCODIGOENCAMINHAMENTO) " + 
				   " INNER JOIN GLOB_SETOR S (NOLOCK) ON (S.CODIGO_SETOR = OE.INCODIGOSETORDESTINO)  WHERE (OA.INCODIGOORGAO = ?) AND OE.INCODIGOSETORDESTINO = ? AND (OE.DAENCAMINHAMENTO >= ?) " + 
				   " AND (OE.DAENCAMINHAMENTO <= ?)  AND (OA.DACONCLUSAO = ?) AND (OA.SMSTATUS <> ?) AND OE.SMCANCELADO = 0 AND R.INCODIGORESPOSTA IS NULL "; 

            		   
		   if (prazo.equals("1")) {
			   q = q + "AND OE.DAPRAZOENCAMINHAMENTO < DATEADD(day, DATEDIFF(day, 0, GETDATE()), 0)";
		   }else if (prazo.equals("2")) {
			   q = q + "AND OE.DAPRAZOENCAMINHAMENTO >= DATEADD(day, DATEDIFF(day, 0, GETDATE()), 0)";
		   }
		
	       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		   Query query =  manager.createNativeQuery(q);
		   query.setParameter(1, setor);
		   query.setParameter(2, setor);
		   query.setParameter(3, orgao);
		   query.setParameter(4, setor);
		   query.setParameter(5, dateFormat.format(dataInicial));
		   query.setParameter(6, dateFormat.format(dataFinal));
		   query.setParameter(7, "1969/12/31 21:00:00.000");
		   query.setParameter(8, 2);

		   @SuppressWarnings("unchecked")
		   List<Object> lista = query.getResultList();
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   List<DadosEncaminhamento> dados = new ArrayList<>(lista.size());
		   for (int i = 0; i < lista.size(); i++) {
	    	  Object obj = lista.get(i);
			  Object[] itensObj = (Object[])obj;
			  dados.add(new DadosEncaminhamento(Long.parseLong(itensObj[0].toString()), 
					  itensObj[1].toString(), itensObj[9].toString(), itensObj[6].toString(), Long.parseLong(itensObj[10].toString()), 
					  itensObj[11].toString(), Integer.parseInt(itensObj[13].toString()) == 0 ? "" : itensObj[13].toString(), 
							  itensObj[12] == null ? "" : itensObj[12].toString(), 
									  LocalDate.parse(itensObj[11].toString(), formatter).isBefore(LocalDate.now()) == true ? "S" : "N"));
		   }
		   
		   return dados;
		
	}

	@Override
	public List<DadosEncaminhamentoTratar> tratarEncaminhamentos(Long orgao, Long setor) {
		String q = " SELECT OA.INATENDIMENTOID, OA.VANUMPROTOCOLO, OA.SMANOATENDIMENTO, OA.INNUMEROATENDIMENTO, ASS.VADESCRICAO as 'ASSUNTO', OP.VADESCRICAO as 'PRIORIDADE', CONVERT(varchar, EE.DAENCAMINHAMENTO, 103) AS 'DATA_ENC', " + 
				"  CONVERT(varchar, EE.DAPRAZOENCAMINHAMENTO, 103) AS 'DATA_PRAZO', EE.INCODIGOENCAMINHAMENTOENVIO, DATEDIFF(day,EE.DAPRAZOENCAMINHAMENTO, GETDATE()) AS 'DIAS_ATRASADO' " + 
				"  FROM OUVIDORIA_ENCAMINHAMENTO_ENVIO EE (NOLOCK) " + 
				"  INNER JOIN OUVIDORIA_ATENDIMENTO OA (NOLOCK) ON (EE.INATENDIMENTOID = OA.INATENDIMENTOID) " + 
				"  LEFT JOIN OUVIDORIA_ENCAMINHAMENTO_INTERNO_ENVIO IE (NOLOCK) ON (IE.INCODIGOENCAMINHAMENTOENVIO = EE.INCODIGOENCAMINHAMENTOENVIO) " + 
				"  LEFT JOIN OUVIDORIA_ENCAMINHAMENTO_INTERNO_RESPOSTA IR (NOLOCK) ON (IR.INCODIGOENCAMINHAMENTOINTERNOENVIO = IE.INCODIGOENCAMINHAMENTOINTERNOENVIO) " + 
				"  LEFT JOIN OUVIDORIA_ENCAMINHAMENTO_RESPOSTA ER (NOLOCK) ON (EE.INCODIGOENCAMINHAMENTOENVIO = ER.INCODIGOENCAMINHAMENTO) " + 
				"  INNER JOIN OUVIDORIA_ASSUNTO ASS (NOLOCK) ON (OA.INCODIGOASSUNTONOVO = ASS.INCODIGOASSUNTONOVO AND OA.INCODIGOORGAO = ASS.INCODIGOORGAO) " + 
				"  INNER JOIN OUVIDORIA_PRIORIZACAO OP (NOLOCK) ON (OA.INCODIGOPRIORIZACAO = OP.INCODIGOPRIORIZACAO) " + 
				"  WHERE OA.INCODIGOORGAO = ? AND EE.INCODIGOSETORDESTINO = ? AND OA.SMSTATUS <> ? AND OA.DACONCLUSAO = ? " + 
				"  AND ER.INCODIGORESPOSTA IS NULL AND IE.INCODIGOENCAMINHAMENTOINTERNOENVIO IS NULL "; 
		
		   Query query =  manager.createNativeQuery(q);
		   query.setParameter(1, orgao);
		   query.setParameter(2, setor);
		   query.setParameter(3, 2);
		   query.setParameter(4, "1969/12/31 21:00:00.000");

		   @SuppressWarnings("unchecked")
		   List<Object> lista = query.getResultList();
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   List<DadosEncaminhamentoTratar> dados = new ArrayList<>(lista.size());
		   for (int i = 0; i < lista.size(); i++) {
	    	  Object obj = lista.get(i);
			  Object[] itensObj = (Object[])obj;
			  dados.add(new DadosEncaminhamentoTratar(Long.parseLong(itensObj[0].toString()), 
					  itensObj[1].toString(), itensObj[6].toString(), itensObj[4].toString(), Long.parseLong(itensObj[8].toString()), 
					  itensObj[7].toString(), 
									  LocalDate.parse(itensObj[7].toString(), formatter).isBefore(LocalDate.now()) == true ? "S" : "N", itensObj[5].toString(), Integer.parseInt(itensObj[9].toString()) <= 0 ? "" : itensObj[9].toString()));
		   }
		   
		   return dados;
	}
	
	
	

	

}
