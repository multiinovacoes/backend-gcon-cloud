package br.com.multiinovacoes.gcon.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.multiinovacoes.gcon.repository.OrgaoQueries;

public class OrgaoRepositoryImpl implements OrgaoQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public String filtrar(String campo, Long id) {
		
		Query query =  manager.createNativeQuery("SELECT " + campo + " FROM GLOB_ORGAO WHERE CODIGO_ORGAO = ? ");
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
