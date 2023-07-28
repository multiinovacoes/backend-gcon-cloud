package br.com.multiinovacoes.gcon.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.multiinovacoes.gcon.model.RespostaParcialLai;
import br.com.multiinovacoes.gcon.repository.RespostaParcialLaiQueries;

public class RespostaParcialLaiRepositoryImpl implements RespostaParcialLaiQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<RespostaParcialLai> consultaAtendimento(Long codigoAtendimento) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RespostaParcialLai> criteria = builder.createQuery(RespostaParcialLai.class);
		Root<RespostaParcialLai> root = criteria.from(RespostaParcialLai.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get("atendimento"), codigoAtendimento));
		predicates.add(builder.equal(root.get("status"), 0));
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<RespostaParcialLai> query = manager.createQuery(criteria);
		return query.getResultList().isEmpty() ? null : query.getResultList();
	}

	@Override
	public RespostaParcialLai consultaResposta(Long codigoResposta) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RespostaParcialLai> criteria = builder.createQuery(RespostaParcialLai.class);
		Root<RespostaParcialLai> root = criteria.from(RespostaParcialLai.class);
		Predicate predicate = builder.equal(root.get("id"), codigoResposta);
		criteria.where(predicate);
		TypedQuery<RespostaParcialLai> query = manager.createQuery(criteria);
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}

	@Override
	public String consultar(String campo, Long id) {
		Query query =  manager.createNativeQuery("SELECT " + campo + " FROM LAI_RESPOSTA_PARCIAL WHERE INCODIGOATENDIMENTO = ? ");
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
