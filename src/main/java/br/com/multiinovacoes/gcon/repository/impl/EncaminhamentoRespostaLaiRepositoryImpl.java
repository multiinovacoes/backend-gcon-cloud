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

import br.com.multiinovacoes.gcon.model.EncaminhamentoRespostaLai;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoRespostaLaiQueries;

public class EncaminhamentoRespostaLaiRepositoryImpl implements EncaminhamentoRespostaLaiQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public EncaminhamentoRespostaLai consultaEncaminhamentoResposta(Long codigoEncaminhamentoResposta) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EncaminhamentoRespostaLai> criteria = builder.createQuery(EncaminhamentoRespostaLai.class);
		Root<EncaminhamentoRespostaLai> root = criteria.from(EncaminhamentoRespostaLai.class);
		Predicate predicates = builder.equal(root.get("id"), codigoEncaminhamentoResposta);
		criteria.where(predicates);
		TypedQuery<EncaminhamentoRespostaLai> query = manager.createQuery(criteria);
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}

	@Override
	public String consultar(Long idAtendimento) {
		Query query =  manager.createNativeQuery("SELECT  TOP 1 R.DESPACHO " +
		 " FROM LAI_ENCAMINHAMENTO (NOLOCK) , LAI_ENCAMINHAMENTO_RESPOSTA R, " +
		 " LAI_ATENDIMENTO(NOLOCK) " +
		 " WHERE " +
		 " LAI_ENCAMINHAMENTO.CODIGO_ATENDIMENTO_LAI = LAI_ATENDIMENTO.INATENDIMENTOID " +
		 " AND LAI_ENCAMINHAMENTO.CODIGO_ENCAMINHAMENTO = R.CODIGO_ENCAMINHAMENTO " +
		 " AND LAI_ENCAMINHAMENTO.STATUS = 0 " +
		 " AND LAI_ATENDIMENTO.SMSTATUS <> 2 " +
		 " AND R.STATUS = 0 " +
		 " AND R.SATISFAZ = 1 " +
		 " and LAI_ATENDIMENTO.INATENDIMENTOID = ?" +
		 " ORDER BY LAI_ENCAMINHAMENTO.CODIGO_ENCAMINHAMENTO desc ");
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
