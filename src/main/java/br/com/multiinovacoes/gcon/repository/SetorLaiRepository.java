package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.SetorLai;

@Repository
public interface SetorLaiRepository extends JpaRepository<SetorLai, Long>, SetorLaiQueries {

	public List<SetorLai> findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(Long orgao, String descricao);

	public List<SetorLai> findByOrgaoAndStatusAndTipoAcessoPerfilAndIdNotInOrderByDescricaoAsc(Long orgao, Integer status, Integer tipoAcessoPerfil, List<Long> idList);

	public List<SetorLai> findByIdInOrderByDescricaoAsc(List<Long> idList);
	
	public List<SetorLai> findByOrgaoOrderByDescricaoAsc(Long orgao);
	
	public List<SetorLai> findByOrgaoAndStatusAndTipoAcessoPerfilOrderByDescricaoAsc(Long orgao, Integer status, Integer tipoAcessoPerfil);


}
