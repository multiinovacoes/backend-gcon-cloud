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

import br.com.multiinovacoes.gcon.model.EncaminhamentoResposta;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoRespostaQueries;

public class EncaminhamentoRespostaRepositoryImpl implements EncaminhamentoRespostaQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public EncaminhamentoResposta consultaEncaminhamentoResposta(Long codigoEncaminhamentoResposta) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EncaminhamentoResposta> criteria = builder.createQuery(EncaminhamentoResposta.class);
		Root<EncaminhamentoResposta> root = criteria.from(EncaminhamentoResposta.class);
		Predicate predicates = builder.equal(root.get("id"), codigoEncaminhamentoResposta);
		criteria.where(predicates);
		TypedQuery<EncaminhamentoResposta> query = manager.createQuery(criteria);
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}

	@Override
	public String consultar(Long idAtendimento) {
		Query query =  manager.createNativeQuery("SELECT  TOP 1 R.VADESPACHO " +
		 " FROM OUVIDORIA_ENCAMINHAMENTO_ENVIO(NOLOCK) , OUVIDORIA_ENCAMINHAMENTO_RESPOSTA R, " +
		 " OUVIDORIA_ATENDIMENTO(NOLOCK) " +
		 " WHERE " +
		 " OUVIDORIA_ENCAMINHAMENTO_ENVIO.INATENDIMENTOID = OUVIDORIA_ATENDIMENTO.INATENDIMENTOID " +
		 " AND OUVIDORIA_ENCAMINHAMENTO_ENVIO.INCODIGOENCAMINHAMENTOENVIO = R.INCODIGOENCAMINHAMENTO " +
		 " AND OUVIDORIA_ENCAMINHAMENTO_ENVIO.SMCANCELADO = 0 " +
		 " AND OUVIDORIA_ATENDIMENTO.SMSTATUS <> 2 " +
		 " and OUVIDORIA_ATENDIMENTO.INATENDIMENTOID = ?" +
		 " ORDER BY INCODIGOENCAMINHAMENTOENVIO desc ");
		query.setParameter(1, idAtendimento);
	    @SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
        if (!lista.isEmpty()) {
        	return (String )lista.get(0);
        }
        else
        	return "Não encontrado";
	}

	

	

}
