package br.com.multiinovacoes.gcon.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.multiinovacoes.gcon.repository.SetorQueries;

public class SetorRepositoryImpl implements SetorQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public String filtrar(String campo, Long id, Long orgao) {
		
		Query query =  manager.createNativeQuery("SELECT " + campo + " FROM GLOB_SETOR WHERE CODIGO_SETOR = ? AND CODIGO_ORGAO = ? ");
		query.setParameter(1, id);
		query.setParameter(2, orgao);
	    
	    @SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
        if (!lista.isEmpty()) {
        	return (String )lista.get(0);
        }
        else
        	return "Não encontrado";
	}

	
  	
	
	

}
