package br.com.multiinovacoes.gcon.repository.impl;

import java.util.ArrayList;
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

import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.Atendimento;
import br.com.multiinovacoes.gcon.model.Despacho;
import br.com.multiinovacoes.gcon.repository.DespachoQueries;

public class DespachoRepositoryImpl implements DespachoQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public List<Despacho> consultaAtendimento(Atendimento atendimento) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Despacho> criteria = builder.createQuery(Despacho.class);
		Root<Despacho> root = criteria.from(Despacho.class);
		root.fetch("usuario", JoinType.LEFT);
		//root.fetch("setorOrigem", JoinType.LEFT);
		//root.fetch("setorDestino", JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get("codigoAtendimento"), atendimento.getNumeroAtendimento()));
		predicates.add(builder.equal(root.get("anoAtendimento"), atendimento.getAnoAtendimento()));
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<Despacho> query = manager.createQuery(criteria);
		return query.getResultList().isEmpty() ? null : query.getResultList();
	}

	@Override
	@Transactional
	public Despacho consultaDespacho(Long codigoDespacho) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Despacho> criteria = builder.createQuery(Despacho.class);
		Root<Despacho> root = criteria.from(Despacho.class);
		root.fetch("usuario", JoinType.LEFT);
		//root.fetch("setorOrigem", JoinType.LEFT);
		//root.fetch("setorDestino", JoinType.LEFT);
		Predicate predicate = builder.equal(root.get("id"), codigoDespacho);
		criteria.where(predicate);
		TypedQuery<Despacho> query = manager.createQuery(criteria);
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}

	@Override
	public String consultar(String campo, Long id) {
		Query query =  manager.createNativeQuery("SELECT " + campo + " FROM OUVIDORIA_DESPACHO WHERE INATENDIMENTOID = ? ");
		query.setParameter(1, id);
	    @SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
        if (!lista.isEmpty()) {
        	return (String )lista.get(0);
        }
        else
        	return "Não encontrado";
	}

	
	

}
