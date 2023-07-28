package br.com.multiinovacoes.gcon.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.EncaminhamentoLai;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoLaiQueries;

public class EncaminhamentoLaiRepositoryImpl implements EncaminhamentoLaiQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public List<EncaminhamentoLai> consultaAtendimento(Long codigoAtendimento) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EncaminhamentoLai> criteria = builder.createQuery(EncaminhamentoLai.class);
		Root<EncaminhamentoLai> root = criteria.from(EncaminhamentoLai.class);
		Predicate predicates = builder.equal(root.get("atendimento"), codigoAtendimento);
		criteria.where(predicates);
		TypedQuery<EncaminhamentoLai> query = manager.createQuery(criteria);
		
		return query.getResultList().isEmpty() ? null : query.getResultList();
	}

	@Override
	public EncaminhamentoLai consultaEncaminhamento(Long codigoEncaminhamento) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EncaminhamentoLai> criteria = builder.createQuery(EncaminhamentoLai.class);
		Root<EncaminhamentoLai> root = criteria.from(EncaminhamentoLai.class);
		Predicate predicates = builder.equal(root.get("id"), codigoEncaminhamento);
		criteria.where(predicates);
		TypedQuery<EncaminhamentoLai> query = manager.createQuery(criteria);
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
	@Transactional
	public List<EncaminhamentoLai> consultaEncaminhamentosRecebidos(Long orgao, Pageable pageable, Integer totalRegistros) {
		
		String q = "SELECT e FROM EncaminhamentoLai e inner join AtendimentoLai a on a.id = e.atendimento "
				+ " left join EncaminhamentoRespostaLai r on e.id = r.encaminhamento "
				+ " where a.orgao = :orgao and a.status = 0 and e.status = 0"
				+ " and r.id is not null ";

		
		TypedQuery<EncaminhamentoLai> query = manager.createQuery(q, EncaminhamentoLai.class);
		query.setParameter("orgao", orgao);
		adicionarRestricoesDePaginacao(query, pageable);
		return  query.getResultList();
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<EncaminhamentoLai> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	
	@Override
	@Transactional
	public List<EncaminhamentoLai> consultaPedidosEncaminhadosLai(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT e FROM EncaminhamentoLai e inner join AtendimentoLai a on a.id = e.atendimento "
				+ " left join EncaminhamentoRespostaLai r on e.id = r.encaminhamento "
				+ " where a.orgao = :orgao and a.status = 0 and e.status = 0"
				+ " and r.id is null and e.dataPrazoResposta >= getDate()";

		TypedQuery<EncaminhamentoLai> query = manager.createQuery(q, EncaminhamentoLai.class);
		query.setParameter("orgao", orgao);
		adicionarRestricoesDePaginacao(query, pageable);
		
		List<EncaminhamentoLai> lista = query.getResultList();
		return lista;
	}

	@Override
	@Transactional
	public List<EncaminhamentoLai> consultaPedidosEncaminhadosLaiVencidos(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT e FROM EncaminhamentoLai e inner join AtendimentoLai a on a.id = e.atendimento "
				+ " left join EncaminhamentoRespostaLai r on e.id = r.encaminhamento "
				+ " where a.orgao = :orgao and a.status = 0 and e.status = 0"
				+ " and r.id is null and e.dataPrazoResposta < getDate()";

		TypedQuery<EncaminhamentoLai> query = manager.createQuery(q, EncaminhamentoLai.class);
		query.setParameter("orgao", orgao);
		adicionarRestricoesDePaginacao(query, pageable);
		
		List<EncaminhamentoLai> lista = query.getResultList();
		return lista;
	}
	

}
