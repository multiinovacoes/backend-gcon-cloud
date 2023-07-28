package br.com.multiinovacoes.gcon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.TipoExpressoes;


@Repository
public interface TipoExpressoesRepository extends JpaRepository<TipoExpressoes, Long> {
	
	public List<TipoExpressoes> findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(String descricao);
	
	public List<TipoExpressoes> findByRelatorioGeralOrderByDescricaoAsc(Integer relatorioGeral);
	
	public Optional<TipoExpressoes> findByExpressao(String expressao);


}
