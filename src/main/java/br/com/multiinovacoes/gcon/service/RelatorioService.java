package br.com.multiinovacoes.gcon.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.multiinovacoes.gcon.report.DadosGrafico;
import br.com.multiinovacoes.gcon.repository.RelatorioQueries;

public class RelatorioService {
	
	@Autowired
	private RelatorioQueries relatorioQueries;
	
	
	
	public List<DadosGrafico> relatorioNatureza(Long orgao, Date dataInicial, Date dataFinal){
		
		List<DadosGrafico> lista = relatorioQueries.getRelatorioNatureza(orgao, dataInicial, dataFinal);
		
		return lista;
		
	}

}
