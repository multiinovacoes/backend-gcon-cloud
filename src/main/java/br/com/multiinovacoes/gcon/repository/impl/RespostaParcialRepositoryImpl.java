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

import br.com.multiinovacoes.gcon.model.RespostaParcial;
import br.com.multiinovacoes.gcon.repository.RespostaParcialQueries;

public class RespostaParcialRepositoryImpl implements RespostaParcialQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<RespostaParcial> consultaAtendimento(Long codigoAtendimento) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RespostaParcial> criteria = builder.createQuery(RespostaParcial.class);
		Root<RespostaParcial> root = criteria.from(RespostaParcial.class);
		root.fetch("usuario", JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get("atendimento"), codigoAtendimento));
		predicates.add(builder.equal(root.get("cancelado"), 0));
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<RespostaParcial> query = manager.createQuery(criteria);
		return query.getResultList().isEmpty() ? null : query.getResultList();
	}

	@Override
	public RespostaParcial consultaResposta(Long codigoResposta) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RespostaParcial> criteria = builder.createQuery(RespostaParcial.class);
		Root<RespostaParcial> root = criteria.from(RespostaParcial.class);
		root.fetch("usuario", JoinType.LEFT);
		Predicate predicate = builder.equal(root.get("id"), codigoResposta);
		criteria.where(predicate);
		TypedQuery<RespostaParcial> query = manager.createQuery(criteria);
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}

	@Override
	public String consultar(String campo, Long id) {
		Query query =  manager.createNativeQuery("SELECT " + campo + " FROM OUVIDORIA_RESPOSTA_PARCIAL WHERE INATENDIMENTOID = ? ");
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
