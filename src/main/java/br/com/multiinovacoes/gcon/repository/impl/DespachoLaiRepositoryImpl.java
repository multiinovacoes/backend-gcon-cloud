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

import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.DespachoLai;
import br.com.multiinovacoes.gcon.repository.DespachoLaiQueries;

public class DespachoLaiRepositoryImpl implements DespachoLaiQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public List<DespachoLai> consultaAtendimentoLai(AtendimentoLai atendimento) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<DespachoLai> criteria = builder.createQuery(DespachoLai.class);
		Root<DespachoLai> root = criteria.from(DespachoLai.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get("atendimento"), atendimento.getId()));
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<DespachoLai> query = manager.createQuery(criteria);
		return query.getResultList().isEmpty() ? null : query.getResultList();
	}

	@Override
	@Transactional
	public DespachoLai consultaDespacho(Long codigoDespacho) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<DespachoLai> criteria = builder.createQuery(DespachoLai.class);
		Root<DespachoLai> root = criteria.from(DespachoLai.class);
		Predicate predicate = builder.equal(root.get("id"), codigoDespacho);
		criteria.where(predicate);
		TypedQuery<DespachoLai> query = manager.createQuery(criteria);
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}

	@Override
	public String consultar(String campo, Long id) {
		Query query =  manager.createNativeQuery("SELECT " + campo + " FROM LAI_DESPACHO WHERE INATENDIMENTOID = ? ");
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
