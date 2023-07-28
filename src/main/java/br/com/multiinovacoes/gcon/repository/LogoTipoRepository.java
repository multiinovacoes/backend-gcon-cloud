package br.com.multiinovacoes.gcon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.LogoTipo;

@Repository
public interface LogoTipoRepository extends JpaRepository<LogoTipo, Long> {
	
	public LogoTipo findByOrgao(Long orgao);


}
