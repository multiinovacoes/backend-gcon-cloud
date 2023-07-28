package br.com.multiinovacoes.gcon.repository.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.multiinovacoes.gcon.enums.StatusRecursoEnum;
import br.com.multiinovacoes.gcon.enums.TipoRecursoEnum;
import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.filter.AtendimentoFilter;
import br.com.multiinovacoes.gcon.repository.AreaLaiRepository;
import br.com.multiinovacoes.gcon.repository.AtendimentoLaiQueries;
import br.com.multiinovacoes.gcon.repository.InstituicaoRepository;

public class AtendimentoLaiRepositoryImpl implements AtendimentoLaiQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private AreaLaiRepository areaLaiRepository;
	
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	
	
	@Override
	@Transactional
	public Page<AtendimentoLai> consultaNovosPedidosLai(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT a FROM AtendimentoLai a where a.orgao = :orgao and a.status <> 2 "
				+ "and a.statusAtendimento = 0 and (a.dataConclusao = '1969-12-31 21:00:00.000' or a.dataConclusao is null)"
				+ "and (select count(e.id) from EncaminhamentoLai e where e.atendimento = a.id and e.status = 0) = 0";
		TypedQuery<AtendimentoLai> query = manager.createQuery(q, AtendimentoLai.class);
		query.setParameter("orgao", orgao);
		adicionarRestricoesDePaginacao(query, pageable);
		
		List<AtendimentoLai> lista = query.getResultList();
		List<AtendimentoLai> novaLista = new ArrayList<>();
		for (AtendimentoLai atendimentoLai : lista) {
			atendimentoLai.setDescricaoArea(areaLaiRepository.getById(atendimentoLai.getArea()).getDescricao());
			atendimentoLai.setDescricaoInstituicao(instituicaoRepository.getById(atendimentoLai.getInstituicao()).getDescricao());
			novaLista.add(atendimentoLai);
		}
		
		return new PageImpl<>(novaLista, pageable, totalRegistros);
	}

	@Override
	@Transactional
	public Page<AtendimentoLai> consultaAtendimentosLaiRecursoAberto(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT a FROM AtendimentoLai a INNER JOIN RecursoLai r ON a.id = r.atendimento and a.orgao = :orgao and a.status <> 2 "
				+ "and a.statusAtendimento = 1 and r.status = :statusRecurso and r.dataPrazo > getDate() and r.tipo = :tipoRecurso";
		TypedQuery<AtendimentoLai> query = manager.createQuery(q, AtendimentoLai.class);
		query.setParameter("orgao", orgao);
		query.setParameter("statusRecurso", StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo());
		query.setParameter("tipoRecurso", TipoRecursoEnum.RECURSO_1_INSTANCIA.getCodigo());

		adicionarRestricoesDePaginacao(query, pageable);
		
		List<AtendimentoLai> lista = query.getResultList();
		List<AtendimentoLai> novaLista = new ArrayList<>();
		for (AtendimentoLai atendimentoLai : lista) {
			atendimentoLai.setDescricaoArea(areaLaiRepository.getById(atendimentoLai.getArea()).getDescricao());
			atendimentoLai.setDescricaoInstituicao(instituicaoRepository.getById(atendimentoLai.getInstituicao()).getDescricao());
			novaLista.add(atendimentoLai);
		}
		
		return new PageImpl<>(novaLista, pageable, totalRegistros);
	}

	@Override
	@Transactional
	public Page<AtendimentoLai> consultaAtendimentosLaiRecursoVencido(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT a FROM AtendimentoLai a INNER JOIN RecursoLai r ON a.id = r.atendimento and a.orgao = :orgao and a.status <> 2 "
				+ "and r.status = :statusRecurso and r.dataPrazo < getDate() and r.tipo = :tipoRecurso";
		TypedQuery<AtendimentoLai> query = manager.createQuery(q, AtendimentoLai.class);
		query.setParameter("orgao", orgao);
		query.setParameter("statusRecurso", StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo());
		query.setParameter("tipoRecurso", TipoRecursoEnum.RECURSO_1_INSTANCIA.getCodigo());
		adicionarRestricoesDePaginacao(query, pageable);
		
		List<AtendimentoLai> lista = query.getResultList();
		List<AtendimentoLai> novaLista = new ArrayList<>();
		for (AtendimentoLai atendimentoLai : lista) {
			atendimentoLai.setDescricaoArea(areaLaiRepository.getById(atendimentoLai.getArea()).getDescricao());
			atendimentoLai.setDescricaoInstituicao(instituicaoRepository.getById(atendimentoLai.getInstituicao()).getDescricao());
			novaLista.add(atendimentoLai);
		}
		
		return new PageImpl<>(novaLista, pageable, totalRegistros);
	}

	@Override
	@Transactional
	public Page<AtendimentoLai> consultaPedidosVencidosLai(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT a FROM AtendimentoLai a where a.orgao = :orgao and a.status <> 2 "
				+ "and a.statusAtendimento = 0 and a.dataPrazo < getDate() and (a.dataConclusao = '1969-12-31 21:00:00.000' or a.dataConclusao is null)";
		TypedQuery<AtendimentoLai> query = manager.createQuery(q, AtendimentoLai.class);
		query.setParameter("orgao", orgao);
		adicionarRestricoesDePaginacao(query, pageable);
		
		List<AtendimentoLai> lista = query.getResultList();
		List<AtendimentoLai> novaLista = new ArrayList<>();
		for (AtendimentoLai atendimentoLai : lista) {
			atendimentoLai.setDescricaoArea(areaLaiRepository.getById(atendimentoLai.getArea()).getDescricao());
			atendimentoLai.setDescricaoInstituicao(instituicaoRepository.getById(atendimentoLai.getInstituicao()).getDescricao());
			novaLista.add(atendimentoLai);
		}
		
		return new PageImpl<>(novaLista, pageable, totalRegistros);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<AtendimentoLai> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}


	@Override
	public AtendimentoLai consultaAtendimentolai(Long codigoAtendimento) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<AtendimentoLai> criteria = builder.createQuery(AtendimentoLai.class);
		Root<AtendimentoLai> root = criteria.from(AtendimentoLai.class);
		Predicate predicates = builder.equal(root.get("id"), codigoAtendimento);
		criteria.where(predicates);
		TypedQuery<AtendimentoLai> query = manager.createQuery(criteria);
		
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}
	
	
	@Override
	public Page<AtendimentoLai> filtrar(Long orgao, AtendimentoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<AtendimentoLai> criteria = builder.createQuery(AtendimentoLai.class);
		Root<AtendimentoLai> root = criteria.from(AtendimentoLai.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root, orgao);
		criteria.where(predicates);
		
		TypedQuery<AtendimentoLai> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		List<AtendimentoLai> lista = query.getResultList();

		List<AtendimentoLai> novaLista = new ArrayList<>();

		for (AtendimentoLai atendimentoLai : lista) {
			atendimentoLai.setDescricaoArea(areaLaiRepository.getById(atendimentoLai.getArea()).getDescricao());
			atendimentoLai.setDescricaoInstituicao(instituicaoRepository.getById(atendimentoLai.getInstituicao()).getDescricao());
			novaLista.add(atendimentoLai);
		}

		
		return new PageImpl<>(novaLista, pageable, total(filtro, orgao));
	}
	
	@SuppressWarnings("deprecation")
	private Predicate[] criarRestricoes(AtendimentoFilter filtro, CriteriaBuilder builder,
			Root<AtendimentoLai> root, Long orgao) {
		List<Predicate> predicates = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		predicates.add(
				builder.equal(root.get("orgao"), orgao));

		if (!StringUtils.isEmpty(filtro.getSolicitante())) {
			predicates.add(builder.like(
					builder.lower(root.get("nomeSolicitante")), "%" + filtro.getSolicitante().toLowerCase() + "%"));
		}
		
		if (filtro.getDataInicio() != null) {
		    LocalDate date = LocalDate.parse(filtro.getDataInicio(),formatter);
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataEntrada"), date));
		}
		
		if (filtro.getDataFinal() != null) {
		    LocalDate date = LocalDate.parse(filtro.getDataFinal(),formatter);
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataEntrada"), date));
		}
		
		if (filtro.getAssunto() != null) {
			predicates.add(
					builder.equal(root.get("assunto"), filtro.getAssunto()));
		}

		if (filtro.getProtocolo() != null) {
			predicates.add(
					builder.equal(root.get("numeroProtocolo"), filtro.getProtocolo()));
		}

		if (filtro.getPalavraChave() != null) {
			predicates.add(builder.like((root.get("descricao")),  builder.lower(builder.literal("%"+filtro.getPalavraChave().toLowerCase()+"%"))));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(AtendimentoFilter filtro, Long orgao) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<AtendimentoLai> root = criteria.from(AtendimentoLai.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root, orgao);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	


	

}
