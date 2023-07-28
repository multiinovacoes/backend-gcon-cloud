package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	public List<Empresa> findByOrgaoOrderByDescricaoAsc(Long orgao);


}
