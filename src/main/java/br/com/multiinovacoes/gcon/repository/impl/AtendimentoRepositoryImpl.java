package br.com.multiinovacoes.gcon.repository.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

import br.com.multiinovacoes.gcon.enums.MesEnum;
import br.com.multiinovacoes.gcon.graphics.Data;
import br.com.multiinovacoes.gcon.graphics.Label;
import br.com.multiinovacoes.gcon.graphics.SeriesName;
import br.com.multiinovacoes.gcon.graphics.StackedColumn;
import br.com.multiinovacoes.gcon.model.Atendimento;
import br.com.multiinovacoes.gcon.model.Natureza;
import br.com.multiinovacoes.gcon.model.dto.HistoricoUsuarioDto;
import br.com.multiinovacoes.gcon.model.filter.AtendimentoFilter;
import br.com.multiinovacoes.gcon.model.request.RelatorioGeralRequest;
import br.com.multiinovacoes.gcon.report.AtendimentoNatureza;
import br.com.multiinovacoes.gcon.report.DadosComparativoPeriodo;
import br.com.multiinovacoes.gcon.report.DadosGrafico;
import br.com.multiinovacoes.gcon.report.DadosRelatorio;
import br.com.multiinovacoes.gcon.report.DadosRelatorioGeral;
import br.com.multiinovacoes.gcon.report.DadosRelatorioUsuario;
import br.com.multiinovacoes.gcon.report.DetalheAtendimentoArea;
import br.com.multiinovacoes.gcon.report.RelatorioPeriodo;
import br.com.multiinovacoes.gcon.report.RelatorioUsuario;
import br.com.multiinovacoes.gcon.report.RetornoRelatorioGeral;
import br.com.multiinovacoes.gcon.repository.AreaRepository;
import br.com.multiinovacoes.gcon.repository.AssuntoRepository;
import br.com.multiinovacoes.gcon.repository.AtendimentoQueries;
import br.com.multiinovacoes.gcon.repository.NaturezaRepository;
import br.com.multiinovacoes.gcon.repository.RelatorioQueries;
import br.com.multiinovacoes.gcon.repository.UsuarioRepository;
import br.com.multiinovacoes.gcon.util.NumberTools;

public class AtendimentoRepositoryImpl implements AtendimentoQueries, RelatorioQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private AssuntoRepository assuntoRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private NaturezaRepository naturezaRepository;
	
	

	@Override
	public List<AtendimentoNatureza> getQtdPainelOrigemManifestacao(Long orgao, Integer ano) {
		String q = " select o.VADESCRICAO AS 'DESCRICAO', count(*) AS 'QTD', " + 
				" ( " + 
				" select count(*) AS 'TOTAL' from OUVIDORIA_ATENDIMENTO a, OUVIDORIA_ORIGEM_MANIFESTACAO o " + 
				" where " + 
				" a.INCODIGOORIGEMMANIFESTACAO = o.INCODIGOORIGEMMANIFESTACAO " + 
				" and a.INCODIGOORGAO = ? " + 
				" and YEAR(a.DACRIACAO) = ? " + 
				" and a.SMSTATUS <> 2 " + 
				" ) " + 
				" from OUVIDORIA_ATENDIMENTO a, OUVIDORIA_ORIGEM_MANIFESTACAO o " + 
				" where " + 
				" a.INCODIGOORIGEMMANIFESTACAO = o.INCODIGOORIGEMMANIFESTACAO " + 
				" and a.INCODIGOORGAO = ? " + 
				" and YEAR(a.DACRIACAO) = ? " + 
				" and a.SMSTATUS <> 2 " + 
				" group by o.VADESCRICAO ";
				
				Query query =  manager.createNativeQuery(q);

		query.setParameter(1, orgao);
		query.setParameter(2, ano);
		query.setParameter(3, orgao);
		query.setParameter(4, ano);
	     
		@SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
		List<AtendimentoNatureza> atendimentoNatureza = new ArrayList<>(lista.size());
		BigDecimal percentual = null;
		
		for (int i = 0; i < lista.size(); i++) {
    		Object obj = lista.get(i);
			Object[] itensObj = (Object[])obj;
			percentual = new BigDecimal((Integer.parseInt(itensObj[1].toString())*100f)/Integer.parseInt(itensObj[2].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			atendimentoNatureza.add(new AtendimentoNatureza(itensObj[0].toString(), percentual.doubleValue()));
		}
        return atendimentoNatureza;        
	}





	@Override
	public List<AtendimentoNatureza> getQtdPainelPesquisaSatisfacao(Long orgao, Integer ano) {
		String q = " select r.VA_DESCRICAO as 'descricao', count(a.INATENDIMENTOID) as 'qtd',"
				+ "(" + 
				" select count(a.INATENDIMENTOID) as 'total' " + 
				" from OUVIDORIA_PESQUISA_SATISFACAO p, OUVIDORIA_ATENDIMENTO a, OUVIDORIA_SATISFACAO_RESPOSTAS r, OUVIDORIA_SATISFACAO_PERGUNTAS pp " + 
				" where " + 
				" a.INATENDIMENTOID = p.IN_ID_ATENDIMENTO " + 
				" and pp.IN_ORGAO = a.INCODIGOORGAO " + 
				" and pp.IN_SATISFACAO_PERGUNTA_ID = r.IN_SATISFACAO_PERGUNTAS " + 
				" and p.IN_ID_RESPOSTA = r.IN_SATISFACAO_RESPOSTAS_ID " + 
				" and a.SMSTATUS = 1 " + 
				" and a.SMSTATUSATENDIMENTO = 1 " + 
				" and a.INSATISFAZ = 1 " + 
				" and a.INCODIGOORGAO = ? " + 
				" and a.SMANOATENDIMENTO = ? " + 
				" and r.IN_ATIVO = 0 " + 
				" and pp.VA_DESCRICAO = 'Como você avalia o serviço prestado pela Ouvidoria?' " + 
				")" + 
				" from OUVIDORIA_PESQUISA_SATISFACAO p, OUVIDORIA_ATENDIMENTO a, OUVIDORIA_SATISFACAO_RESPOSTAS r, OUVIDORIA_SATISFACAO_PERGUNTAS pp " + 
				" where " + 
				" a.INATENDIMENTOID = p.IN_ID_ATENDIMENTO " + 
				" and pp.IN_ORGAO = a.INCODIGOORGAO " + 
				" and pp.IN_SATISFACAO_PERGUNTA_ID = r.IN_SATISFACAO_PERGUNTAS " + 
				" and p.IN_ID_RESPOSTA = r.IN_SATISFACAO_RESPOSTAS_ID " + 
				" and a.SMSTATUS = 1 " + 
				" and a.SMSTATUSATENDIMENTO = 1 " + 
				" and a.INSATISFAZ = 1 " + 
				" and a.INCODIGOORGAO = ? " + 
				" and a.SMANOATENDIMENTO = ? " + 
				" and r.IN_ATIVO = 0 " + 
				" and pp.VA_DESCRICAO = 'Como você avalia o serviço prestado pela Ouvidoria?' " + 
				" group by r.VA_DESCRICAO ";
				
				Query query =  manager.createNativeQuery(q);

		query.setParameter(1, orgao);
		query.setParameter(2, ano);
		query.setParameter(3, orgao);
		query.setParameter(4, ano);
	     
		@SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
		List<AtendimentoNatureza> atendimentoNatureza = new ArrayList<>(lista.size());
		BigDecimal percentual = null;
		
		for (int i = 0; i < lista.size(); i++) {
    		Object obj = lista.get(i);
			Object[] itensObj = (Object[])obj;
			percentual = new BigDecimal((Integer.parseInt(itensObj[1].toString())*100f)/Integer.parseInt(itensObj[2].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			atendimentoNatureza.add(new AtendimentoNatureza(itensObj[0].toString(), percentual.doubleValue()));
		}
        return atendimentoNatureza;        
	}





	@Override
	public List<AtendimentoNatureza> getQtdAtendimentosNaturezaDashboard(Long orgao, Integer mes, Integer ano) {
		String q = "SELECT N.VADESCRICAO, COUNT(*) AS TOTAL FROM OUVIDORIA_ATENDIMENTO A, " + 
				" OUVIDORIA_NATUREZA N " +
				" WHERE " + 
				" MONTH(DACRIACAO) = ? " +
				" AND YEAR(DACRIACAO) = ? " + 
				" AND A.INCODIGOORGAO = ? " + 
				" AND A.SMSTATUS <> 2 " +
				" AND N.INCODIGONATUREZA = A.INCODIGONATUREZA " + 
				" GROUP BY N.VADESCRICAO " +
				" ORDER BY TOTAL DESC";
				
				Query query =  manager.createNativeQuery(q);

		query.setParameter(1, mes);
		query.setParameter(2, ano);
		query.setParameter(3, orgao);
	     
		@SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
		List<AtendimentoNatureza> atendimentoNatureza = new ArrayList<>(lista.size());
		
		for (int i = 0; i < lista.size(); i++) {
    		Object obj = lista.get(i);
			Object[] itensObj = (Object[])obj;
			atendimentoNatureza.add(new AtendimentoNatureza(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString())));
		}
        return atendimentoNatureza;        
	}





	@Override
    public List<AtendimentoNatureza> getQtdAtendimentosNatureza(Long orgao, Integer ano, Integer totalAtendimentos) {
    	
		String q = "SELECT N.VADESCRICAO, COUNT(*) AS TOTAL FROM OUVIDORIA_ATENDIMENTO A, " + 
				" OUVIDORIA_NATUREZA N " +
				" WHERE " + 
				" YEAR(DACRIACAO) = ? " + 
				" AND A.INCODIGOORGAO = ? " + 
				" AND A.SMSTATUS <> 2 " +
				" AND N.INCODIGONATUREZA = A.INCODIGONATUREZA " + 
				" GROUP BY N.VADESCRICAO " +
				" ORDER BY TOTAL DESC";
				
				Query query =  manager.createNativeQuery(q);

		query.setParameter(1, ano);
		query.setParameter(2, orgao);
	     
		@SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
		List<AtendimentoNatureza> atendimentoNatureza = new ArrayList<>(lista.size());
		BigDecimal percentual = null;
		
		for (int i = 0; i < lista.size(); i++) {
    		Object obj = lista.get(i);
			Object[] itensObj = (Object[])obj;
			percentual = new BigDecimal((Integer.parseInt(itensObj[1].toString())*100f)/totalAtendimentos).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			atendimentoNatureza.add(new AtendimentoNatureza(itensObj[0].toString(), percentual.doubleValue()));
		}
        return atendimentoNatureza;        
    	
    }
	




	@Override
	public List<AtendimentoNatureza> getQtdAtendimentosNatureza(Long orgao, Integer status, Integer mes, Integer ano,
			Long area, Long natureza) {
		String q = "SELECT N.VADESCRICAO, COUNT(*) AS TOTAL FROM OUVIDORIA_ATENDIMENTO A, " + 
				" OUVIDORIA_NATUREZA N " +
				" WHERE " + 
				" YEAR(DACRIACAO) = ? " + 
				" AND A.INCODIGOORGAO = ? " + 
				" AND A.SMSTATUS <> ? " +
				" AND N.INCODIGONATUREZA = A.INCODIGONATUREZA " + 
				" AND A.INCODIGOAREA = ? " +
		        " AND A.INCODIGONATUREZA = ? ";
    	
		     if (mes != 0) {
		    	 q = q + " AND MONTH(DACRIACAO) = ? ";
		     }
		     
		     q = q +
				" GROUP BY N.VADESCRICAO " +
				" ORDER BY TOTAL DESC";
				
				Query query =  manager.createNativeQuery(q);

		query.setParameter(1, ano);
		query.setParameter(2, orgao);
		query.setParameter(3, status);
		query.setParameter(4, area);
		query.setParameter(5, natureza);

	     if (mes != 0) {
    	 	query.setParameter(6, mes);
	     }
	     
		@SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
		List<AtendimentoNatureza> atendimentoNatureza = new ArrayList<>(lista.size());
		for (int i = 0; i < lista.size(); i++) {
    		Object obj = lista.get(i);
			Object[] itensObj = (Object[])obj;
			atendimentoNatureza.add(new AtendimentoNatureza(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString())));
		}
        return atendimentoNatureza;        
	}





	@Override
	public List<DadosGrafico> getQtdAtendimentos(Long orgao, Integer ano) {
		
		String q = "SELECT MONTH(DACRIACAO), COUNT(*) AS TOTAL FROM OUVIDORIA_ATENDIMENTO A " +
				" WHERE " + 
				" YEAR(DACRIACAO) = ? " + 
				" AND A.INCODIGOORGAO = ? " +
				" AND A.SMSTATUS <> 2 " +
				" GROUP BY MONTH(DACRIACAO) " + 
				" ORDER BY MONTH(DACRIACAO) ";
		
		Query query =  manager.createNativeQuery(q);
		query.setParameter(1, ano);
		query.setParameter(2, orgao);
		
		@SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
		List<DadosGrafico> dados = new ArrayList<>(lista.size());
		for (int i = 0; i < lista.size(); i++) {
    		Object obj = lista.get(i);
			Object[] itensObj = (Object[])obj;
			dados.add(new DadosGrafico(MesEnum.pegarDescricao(Integer.parseInt(itensObj[0].toString())).getDescricao(), Double.parseDouble(itensObj[1].toString()), ""));
		}
        return dados;        
	}



	@Override
	public List<AtendimentoNatureza> getQtdAtendimentosSecretaria(Long orgao, Integer status, Integer mes,
			Integer ano) {
		
		String q = "SELECT TOP 10 AR.VADESCRICAO, COUNT(*) AS TOTAL,  A.INCODIGOAREA FROM OUVIDORIA_ATENDIMENTO A, " +
				" OUVIDORIA_AREAASSUNTO AR " + 
				" WHERE " + 
				" YEAR(DACRIACAO) = ? " + 
				" AND A.INCODIGOORGAO = ? " +
				" AND A.SMSTATUS <> ? " +
				" AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				" AND AR.INCODIGOAREA = A.INCODIGOAREA "; 
				//" AND AR.INCODIGOAREA > ? ";
		
		if (mes != 0) {
			q = q + " AND MONTH(DACRIACAO) = ? ";
		}
		
		q = q +
				" GROUP BY AR.VADESCRICAO,  A.INCODIGOAREA " + 
				" ORDER BY TOTAL DESC ";
		
		Query query =  manager.createNativeQuery(q);
		query.setParameter(1, ano);
		query.setParameter(2, orgao);
		query.setParameter(3, status);
		//query.setParameter(4, 0);
		
		if (mes != 0) {
			query.setParameter(4, mes);
		}
		@SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
		List<AtendimentoNatureza> atendimentoNatureza = new ArrayList<>(lista.size());
		for (int i = 0; i < lista.size(); i++) {
    		Object obj = lista.get(i);
			Object[] itensObj = (Object[])obj;
			atendimentoNatureza.add(new AtendimentoNatureza(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), Long.parseLong(itensObj[2].toString())));
		}
        return atendimentoNatureza;        
	}
	
	
	
	
	
	
	@Override
	public StackedColumn getQtdAtendimentosSecretariaNatureza(Long orgao, Integer status, Integer mes,
			Integer ano) {
		StackedColumn stackedColumn = new StackedColumn();
		List<AtendimentoNatureza> lista = getQtdAtendimentosSecretaria(orgao, status, mes, ano);
		List<Label> listaLabel = new ArrayList<>();
		for (AtendimentoNatureza atendimentoNatureza : lista) {
			listaLabel.add(new Label(atendimentoNatureza.getLabel()));
		}
		List<AtendimentoNatureza> listaNatureza = null;
		List<SeriesName> listaSeries = new ArrayList<>();
		List<Data> listaData = null;
		List<Natureza> listaNaturezas = naturezaRepository.findAll();
			for (Natureza natureza : listaNaturezas) {
				listaData = new ArrayList<>();
				for (AtendimentoNatureza atendimentoNatureza : lista) {
					listaNatureza = getQtdAtendimentosNatureza(orgao, status, mes, ano, atendimentoNatureza.getArea(), natureza.getId());
					if (listaNatureza.isEmpty()) {
						listaData.add(new Data(0d));
					}else {
						listaData.add(new Data(listaNatureza.get(0).getValue()));
					}
				}
				listaSeries.add(new SeriesName(natureza.getDescricao(), listaData));
			}
			stackedColumn.setLabel(listaLabel);
			stackedColumn.setSeriesName(listaSeries);
		return stackedColumn;
	}


	@Override
	public List<AtendimentoNatureza> getQtdAtendimentosAssunto(Long orgao, Integer status, Integer ano) {
		Query query =  manager.createNativeQuery("SELECT TOP 15 ASS.VADESCRICAO, COUNT(*) AS TOTAL,  A.INCODIGOASSUNTONOVO FROM OUVIDORIA_ATENDIMENTO A, " +
				" OUVIDORIA_ASSUNTO ASS " + 
				" WHERE YEAR(DACRIACAO) = ? " + 
				" AND A.INCODIGOORGAO = ? " +
				" AND A.SMSTATUS <> ? " +
				" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				" AND ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " + 
				" AND A.INCODIGOASSUNTONOVO > ? " +
				" GROUP BY ASS.VADESCRICAO,  A.INCODIGOASSUNTONOVO " + 
				" ORDER BY TOTAL DESC ");
		query.setParameter(1, ano);
		query.setParameter(2, orgao);
		query.setParameter(3, status);
		query.setParameter(4, 0);
		@SuppressWarnings("unchecked")
		List<Object> lista = query.getResultList();
		List<AtendimentoNatureza> atendimentoNatureza = new ArrayList<>(lista.size());
		for (int i = 0; i < lista.size(); i++) {
    		Object obj = lista.get(i);
			Object[] itensObj = (Object[])obj;
			atendimentoNatureza.add(new AtendimentoNatureza(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString())));
		}
        return atendimentoNatureza;       
	}

	@Override
	public Atendimento consultaAtendimento(Long codigoAtendimento) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Atendimento> criteria = builder.createQuery(Atendimento.class);
		Root<Atendimento> root = criteria.from(Atendimento.class);
		Predicate predicates = builder.equal(root.get("id"), codigoAtendimento);
		criteria.where(predicates);
		TypedQuery<Atendimento> query = manager.createQuery(criteria);
		
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}

	
	@Override
	public Atendimento pesquisarNumeroProtocolo(String numeroProtocolo, Long orgao) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Atendimento> criteria = builder.createQuery(Atendimento.class);
		Root<Atendimento> root = criteria.from(Atendimento.class);
		AtendimentoFilter filtro = new AtendimentoFilter();
		filtro.setProtocolo(numeroProtocolo);
		Predicate[] predicates = criarRestricoes(filtro, builder, root, orgao);
		criteria.where(predicates);
		TypedQuery<Atendimento> query = manager.createQuery(criteria);
		
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}
	
	@Override
	public Page<Atendimento> filtrar(Long orgao, AtendimentoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Atendimento> criteria = builder.createQuery(Atendimento.class);
		Root<Atendimento> root = criteria.from(Atendimento.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root, orgao);
		criteria.where(predicates);
		
		TypedQuery<Atendimento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		List<Atendimento> lista = query.getResultList();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getAssunto() != null)
				lista.get(i).setDescricaoAssunto(assuntoRepository.getById(lista.get(i).getAssunto()).getDescricao());
		}
		
		return new PageImpl<>(lista, pageable, total(filtro, orgao));
	}

	@Override
	@Transactional
	public Page<Atendimento> consultaNovasManifestacoes(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT a FROM Atendimento a where a.orgao = ?1 and a.status <> 2 "
				+ "and a.statusAtendimento = 0 and (a.dataConclusao = '1969-12-31 21:00:00.000' or a.dataConclusao is null)"
				+ "and (select count(e.id) from Encaminhamento e where e.atendimento = a.id and e.status = 0) = 0";
		TypedQuery<Atendimento> query = manager.createQuery(q, Atendimento.class);
		query.setParameter(1, orgao);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, totalRegistros);
	}
	

	@Override
	@Transactional
	public Page<Atendimento> consultaCanalDenuncias(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT a FROM Atendimento a where a.orgao = ?1 and a.status <> 2 "
				+ "and a.statusAtendimento = 0 and (a.dataConclusao = '1969-12-31 21:00:00.000' or a.dataConclusao is null) and natureza = ?2";
		TypedQuery<Atendimento> query = manager.createQuery(q, Atendimento.class);
		query.setParameter(1, orgao);
		query.setParameter(2, Atendimento.CANAL_DENUNCIA);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, totalRegistros);
	}

	@Override
	@Transactional
	public Page<Atendimento> consultaManifestacoesVencidas(Long orgao, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT a FROM Atendimento a where a.orgao = ?1 and a.status = 0 "
				+ "and a.statusAtendimento = 0 and a.dataPrazo < DATEADD(day, DATEDIFF(day, 0, GETDATE()), 0) order by a.id";
		TypedQuery<Atendimento> query = manager.createQuery(q, Atendimento.class);
		query.setParameter(1, orgao);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, totalRegistros);
	}


	@Override
	@Transactional
	public Page<Atendimento> consultaManifestacoesVencer(Long orgao, Integer diasVencer, Pageable pageable, Integer totalRegistros) {
		String q = "SELECT a FROM Atendimento a where a.orgao = ?1 and a.status = 0 "
				+ "and a.statusAtendimento = 0 and a.dataPrazo >= DATEADD(day, DATEDIFF(day, 0, GETDATE()), 0) and DATEDIFF(day, getDate(), a.dataPrazo) <= ?2 order by a.id";
		
		TypedQuery<Atendimento> query = manager.createQuery(q, Atendimento.class);
		query.setParameter(1, orgao);
		query.setParameter(2, diasVencer);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, totalRegistros);
	}


	@SuppressWarnings("deprecation")
	private Predicate[] criarRestricoes(AtendimentoFilter filtro, CriteriaBuilder builder,
			Root<Atendimento> root, Long orgao) {
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
//			predicates.add(
//					builder.equal(root.get("numeroProtocolo"), filtro.getProtocolo()));
			predicates.add(
			builder.equal(root.get("anoAtendimento"), filtro.getProtocolo().substring(0, 4)));
			predicates.add(builder.equal(root.get("sequencialOrgao"), filtro.getProtocolo().substring(4, filtro.getProtocolo().length())));
			
		}

		if (filtro.getTelefone() != null) {
			predicates.add(
			builder.equal(root.get("foneCelular"), filtro.getTelefone()));
			
		}

		if (filtro.getDocumento() != null) {
			predicates.add(
					builder.equal(root.get("numeroDocumento"), filtro.getDocumento()));
		}

		if (filtro.getPalavraChave() != null) {
			predicates.add(builder.like((root.get("descricaoOque")),  builder.lower(builder.literal("%"+filtro.getPalavraChave().toLowerCase()+"%"))));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Atendimento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(AtendimentoFilter filtro, Long orgao) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Atendimento> root = criteria.from(Atendimento.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root, orgao);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	@Override
	public String consultar(String campo, Long id, Long orgao) {
		Query query =  manager.createNativeQuery("SELECT " + campo + " FROM OUVIDORIA_ATENDIMENTO WHERE INATENDIMENTOID = ? AND INCODIGOORGAO = ? ");
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

	@Override
	public List<DetalheAtendimentoArea> getAtendimentoArea(Long orgao, Date dataInicial, Date dataFinal, String area, String status) {
		
	        
		   String q = "  SELECT DISTINCT(OA.INATENDIMENTOID), OA.INNUMEROATENDIMENTO , OA.SMANOATENDIMENTO, " + 
	                " AR.VADESCRICAO as 'area', OA.SMSTATUS as 'status', ASS.VADESCRICAO as 'assunto', " +
	                " N.VADESCRICAO as 'natureza', OA.INCODIGOORGAO, CONVERT(varchar, OA.DACRIACAO, 103) as 'data', CONVERT(varchar, OA.DACONCLUSAO , 103) as 'dataConclusao' " +
	                " FROM OUVIDORIA_ATENDIMENTO OA (NOLOCK)  " +
	                " INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = OA.INCODIGOASSUNTONOVO " +
	                " INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " + 
					" INNER JOIN OUVIDORIA_NATUREZA N ON N.INCODIGONATUREZA = OA.INCODIGONATUREZA " +
	                " WHERE (OA.INCODIGOORGAO = ?) " +
	                " and (OA.DAENTRADA >= ? ) AND (OA.DAENTRADA <= ? ) AND (OA.SMSTATUS <> 2) " + 
	                " AND ASS.INCODIGOORGAO = OA.INCODIGOORGAO " + 
	                " AND AR.INCODIGOORGAO = OA.INCODIGOORGAO ";
		   
		   if(!area.equals("")){
	          q = (new StringBuilder()).append(q).append(" AND AR.INCODIGOAREA = ? ").toString();
		   }
		   
		   if (!status.equals("")) {
			  if(status.equals("1")){
	                q = (new StringBuilder()).append(q).append(" AND (OA.DACONCLUSAO <> '1969-12-31 21:00:00.000') ").toString();
	          }else{
	                q = (new StringBuilder()).append(q).append(" AND (OA.DACONCLUSAO = '1969-12-31 21:00:00.000') ").toString();
	          }
		   }
		   
	       q = (new StringBuilder()).append(q).append(" GROUP BY OA.INATENDIMENTOID,OA.INNUMEROATENDIMENTO , OA.SMANOATENDIMENTO, " + 
							" AR.VADESCRICAO, OA.SMSTATUS, OA.SMSTATUSATENDIMENTO, " +
							" ASS.VADESCRICAO, N.VADESCRICAO, OA.INCODIGOORGAO, " + 
							" OA.DACRIACAO, OA.DACONCLUSAO   " +
							" ORDER BY CONVERT(varchar, OA.DACRIACAO, 103) ").toString();
	       
	       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   
		   
		   Query query =  manager.createNativeQuery(q);
		   query.setParameter(1, orgao);
		   query.setParameter(2, dateFormat.format(dataInicial));
		   query.setParameter(3, dateFormat.format(dataFinal));
		   
		   if(!area.equals("")){
			   query.setParameter(4, area);
		   }

		   @SuppressWarnings("unchecked")
		   List<Object> lista = query.getResultList();
		   List<DetalheAtendimentoArea> detalhe = new ArrayList<>(lista.size());
		   Atendimento atendimento = null;
		   
		   for (int i = 0; i < lista.size(); i++) {
	    		Object obj = lista.get(i);
				Object[] itensObj = (Object[])obj;
				atendimento = consultaAtendimento(Long.parseLong(itensObj[0].toString()));
				String prot = itensObj[2].toString() + itensObj[1].toString();
				detalhe.add(new DetalheAtendimentoArea(Long.parseLong(itensObj[0].toString()), prot,  itensObj[1].toString(), itensObj[2].toString(), itensObj[3].toString(), itensObj[4].toString().equals("0") ? "Aberto" : "Concluído",
						itensObj[5].toString(), itensObj[6].toString(), itensObj[8].toString(), itensObj[9].toString().equals("31/12/1969") ? "" : itensObj[9].toString(), atendimento.getDescricaoFatos(), atendimento.getObservacao()));
			}
		   
		return detalhe;
	}
	
	
	
	
	

	@Override
	public List<RelatorioPeriodo> getAtendimentoPeriodo(Long orgao, Date dataInicial, Date dataFinal) {
		   String q = "  SELECT " + 
				" A.INATENDIMENTOID, " +
		   		" A.INNUMEROATENDIMENTO, " + 
		   		" A.SMANOATENDIMENTO, " + 
		   		" A.VANUMPROTOCOLO, " + 
		   		" CONVERT(varchar, A.DAENTRADA, 103) AS 'DATA', " + 
		   		" ASS.VADESCRICAO AS 'ASSUNTO', " + 
		   		" N.VADESCRICAO AS 'NATUREZA', " + 
		   		" A.SMSTATUSATENDIMENTO AS 'STATUS'," + 
		   		" CONVERT(varchar, A.DADATA_PRAZO, 103) AS 'DATA PRAZO', " + 
		   		" CONVERT(varchar, A.DACONCLUSAO, 103) AS 'DATA CONCLUSAO', " + 
		   		" A.INSEQUENCIALORGAO " +
		   		" FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) , OUVIDORIA_ASSUNTO ASS (NOLOCK) , OUVIDORIA_NATUREZA N (NOLOCK) " + 
		   		" WHERE " + 
		   		" A.INCODIGOORGAO = ASS.INCODIGOORGAO " + 
		   		" AND A.INCODIGOASSUNTONOVO = ASS.INCODIGOASSUNTONOVO " + 
		   		" AND A.INCODIGONATUREZA = N.INCODIGONATUREZA " + 
		   		" AND (A.INCODIGOORGAO = ?) " + 
		   		" AND (A.DAENTRADA >= ?) AND (A.DAENTRADA <= ?) " + 
		   		" AND (A.SMSTATUS <> 2) " + 
		   		" ORDER BY A.SMANOATENDIMENTO, A.INNUMEROATENDIMENTO ASC ";
	       
	       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   
			Query query =  manager.createNativeQuery(q);
			query.setParameter(1, orgao);
			query.setParameter(2, dateFormat.format(dataInicial));
			query.setParameter(3, dateFormat.format(dataFinal));

			@SuppressWarnings("unchecked")
			List<Object> lista = query.getResultList();
			List<RelatorioPeriodo> detalhe = new ArrayList<>(lista.size());
			for (int i = 0; i < lista.size(); i++) {
	    		Object obj = lista.get(i);
				Object[] itensObj = (Object[])obj;
				String prot = itensObj[2].toString() + itensObj[10].toString();
				detalhe.add(new RelatorioPeriodo(Long.parseLong(itensObj[0].toString()), prot,  itensObj[4].toString(), itensObj[5].toString(), itensObj[6].toString(), 
						itensObj[7].toString().equals("0") ? "Aberto" : "Concluído",
								itensObj[9].toString().equals("31/12/1969") ? "" : itensObj[9].toString(), itensObj[8].toString()));
			}
		return detalhe;
	}

	@Override
	public List<DetalheAtendimentoArea> getAtendimentoArea(Long orgao, Date dataInicial, Date dataFinal, String area) {
		   String q = " SELECT A.VANUMPROTOCOLO as 'numero', CONVERT(NVARCHAR, A.DACRIACAO, 103) as 'data', CONVERT(NVARCHAR, A.DACONCLUSAO, 103) as 'data_conclusao', ASS.VADESCRICAO as 'assunto', N.VADESCRICAO as 'natureza', AA.VADESCRICAO as 'area', O.VADESCRICAO as 'origem', "
		   		+ " P.VADESCRICAO as 'prioridade', T.VA_DESCRICAO as 'tipo', B.VADESCRICAO " + 
		   		" FROM OUVIDORIA_ATENDIMENTO A INNER JOIN  OUVIDORIA_ASSUNTO ASS ON A.INCODIGOASSUNTO = ASS.INCODIGOASSUNTO AND A.INCODIGOORGAO = ASS.INCODIGOORGAO " + 
		   		" INNER JOIN OUVIDORIA_NATUREZA N ON A.INCODIGONATUREZA = N.INCODIGONATUREZA " + 
		   		" INNER JOIN OUVIDORIA_AREAASSUNTO AA ON A.INCODIGOAREA = AA.INCODIGOAREAASSUNTO AND AA.INCODIGOORGAO = A.INCODIGOORGAO " + 
		   		" INNER JOIN OUVIDORIA_ORIGEM_MANIFESTACAO O ON A.INCODIGOORIGEMMANIFESTACAO = O.INCODIGOORIGEMMANIFESTACAO " + 
		   	    " INNER JOIN OUVIDORIA_BAIRRO_OCORRENCIA B ON B.IN_ID_BAIRRO_OCORRENCIA = A.IN_BAIRRO_OCORRENCIA " +
		   		" INNER JOIN OUVIDORIA_PRIORIZACAO P ON A.INCODIGOPRIORIZACAO = P.INCODIGOPRIORIZACAO " + 
		   		" INNER JOIN OUVIDORIA_TIPO_MANIFESTANTE T ON A.INTIPO_MANIFESTANTE = T.IN_TIPO_MANIFESTANTE_ID AND T.IN_ORGAO = A.INCODIGOORGAO " + 
		   		" WHERE A.DACRIACAO >= ? AND A.DACRIACAO <= ? " + 
		   		" AND SMSTATUS <> 2 "; 
		   
		   if(area != null){
	          q = (new StringBuilder()).append(q).append(" AND AR.INCODIGOAREAASSUNTO = ? ").toString();
		   }
		   
		   q = (new StringBuilder()).append(q).append(" ORDER BY AA.VADESCRICAO, A.DACRIACAO, ASS.VADESCRICAO ").toString();
	       
	       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   
		   
			Query query =  manager.createNativeQuery(q);
			query.setParameter(1, dateFormat.format(dataInicial));
			query.setParameter(2, dateFormat.format(dataFinal));
		    if(area != null){
		    	query.setParameter(3, area);
		    }

			@SuppressWarnings("unchecked")
			List<Object> lista = query.getResultList();
			List<DetalheAtendimentoArea> detalhe = new ArrayList<>(lista.size());
			for (int i = 0; i < lista.size(); i++) {
	    		Object obj = lista.get(i);
				Object[] itensObj = (Object[])obj;
				detalhe.add(new DetalheAtendimentoArea(itensObj[0].toString(),  itensObj[1].toString(), itensObj[2].toString().equals("31/12/1969") ? "" : itensObj[2].toString(), itensObj[3].toString(), itensObj[4].toString(),
						itensObj[5].toString(), itensObj[6].toString(), itensObj[7].toString(), itensObj[8].toString(), itensObj[9].toString()));
			}
		return detalhe;
	}

	@Override
	public Integer qtdHistoricoUsuario(Long codigoOrgao, Long tipoDocumento, String documento, String email, Long numero) {
		  String sql = "";
		  boolean existsCondicao = false;
		  sql = " SELECT COUNT(*) FROM OUVIDORIA_ATENDIMENTO (NOLOCK) WHERE INCODIGOORGAO = ? AND INATENDIMENTOID <> ? AND INCODIGOASSUNTONOVO IS NOT NULL ";
		  if(documento != null && !documento.equals("") && !documento.equals("Anônimo")){
			  existsCondicao = true;
			  if(email != null && !email.trim().equals("")){
				  sql +=" AND (VAEMAIL LIKE '"+email+"' OR "; 
			  }else{
				  sql+=" AND ( ";
			  }
			  sql +=" (INCODIGOTIPODOCUMENTO = "+tipoDocumento+" AND VACPF LIKE '"+documento+"'))";
		  }else{
			  if(email != null && !email.trim().equals("")){
				  existsCondicao = true;
				  sql +=" AND (VAEMAIL LIKE '"+email+"')";
			  }
		  }
		    if (existsCondicao) {
				Query query =  manager.createNativeQuery(sql);
				query.setParameter(1, codigoOrgao);
				query.setParameter(2, numero);
			    @SuppressWarnings("unchecked")
				List<Object> lista = query.getResultList();
		        if (!lista.isEmpty()) {
		        	return (Integer )lista.get(0);
		        }
		    }
		return null;
	}
	

	@Override
	public List<HistoricoUsuarioDto> historicoUsuario(Long codigoOrgao, Long tipoDocumento, String documento, String email, Long numero) {
		  String sql = "";
		  boolean existeCondicao = false;
		  sql = " SELECT  INATENDIMENTOID, VANUMPROTOCOLO, CONVERT(varchar(10), OUVIDORIA_ATENDIMENTO.DACRIACAO, 103), OUVIDORIA_ASSUNTO.VADESCRICAO, "
		  		+ "SMSTATUS, SMANOATENDIMENTO, INSEQUENCIALORGAO FROM OUVIDORIA_ATENDIMENTO (NOLOCK), OUVIDORIA_ASSUNTO  WHERE OUVIDORIA_ASSUNTO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO " + 
		  		" AND OUVIDORIA_ATENDIMENTO.INCODIGOASSUNTONOVO = OUVIDORIA_ASSUNTO.INCODIGOASSUNTONOVO AND OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = ? AND OUVIDORIA_ATENDIMENTO.INATENDIMENTOID <> ? ";
		  if(email != null && !email.trim().equals("")){
			  existeCondicao = true;
			  sql +=" AND (VAEMAIL LIKE '"+email+"' ";
		  }
		  if(documento != null && !documento.equals("") && !documento.equals("Anônimo")){
			  existeCondicao = true;
			  if(email != null && !email.trim().equals("")){
				  sql+=" OR ";
			  }else{
				  sql+=" AND (";
			  } 
			  sql +=" (INCODIGOTIPODOCUMENTO = "+tipoDocumento+" AND VACPF LIKE '"+documento+"') )";
		  }else{
			  sql+=")";
		  }
		  List<HistoricoUsuarioDto> historicoUsuario = null;
		  if (existeCondicao) {
				Query query =  manager.createNativeQuery(sql);
				query.setParameter(1, codigoOrgao);
				query.setParameter(2, numero);
				@SuppressWarnings("unchecked")
				List<Object> lista = query.getResultList();
				historicoUsuario = new ArrayList<>(lista.size());
				for (int i = 0; i < lista.size(); i++) {
		    		Object obj = lista.get(i);
					Object[] itensObj = (Object[])obj;
					historicoUsuario.add(new HistoricoUsuarioDto(Long.parseLong(itensObj[0].toString()), 
							itensObj[5].toString()+itensObj[6].toString(),  itensObj[2].toString(), itensObj[3].toString(), itensObj[4].toString().equals("0") ? "Aberto" : itensObj[4].toString().equals("1") ? "Concluído" : "Cancelado"));
				}
			}
        return historicoUsuario;        
	}
	
	
	@Override
	public List<DadosGrafico> getRelatorioNatureza(Long orgao, Date dataInicial, Date dataFinal) {
		   String q = "  SELECT OUVIDORIA_NATUREZA.VADESCRICAO as 'descricao', " + 
		   		" Count(OUVIDORIA_ATENDIMENTO.INCODIGONATUREZA) as 'total', " + 
		   		" CAST((CAST(Count(OUVIDORIA_ATENDIMENTO.INCODIGONATUREZA) AS decimal(10,2))/CAST(TOTAL.todos AS decimal(10,2)) * 100) AS decimal(10,2)) as 'percentual', " + 
		   		" OUVIDORIA_NATUREZA.INCODIGONATUREZA as 'codigo' " + 
		   		" FROM OUVIDORIA_NATUREZA(NOLOCK), OUVIDORIA_ATENDIMENTO(NOLOCK), " + 
		   		" (SELECT Count(OA.INCODIGONATUREZA) as todos FROM OUVIDORIA_ATENDIMENTO OA(NOLOCK) " + 
		   		" WHERE (OA.INCODIGOORGAO = ?) " + 
		   		" AND (OA.DAENTRADA >= ?) " + 
		   		" AND (OA.DAENTRADA <= ?) " + 
		   		" AND (OA.SMSTATUS <> 2))TOTAL " + 
		   		" WHERE (OUVIDORIA_NATUREZA.INCODIGONATUREZA = OUVIDORIA_ATENDIMENTO.INCODIGONATUREZA) " + 
		   		" AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = ?) " + 
		   		" AND (OUVIDORIA_ATENDIMENTO.DAENTRADA >= ? )" + 
		   		" AND (OUVIDORIA_ATENDIMENTO.DAENTRADA <= ? ) " + 
		   		" AND (OUVIDORIA_ATENDIMENTO.SMSTATUS <> 2) " + 
		   		" GROUP BY OUVIDORIA_NATUREZA.VADESCRICAO, TOTAL.todos, OUVIDORIA_NATUREZA.INCODIGONATUREZA " + 
		   		" ORDER BY OUVIDORIA_NATUREZA.VADESCRICAO, TOTAL.todos, OUVIDORIA_NATUREZA.INCODIGONATUREZA ";
		       
		       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
				Query query =  manager.createNativeQuery(q);
				query.setParameter(1, orgao);
				query.setParameter(2, dateFormat.format(dataInicial));
				query.setParameter(3, dateFormat.format(dataFinal));
				query.setParameter(4, orgao);
				query.setParameter(5, dateFormat.format(dataInicial));
				query.setParameter(6, dateFormat.format(dataFinal));

				@SuppressWarnings("unchecked")
				List<Object> lista = query.getResultList();
				List<DadosGrafico> dados = new ArrayList<>(lista.size());
				DecimalFormat frmt = new DecimalFormat();
				frmt.setMaximumFractionDigits(2);
				for (int i = 0; i < lista.size(); i++) {
		    		Object obj = lista.get(i);
					Object[] itensObj = (Object[])obj;
					dados.add(new DadosGrafico(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), itensObj[2].toString()));
				}
			return dados;
	}

	
	@Override
	public List<DadosGrafico> getRelatorioAssunto(Long orgao, Date dataInicial, Date dataFinal) {
		   String q = "SELECT DISTINCT(OUVIDORIA_ASSUNTO.VADESCRICAO), " + 
		   		"  Count(OUVIDORIA_ATENDIMENTO.INCODIGOASSUNTONOVO) as CONT, " + 
		   		"  CAST(COUNT(OUVIDORIA_ATENDIMENTO.INCODIGOASSUNTONOVO) AS  DECIMAL(12,2))*100 " + 
		   		"  / (SELECT Count(OUVIDORIA_ATENDIMENTO.INCODIGOASSUNTONOVO) " + 
		   		"  FROM OUVIDORIA_ASSUNTO(NOLOCK), OUVIDORIA_ATENDIMENTO(NOLOCK) " + 
		   		"  WHERE (OUVIDORIA_ASSUNTO.INCODIGOASSUNTONOVO = OUVIDORIA_ATENDIMENTO.INCODIGOASSUNTONOVO) " + 
		   		"  AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = ? ) " + 
		   		"  AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = OUVIDORIA_ASSUNTO.INCODIGOORGAO ) " + 
		   		"  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA >= ? ) " + 
		   		"  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA <= ? ) ) " + 
		   		"  FROM OUVIDORIA_ASSUNTO(NOLOCK), OUVIDORIA_ATENDIMENTO(NOLOCK) " + 
		   		"  WHERE (OUVIDORIA_ASSUNTO.INCODIGOASSUNTONOVO = OUVIDORIA_ATENDIMENTO.INCODIGOASSUNTONOVO) " + 
		   		"  AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = ? ) " + 
		   		"  AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = OUVIDORIA_ASSUNTO.INCODIGOORGAO ) " + 
		   		"  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA >= ? )  " + 
		   		"  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA <= ? ) " + 
		   		"  AND (OUVIDORIA_ATENDIMENTO.SMSTATUS <> 2) " + 
		   		"  GROUP BY OUVIDORIA_ASSUNTO.VADESCRICAO " + 
		   		"  ORDER BY CONT DESC  ";
		       
		       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
				Query query =  manager.createNativeQuery(q);
				query.setParameter(1, orgao);
				query.setParameter(2, dateFormat.format(dataInicial));
				query.setParameter(3, dateFormat.format(dataFinal));
				query.setParameter(4, orgao);
				query.setParameter(5, dateFormat.format(dataInicial));
				query.setParameter(6, dateFormat.format(dataFinal));

				@SuppressWarnings("unchecked")
				List<Object> lista = query.getResultList();
				List<DadosGrafico> dados = new ArrayList<>(lista.size());
				DecimalFormat frmt = new DecimalFormat();
				frmt.setMaximumFractionDigits(2);
				for (int i = 0; i < lista.size(); i++) {
		    		Object obj = lista.get(i);
					Object[] itensObj = (Object[])obj;
					dados.add(new DadosGrafico(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), NumberTools.format(Double.parseDouble(itensObj[2].toString()))));
				}
			return dados;
	}


	@Override
	public List<DadosGrafico> getRelatorioPriorizacao(Long orgao, Date dataInicial, Date dataFinal) {
		  String q = (" SELECT OUVIDORIA_PRIORIZACAO.VADESCRICAO as 'descricao', " +
                  " Count(OUVIDORIA_ATENDIMENTO.INCODIGOPRIORIZACAO) as 'total', " +
                  " CAST((CAST(Count(OUVIDORIA_ATENDIMENTO.INCODIGOPRIORIZACAO) AS decimal(10,2))/CAST(TOTAL.todos AS decimal(10,2)) * 100) AS decimal(10,2)) as 'percentual', " +
                  " OUVIDORIA_PRIORIZACAO.INCODIGOPRIORIZACAO as 'codigo'" +
                  " FROM OUVIDORIA_ATENDIMENTO(NOLOCK),OUVIDORIA_PRIORIZACAO(NOLOCK), " +
                  " (SELECT Count(OA.INCODIGOPRIORIZACAO) todos "+
                  " FROM OUVIDORIA_ATENDIMENTO OA(NOLOCK) "+
                  " WHERE (OA.INCODIGOORGAO = ?) AND" +
                  "  (OA.DAENTRADA >= ?) " +
                  "  AND (OA.DAENTRADA <= ?) " +
                  " AND (OA.SMSTATUS <> 2)) TOTAL " +
                  " WHERE (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = ?) AND" +
                  "  (OUVIDORIA_ATENDIMENTO.DAENTRADA >= ?) " +
                  "  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA <= ?) " +
                  " AND (OUVIDORIA_ATENDIMENTO.SMSTATUS <> 2) " +
                  "  AND OUVIDORIA_ATENDIMENTO.INCODIGOPRIORIZACAO = OUVIDORIA_PRIORIZACAO.INCODIGOPRIORIZACAO " +
                  " GROUP BY OUVIDORIA_PRIORIZACAO.VADESCRICAO,TOTAL.todos, OUVIDORIA_PRIORIZACAO.INCODIGOPRIORIZACAO " +
                  " ORDER BY OUVIDORIA_PRIORIZACAO.VADESCRICAO, TOTAL.todos, OUVIDORIA_PRIORIZACAO.INCODIGOPRIORIZACAO ");

		       
		       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
				Query query =  manager.createNativeQuery(q);
				query.setParameter(1, orgao);
				query.setParameter(2, dateFormat.format(dataInicial));
				query.setParameter(3, dateFormat.format(dataFinal));
				query.setParameter(4, orgao);
				query.setParameter(5, dateFormat.format(dataInicial));
				query.setParameter(6, dateFormat.format(dataFinal));

				@SuppressWarnings("unchecked")
				List<Object> lista = query.getResultList();
				List<DadosGrafico> dados = new ArrayList<>(lista.size());
				DecimalFormat frmt = new DecimalFormat();
				frmt.setMaximumFractionDigits(2);
				for (int i = 0; i < lista.size(); i++) {
		    		Object obj = lista.get(i);
					Object[] itensObj = (Object[])obj;
					dados.add(new DadosGrafico(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), NumberTools.format(Double.parseDouble(itensObj[2].toString()))));
				}
			return dados;
	}

	@Override
	public List<DadosGrafico> getRelatorioOrigem(Long orgao, Date dataInicial, Date dataFinal) {
	    // prepara a consulta OUVIDORIA_
	    String q = ("SELECT OUVIDORIA_ORIGEM_MANIFESTACAO.VADESCRICAO as 'descricao', " +
                  " Count(OUVIDORIA_ATENDIMENTO.INCODIGOORIGEMMANIFESTACAO) as CONT, " +
                  " CAST(COUNT(OUVIDORIA_ATENDIMENTO.INCODIGOORIGEMMANIFESTACAO) AS  DECIMAL(12,2))*100 " +
                  " / (SELECT Count(OUVIDORIA_ATENDIMENTO.INCODIGOORIGEMMANIFESTACAO) as 'percentual' " +
                  "   FROM OUVIDORIA_ORIGEM_MANIFESTACAO(NOLOCK), OUVIDORIA_ATENDIMENTO(NOLOCK), GLOB_ORGAO(NOLOCK) " +
                  "   WHERE (OUVIDORIA_ORIGEM_MANIFESTACAO.INCODIGOORIGEMMANIFESTACAO = OUVIDORIA_ATENDIMENTO.INCODIGOORIGEMMANIFESTACAO) " +
                  "     AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = ?) " +
                  "     AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = GLOB_ORGAO.CODIGO_ORGAO) " +
                  "     AND (OUVIDORIA_ATENDIMENTO.DAENTRADA >= ?) " +
                  "     AND (OUVIDORIA_ATENDIMENTO.DAENTRADA <= ?) ), " +
                  " OUVIDORIA_ORIGEM_MANIFESTACAO.INCODIGOORIGEMMANIFESTACAO " +
                  " FROM OUVIDORIA_ORIGEM_MANIFESTACAO(NOLOCK), OUVIDORIA_ATENDIMENTO(NOLOCK), GLOB_ORGAO(NOLOCK) " +
                  " WHERE (OUVIDORIA_ORIGEM_MANIFESTACAO.INCODIGOORIGEMMANIFESTACAO = OUVIDORIA_ATENDIMENTO.INCODIGOORIGEMMANIFESTACAO) " +
                  "  AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = ?) " +
                  "  AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = GLOB_ORGAO.CODIGO_ORGAO) " +
                  "  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA >= ?) " +
                  "  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA <= ?) " +
                  " AND (OUVIDORIA_ATENDIMENTO.SMSTATUS <> 2) " +
                  " GROUP BY OUVIDORIA_ORIGEM_MANIFESTACAO.VADESCRICAO, OUVIDORIA_ORIGEM_MANIFESTACAO.INCODIGOORIGEMMANIFESTACAO " +
                  " ORDER BY CONT DESC ");
		       
		       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
				Query query =  manager.createNativeQuery(q);
				query.setParameter(1, orgao);
				query.setParameter(2, dateFormat.format(dataInicial));
				query.setParameter(3, dateFormat.format(dataFinal));
				query.setParameter(4, orgao);
				query.setParameter(5, dateFormat.format(dataInicial));
				query.setParameter(6, dateFormat.format(dataFinal));

				@SuppressWarnings("unchecked")
				List<Object> lista = query.getResultList();
				List<DadosGrafico> dados = new ArrayList<>(lista.size());
				DecimalFormat frmt = new DecimalFormat();
				frmt.setMaximumFractionDigits(2);
				for (int i = 0; i < lista.size(); i++) {
		    		Object obj = lista.get(i);
					Object[] itensObj = (Object[])obj;
					dados.add(new DadosGrafico(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), NumberTools.format(Double.parseDouble(itensObj[2].toString()))));
				}
			return dados;
	}

	
	@Override
	public List<DadosGrafico> getRelatorioAreaAssunto(Long orgao, Date dataInicial, Date dataFinal) {
	    // prepara a consulta OUVIDORIA_
	    // prepara a consulta
	    String q = ("SELECT OUVIDORIA_AREAASSUNTO.VADESCRICAO as 'descricao', " +
                  "Count(OUVIDORIA_ATENDIMENTO.INCODIGOASSUNTONOVO), OUVIDORIA_AREAASSUNTO.INCODIGOAREA as 'id'" +
                  "FROM OUVIDORIA_AREAASSUNTO(NOLOCK), OUVIDORIA_ASSUNTO(NOLOCK), OUVIDORIA_ATENDIMENTO(NOLOCK) " +
                  "WHERE (OUVIDORIA_AREAASSUNTO.INCODIGOAREA = OUVIDORIA_ASSUNTO.INCODIGOAREANOVO) " +
                  "  AND (OUVIDORIA_ASSUNTO.INCODIGOASSUNTONOVO = OUVIDORIA_ATENDIMENTO.INCODIGOASSUNTONOVO) " +
                  "  AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = ?) " +
                  "  AND OUVIDORIA_ASSUNTO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO  "+
                  "  AND OUVIDORIA_AREAASSUNTO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO "+
                  "  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA >= ?) " +
                  "  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA <= ?) " +
                  "  AND (OUVIDORIA_ATENDIMENTO.SMSTATUS <> 2) " +
                  "GROUP BY OUVIDORIA_AREAASSUNTO.VADESCRICAO, OUVIDORIA_AREAASSUNTO.INCODIGOAREA ORDER BY Count(OUVIDORIA_ATENDIMENTO.INCODIGOASSUNTONOVO) DESC");
		       
		       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
				Query query =  manager.createNativeQuery(q);
				query.setParameter(1, orgao);
				query.setParameter(2, dateFormat.format(dataInicial));
				query.setParameter(3, dateFormat.format(dataFinal));

				@SuppressWarnings("unchecked")
				List<Object> lista = query.getResultList();
				List<DadosGrafico> dados = new ArrayList<>(lista.size());
				DecimalFormat frmt = new DecimalFormat();
				frmt.setMaximumFractionDigits(2);
				for (int i = 0; i < lista.size(); i++) {
		    		Object obj = lista.get(i);
					Object[] itensObj = (Object[])obj;
					dados.add(new DadosGrafico(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), null));
				}
			return dados;
	}
	
	
	@Override
	public List<DadosGrafico> getRelatorioTipoManifestante(Long orgao, Date dataInicial, Date dataFinal) {
	    // prepara a consulta OUVIDORIA_
	     String q = ("SELECT OUVIDORIA_TIPO_MANIFESTANTE.VA_DESCRICAO as 'descricao', " +
                " Count(OUVIDORIA_ATENDIMENTO.INTIPOMANIFESTANTENOVO) as 'total',"
                + "CAST(COUNT(OUVIDORIA_ATENDIMENTO.INTIPOMANIFESTANTENOVO) AS  DECIMAL(12,2))*100/(SELECT Count(OUVIDORIA_ATENDIMENTO.INTIPOMANIFESTANTENOVO) " +
	                                                  "   FROM OUVIDORIA_TIPO_MANIFESTANTE(NOLOCK), OUVIDORIA_ATENDIMENTO(NOLOCK) " +
	                                                  "   WHERE (OUVIDORIA_TIPO_MANIFESTANTE.INTIPOMANIFESTANTENOVO = OUVIDORIA_ATENDIMENTO.INTIPOMANIFESTANTENOVO) " +
	                                                  "     AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = ?) " +
	                                                  "     AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = OUVIDORIA_TIPO_MANIFESTANTE.IN_ORGAO) " +
	                                                  "     AND (OUVIDORIA_ATENDIMENTO.DAENTRADA >= ?) " +
	                                                  "     AND (OUVIDORIA_ATENDIMENTO.DAENTRADA <= ?) ) as 'percentual' , OUVIDORIA_TIPO_MANIFESTANTE.INTIPOMANIFESTANTENOVO as 'codigo'  " +
                "FROM OUVIDORIA_TIPO_MANIFESTANTE(NOLOCK), OUVIDORIA_ATENDIMENTO(NOLOCK) " +
                "WHERE(OUVIDORIA_TIPO_MANIFESTANTE.INTIPOMANIFESTANTENOVO = OUVIDORIA_ATENDIMENTO.INTIPOMANIFESTANTENOVO) " +
                "  AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = ?) " +
                "  AND OUVIDORIA_TIPO_MANIFESTANTE.IN_ORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO  "+
                "  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA >= ?) " +
                "  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA <= ?) " +
                "  AND (OUVIDORIA_ATENDIMENTO.SMSTATUS <> 2) " +
                "GROUP BY OUVIDORIA_TIPO_MANIFESTANTE.VA_DESCRICAO, OUVIDORIA_TIPO_MANIFESTANTE.INTIPOMANIFESTANTENOVO"
                + " ORDER BY Count(OUVIDORIA_ATENDIMENTO.INTIPOMANIFESTANTENOVO) DESC");
		       
		       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
				Query query =  manager.createNativeQuery(q);
				query.setParameter(1, orgao);
				query.setParameter(2, dateFormat.format(dataInicial));
				query.setParameter(3, dateFormat.format(dataFinal));
				query.setParameter(4, orgao);
				query.setParameter(5, dateFormat.format(dataInicial));
				query.setParameter(6, dateFormat.format(dataFinal));

				@SuppressWarnings("unchecked")
				List<Object> lista = query.getResultList();
				List<DadosGrafico> dados = new ArrayList<>(lista.size());
				DecimalFormat frmt = new DecimalFormat();
				frmt.setMaximumFractionDigits(2);
				for (int i = 0; i < lista.size(); i++) {
		    		Object obj = lista.get(i);
					Object[] itensObj = (Object[])obj;
					dados.add(new DadosGrafico(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), NumberTools.format(Double.parseDouble(itensObj[2].toString()))));
				}
			return dados;
	}
	
	

	@Override
	public List<DadosGrafico> getQtdAtendimentosResolutividade(Long orgao, Integer ano) {
			
		  String q = " SELECT 'CONCLUIDAS NO PRAZO' as 'descricao', " +  
				  " (SELECT COUNT(DISTINCT(A.INATENDIMENTOID)) " +
				  " FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) " +
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE " +
				  " A.INCODIGOORGAO = ? " + 
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO  " +
				  " AND A.SMSTATUS = 1 " +
				  " AND SMSTATUSATENDIMENTO = 1 " +  
				  " AND (DATEDIFF(day, DACONCLUSAO, DADATA_PRAZO_ATUAL) >= 0) " +  
				  " AND YEAR(A.DAENTRADA)  = ? ) as 'total', " + 
				  " (SELECT COUNT(DISTINCT(A.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE A.INCODIGOORGAO = ? " +
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO  " +
				  " AND A.SMSTATUS = 1 AND A.SMSTATUSATENDIMENTO = 1 " +  
				  " AND (DATEDIFF(day, DACONCLUSAO, DADATA_PRAZO_ATUAL) >= 0) " +  
				  " AND YEAR(A.DAENTRADA) = ? " +
				  " ) /   NULLIF(CAST(COUNT(DISTINCT(A.INATENDIMENTOID)) AS  DECIMAL(12,2)),0) * 100 as 'percentual', 1 " +
				  " FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) INNER JOIN GLOB_ORGAO O ON O.CODIGO_ORGAO = A.INCODIGOORGAO " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE(A.INCODIGOORGAO = ?) " + 
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO  " +
				  " AND YEAR(A.DAENTRADA) = ? AND A.SMSTATUS <> 2 " +
				  " UNION " +
				  " SELECT 'CONCLUIDAS FORA PRAZO' as 'descricao2', " +  
				  " (SELECT COUNT(DISTINCT(A.INATENDIMENTOID)) " +
				  " FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) " +
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE " +
				  " A.INCODIGOORGAO = ? " + 
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				  " AND A.SMSTATUS = 1 " +
				  " AND SMSTATUSATENDIMENTO = 1 " +  
				  " AND (DATEDIFF(day, DACONCLUSAO, DADATA_PRAZO_ATUAL) < 0) " +  
				  " AND YEAR(A.DAENTRADA) = ? ) as 'total2', " + 
				  " (SELECT   COUNT(DISTINCT(A.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE A.INCODIGOORGAO = ? " + 
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				  " AND A.SMSTATUS = 1 AND A.SMSTATUSATENDIMENTO = 1 " +  
				  " AND (DATEDIFF(day, DACONCLUSAO, DADATA_PRAZO_ATUAL) < 0) " +  
				  " AND YEAR(A.DAENTRADA) = ? " +
				  " ) /   NULLIF(CAST(COUNT(DISTINCT(A.INATENDIMENTOID)) AS  DECIMAL(12,2)),0) * 100 as 'percentual2', 2" +
				  " FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) INNER JOIN GLOB_ORGAO O ON O.CODIGO_ORGAO = A.INCODIGOORGAO " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE(A.INCODIGOORGAO = ?) " +
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				  " AND YEAR(A.DAENTRADA) = ? AND A.SMSTATUS <> 2 " +  
				  " UNION " +
				  " SELECT 'EM TRAMITAÇÃO NO PRAZO' as 'descricao3', " +  
				  " (SELECT COUNT(DISTINCT(AAA.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO AAA (NOLOCK) " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = AAA.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE AAA.INCODIGOORGAO = ? " +
				  //" AND ASS.INCODIGOORGAO = AAA.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = AAA.INCODIGOORGAO  " +
//				  " AND (SMSTATUSATENDIMENTO = 0 OR  SMSTATUSATENDIMENTO = 1) " + 
				  " AND AAA.SMSTATUS = 0 AND (DATEDIFF(day, GETDATE(), DADATA_PRAZO_ATUAL) >= 0) " +  
				  " AND YEAR(AAA.DAENTRADA) = ?  ) as 'total3', " +
				  " (SELECT COUNT(DISTINCT(AA.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO AA (NOLOCK) " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = AA.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE AA.INCODIGOORGAO = ? " +
				  //" AND ASS.INCODIGOORGAO = AA.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = AA.INCODIGOORGAO " +
//				  " AND (SMSTATUSATENDIMENTO = 0 OR  SMSTATUSATENDIMENTO = 1) " + 
				  " AND AA.SMSTATUS = 0 AND (DATEDIFF(day, GETDATE(), DADATA_PRAZO_ATUAL) >= 0) " +  
				  " AND YEAR(AA.DAENTRADA) = ? " +
				  " ) /   NULLIF(CAST(COUNT(DISTINCT(A.INATENDIMENTOID)) AS  DECIMAL(12,2)),0) * 100 as 'percentual3', 3 " +  
				  " FROM  OUVIDORIA_ATENDIMENTO A (NOLOCK) INNER JOIN " +
				  " GLOB_ORGAO O ON O.CODIGO_ORGAO = A.INCODIGOORGAO " +
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE (A.INCODIGOORGAO = ?) " +
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				  " AND YEAR(A.DAENTRADA) = ? " + 
				  " AND A.SMSTATUS <> 2 " +
				  " UNION " +
				  " SELECT 'EM TRAMITAÇÃO FORA DO PRAZO' as 'descricao4',  " +
				  " (SELECT COUNT(DISTINCT(AAA.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO AAA (NOLOCK) " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = AAA.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE AAA.INCODIGOORGAO = ? " +
				  //" AND ASS.INCODIGOORGAO = AAA.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = AAA.INCODIGOORGAO  " +
//				  " AND (SMSTATUSATENDIMENTO = 0 OR  SMSTATUSATENDIMENTO = 1) " + 
				  " AND AAA.SMSTATUS = 0 AND (DATEDIFF(day, GETDATE(), DADATA_PRAZO_ATUAL) < 0) " +  
				  " AND YEAR(AAA.DAENTRADA) = ?  ) as 'total4', " + 
				  " (SELECT COUNT(DISTINCT(AA.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO AA (NOLOCK)  " +
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = AA.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE AA.INCODIGOORGAO = ? " + 
				  //" AND ASS.INCODIGOORGAO = AA.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = AA.INCODIGOORGAO " +
//				  " AND (SMSTATUSATENDIMENTO = 0 OR  SMSTATUSATENDIMENTO = 1) " + 
				  " AND AA.SMSTATUS = 0 AND (DATEDIFF(day, GETDATE(), DADATA_PRAZO_ATUAL) < 0) " +  
				  " AND YEAR(AA.DAENTRADA) = ? " +
				  " ) /   NULLIF(CAST(COUNT(DISTINCT(A.INATENDIMENTOID)) AS  DECIMAL(12,2)),0) * 100 as 'percentual4', 4" +
				  " FROM  OUVIDORIA_ATENDIMENTO A (NOLOCK) INNER JOIN " +
				  " GLOB_ORGAO O ON O.CODIGO_ORGAO = A.INCODIGOORGAO " +
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE (A.INCODIGOORGAO = ?) " +
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				  " AND YEAR(A.DAENTRADA) = ? " + 
				  " AND A.SMSTATUS <> 2 " +
		          " AND A.INCODIGOAREA > 0 ";

		     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
						Query query =  manager.createNativeQuery(q);
						query.setParameter(1, orgao);
						query.setParameter(2, ano);
						query.setParameter(3, orgao);
						query.setParameter(4, ano);
						query.setParameter(5, orgao);
						query.setParameter(6, ano);
						query.setParameter(7, orgao);
						query.setParameter(8, ano);
						query.setParameter(9, orgao);
						query.setParameter(10, ano);
						query.setParameter(11, orgao);
						query.setParameter(12, ano);
						query.setParameter(13, orgao);
						query.setParameter(14, ano);
						query.setParameter(15, orgao);
						query.setParameter(16, ano);
						query.setParameter(17, orgao);
						query.setParameter(18, ano);
						query.setParameter(19, orgao);
						query.setParameter(20, ano);
						query.setParameter(21, orgao);
						query.setParameter(22, ano);
						query.setParameter(23, orgao);
						query.setParameter(24, ano);

						@SuppressWarnings("unchecked")
						List<Object> lista = query.getResultList();
						List<DadosGrafico> dados = new ArrayList<>(lista.size());
						DecimalFormat frmt = new DecimalFormat();
						frmt.setMaximumFractionDigits(2);
						for (int i = 0; i < lista.size(); i++) {
				    		Object obj = lista.get(i);
							Object[] itensObj = (Object[])obj;
							dados.add(new DadosGrafico(itensObj[0].toString(),  itensObj[2] == null ? 0d : Double.parseDouble(itensObj[2].toString()), ""));
						}
						

		return dados;
	}

	@Override
	public DadosRelatorio getRelatorioSecretaria(Long orgao, Date dataInicial, Date dataFinal, Long area) {
		
		  DadosRelatorio dadosRel = new DadosRelatorio();
		
		
		
		  String q = " SELECT 'CONCLUIDAS NO PRAZO' as 'descricao', " +  
				  " (SELECT COUNT(DISTINCT(A.INATENDIMENTOID)) " +
				  " FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) " +
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE " +
				  " A.INCODIGOORGAO = ? " + 
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO  " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND A.SMSTATUS = 1 " +
				  " AND SMSTATUSATENDIMENTO = 1 " +  
				  " AND (DATEDIFF(day, DACONCLUSAO, DADATA_PRAZO_ATUAL) >= 0) " +  
				  " AND A.DAENTRADA  >= ? AND A.DAENTRADA <= ? ) as 'total', " + 
				  " (SELECT COUNT(DISTINCT(A.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE A.INCODIGOORGAO = ? " +
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO  " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND A.SMSTATUS = 1 AND A.SMSTATUSATENDIMENTO = 1 " +  
				  " AND (DATEDIFF(day, DACONCLUSAO, DADATA_PRAZO_ATUAL) >= 0) " +  
				  " AND A.DAENTRADA >= ? AND A.DAENTRADA <= ? " +
				  " ) /   NULLIF(CAST(COUNT(DISTINCT(A.INATENDIMENTOID)) AS  DECIMAL(12,2)),0) * 100 as 'percentual', 1 " +
				  " FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) INNER JOIN GLOB_ORGAO O ON O.CODIGO_ORGAO = A.INCODIGOORGAO " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE(A.INCODIGOORGAO = ?) " + 
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO  " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND A.DAENTRADA  >= ? AND A.DAENTRADA <= ? AND A.SMSTATUS <> 2 " +
				  " UNION " +
				  " SELECT 'CONCLUIDAS FORA PRAZO' as 'descricao2', " +  
				  " (SELECT COUNT(DISTINCT(A.INATENDIMENTOID)) " +
				  " FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) " +
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE " +
				  " A.INCODIGOORGAO = ? " + 
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND A.SMSTATUS = 1 " +
				  " AND SMSTATUSATENDIMENTO = 1 " +  
				  " AND (DATEDIFF(day, DACONCLUSAO, DADATA_PRAZO_ATUAL) < 0) " +  
				  " AND A.DAENTRADA >= ? AND A.DAENTRADA     <= ? ) as 'total2', " + 
				  " (SELECT   COUNT(DISTINCT(A.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE A.INCODIGOORGAO = ? " + 
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND A.SMSTATUS = 1 AND A.SMSTATUSATENDIMENTO = 1 " +  
				  " AND (DATEDIFF(day, DACONCLUSAO, DADATA_PRAZO_ATUAL) < 0) " +  
				  " AND A.DAENTRADA >= ? AND A.DAENTRADA  <= ? " +
				  " ) /   NULLIF(CAST(COUNT(DISTINCT(A.INATENDIMENTOID)) AS  DECIMAL(12,2)),0) * 100 as 'percentual2', 2" +
				  " FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) INNER JOIN GLOB_ORGAO O ON O.CODIGO_ORGAO = A.INCODIGOORGAO " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE(A.INCODIGOORGAO = ?) " +
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND A.DAENTRADA  >= ? AND A.DAENTRADA  <= ? AND A.SMSTATUS <> 2 " +  
				  " UNION " +
				  " SELECT 'ABERTAS NO PRAZO' as 'descricao3', " +  
				  " (SELECT COUNT(DISTINCT(AAA.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO AAA (NOLOCK) " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = AAA.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE AAA.INCODIGOORGAO = ? " +
				  //" AND ASS.INCODIGOORGAO = AAA.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = AAA.INCODIGOORGAO  " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND (SMSTATUSATENDIMENTO = 0 OR  SMSTATUSATENDIMENTO = 1) " + 
				  " AND AAA.SMSTATUS = 0 AND (DATEDIFF(day, GETDATE(), DADATA_PRAZO_ATUAL) >= 0) " +  
				  " AND AAA.DAENTRADA >= ? AND AAA.DAENTRADA  <= ?  ) as 'total3', " +
				  " (SELECT COUNT(DISTINCT(AA.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO AA (NOLOCK) " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = AA.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE AA.INCODIGOORGAO = ? " +
				  //" AND ASS.INCODIGOORGAO = AA.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = AA.INCODIGOORGAO " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND (SMSTATUSATENDIMENTO = 0 OR  SMSTATUSATENDIMENTO = 1) " + 
				  " AND AA.SMSTATUS = 0 AND (DATEDIFF(day, GETDATE(), DADATA_PRAZO_ATUAL) >= 0) " +  
				  " AND AA.DAENTRADA >= ? AND AA.DAENTRADA  <= ? " +
				  " ) /   NULLIF(CAST(COUNT(DISTINCT(A.INATENDIMENTOID)) AS  DECIMAL(12,2)),0) * 100 as 'percentual3', 3 " +  
				  " FROM  OUVIDORIA_ATENDIMENTO A (NOLOCK) INNER JOIN " +
				  " GLOB_ORGAO O ON O.CODIGO_ORGAO = A.INCODIGOORGAO " +
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE (A.INCODIGOORGAO = ?) " +
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND A.DAENTRADA >= ? AND A.DAENTRADA  <= ? " + 
				  " AND A.SMSTATUS <> 2 " +
				  " UNION " +
				  " SELECT 'ABERTAS FORA DO PRAZO' as 'descricao4',  " +
				  " (SELECT COUNT(DISTINCT(AAA.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO AAA (NOLOCK) " + 
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = AAA.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE AAA.INCODIGOORGAO = ? " +
				  //" AND ASS.INCODIGOORGAO = AAA.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = AAA.INCODIGOORGAO  " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND (SMSTATUSATENDIMENTO = 0 OR  SMSTATUSATENDIMENTO = 1) " + 
				  " AND AAA.SMSTATUS = 0 AND (DATEDIFF(day, GETDATE(), DADATA_PRAZO_ATUAL) < 0) " +  
				  " AND AAA.DAENTRADA >= ? AND AAA.DAENTRADA <= ?  ) as 'total4', " + 
				  " (SELECT COUNT(DISTINCT(AA.INATENDIMENTOID)) FROM OUVIDORIA_ATENDIMENTO AA (NOLOCK)  " +
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = AA.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE AA.INCODIGOORGAO = ? " + 
				  //" AND ASS.INCODIGOORGAO = AA.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = AA.INCODIGOORGAO " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND (SMSTATUSATENDIMENTO = 0 OR  SMSTATUSATENDIMENTO = 1) " + 
				  " AND AA.SMSTATUS = 0 AND (DATEDIFF(day, GETDATE(), DADATA_PRAZO_ATUAL) < 0) " +  
				  " AND AA.DAENTRADA  >= ? AND AA.DAENTRADA <= ? " +
				  " ) /   NULLIF(CAST(COUNT(DISTINCT(A.INATENDIMENTOID)) AS  DECIMAL(12,2)),0) * 100 as 'percentual4', 4" +
				  " FROM  OUVIDORIA_ATENDIMENTO A (NOLOCK) INNER JOIN " +
				  " GLOB_ORGAO O ON O.CODIGO_ORGAO = A.INCODIGOORGAO " +
				  //" INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  //" INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE (A.INCODIGOORGAO = ?) " +
				  //" AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " +
				  //" AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				  " AND A.INCODIGOAREA = ? " +
				  " AND A.DAENTRADA >= ? AND A.DAENTRADA  <= ? " + 
				  " AND A.SMSTATUS <> 2 " +
		  		  " AND A.INCODIGOAREA > 0 ";


		     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
						Query query =  manager.createNativeQuery(q);
						query.setParameter(1, orgao);
						query.setParameter(2, area);
						query.setParameter(3, dateFormat.format(dataInicial));
						query.setParameter(4, dateFormat.format(dataFinal));
						query.setParameter(5, orgao);
						query.setParameter(6, area);
						query.setParameter(7, dateFormat.format(dataInicial));
						query.setParameter(8, dateFormat.format(dataFinal));
						query.setParameter(9, orgao);
						query.setParameter(10, area);
						query.setParameter(11, dateFormat.format(dataInicial));
						query.setParameter(12, dateFormat.format(dataFinal));
						query.setParameter(13, orgao);
						query.setParameter(14, area);
						query.setParameter(15, dateFormat.format(dataInicial));
						query.setParameter(16, dateFormat.format(dataFinal));
						query.setParameter(17, orgao);
						query.setParameter(18, area);
						query.setParameter(19, dateFormat.format(dataInicial));
						query.setParameter(20, dateFormat.format(dataFinal));
						query.setParameter(21, orgao);
						query.setParameter(22, area);
						query.setParameter(23, dateFormat.format(dataInicial));
						query.setParameter(24, dateFormat.format(dataFinal));
						query.setParameter(25, orgao);
						query.setParameter(26, area);
						query.setParameter(27, dateFormat.format(dataInicial));
						query.setParameter(28, dateFormat.format(dataFinal));
						query.setParameter(29, orgao);
						query.setParameter(30, area);
						query.setParameter(31, dateFormat.format(dataInicial));
						query.setParameter(32, dateFormat.format(dataFinal));
						query.setParameter(33, orgao);
						query.setParameter(34, area);
						query.setParameter(35, dateFormat.format(dataInicial));
						query.setParameter(36, dateFormat.format(dataFinal));
						query.setParameter(37, orgao);
						query.setParameter(38, area);
						query.setParameter(39, dateFormat.format(dataInicial));
						query.setParameter(40, dateFormat.format(dataFinal));
						query.setParameter(41, orgao);
						query.setParameter(42, area);
						query.setParameter(43, dateFormat.format(dataInicial));
						query.setParameter(44, dateFormat.format(dataFinal));
						query.setParameter(45, orgao);
						query.setParameter(46, area);
						query.setParameter(47, dateFormat.format(dataInicial));
						query.setParameter(48, dateFormat.format(dataFinal));
						
						

						@SuppressWarnings("unchecked")
						List<Object> lista = query.getResultList();
						List<DadosGrafico> dados = new ArrayList<>(lista.size());
						DecimalFormat frmt = new DecimalFormat();
						frmt.setMaximumFractionDigits(2);
						for (int i = 0; i < lista.size(); i++) {
				    		Object obj = lista.get(i);
							Object[] itensObj = (Object[])obj;
							dados.add(new DadosGrafico(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), itensObj[2] == null ? "0.00" : NumberTools.format(Double.parseDouble(itensObj[2].toString()))));
						}
						
						dadosRel.setArea(areaRepository.findById(area).get().getDescricao());
						dadosRel.setListaResolutividade(dados);
						dadosRel.setListaNatureza(this.getRelatorioNaturezaSecretaria(orgao, dataInicial, dataFinal, area));
						dadosRel.setListaAssunto(this.getRelatorioAssuntoSecretaria(orgao, dataInicial, dataFinal, area));
					return dadosRel;
	}

	private List<DadosGrafico> getRelatorioNaturezaSecretaria(Long orgao, Date dataInicial, Date dataFinal, Long area) {
		
		  String q = (" SELECT N.VADESCRICAO as 'descricao', " +  
				  " COUNT(DISTINCT(A.INATENDIMENTOID)) as CONT, " + 
				  " CAST((CAST(Count(A.INATENDIMENTOID) AS decimal(10,2))/CAST(TOTAL.todos AS decimal(10,2)) * 100) AS decimal(10,2)) as 'percentual', N.INCODIGONATUREZA " +
				  " FROM OUVIDORIA_NATUREZA N (NOLOCK) " +
				  " INNER JOIN OUVIDORIA_ATENDIMENTO A (NOLOCK) ON (A.INCODIGONATUREZA = N.INCODIGONATUREZA) " + 
				  " INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = A.INCODIGOASSUNTONOVO " +
				  " INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA, " +
				  " (SELECT Count(OA.INCODIGONATUREZA) as todos " +
				  " FROM OUVIDORIA_ATENDIMENTO OA(NOLOCK) " +
				  " INNER JOIN OUVIDORIA_ASSUNTO ASS ON ASS.INCODIGOASSUNTONOVO = OA.INCODIGOASSUNTONOVO " +
				  " INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				  " WHERE (OA.INCODIGOORGAO = ?) " +
				  " AND ASS.INCODIGOORGAO = OA.INCODIGOORGAO " + 
				  " AND AR.INCODIGOORGAO = OA.INCODIGOORGAO " +
				  " AND AR.INCODIGOAREA = ? " +
				  " AND (OA.DAENTRADA >= ?)   AND (OA.DAENTRADA <= ?) " +  
				  " AND (OA.SMSTATUS <> 2)) TOTAL "  +
				  " WHERE (N.INCODIGONATUREZA = A.INCODIGONATUREZA) " +  
				  " AND (A.INCODIGOORGAO = ?)   " +
				  " AND ASS.INCODIGOORGAO = A.INCODIGOORGAO " + 
				  " AND AR.INCODIGOORGAO = A.INCODIGOORGAO " +
				  " AND AR.INCODIGOAREA = ? " +
				  " AND (A.DAENTRADA >= ?) AND (A.DAENTRADA <= ?)  AND (A.SMSTATUS <> 2) " + 
				  " GROUP BY N.VADESCRICAO, TOTAL.todos, N.INCODIGONATUREZA " +
				  " ORDER BY N.VADESCRICAO, TOTAL.todos ");
		
	       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   
					Query query =  manager.createNativeQuery(q);
					query.setParameter(1, orgao);
					query.setParameter(2, area);
					query.setParameter(3, dateFormat.format(dataInicial));
					query.setParameter(4, dateFormat.format(dataFinal));
					query.setParameter(5, orgao);
					query.setParameter(6, area);
					query.setParameter(7, dateFormat.format(dataInicial));
					query.setParameter(8, dateFormat.format(dataFinal));

					@SuppressWarnings("unchecked")
					List<Object> lista = query.getResultList();
					List<DadosGrafico> dados = new ArrayList<>(lista.size());
					DecimalFormat frmt = new DecimalFormat();
					frmt.setMaximumFractionDigits(2);
					for (int i = 0; i < lista.size(); i++) {
			    		Object obj = lista.get(i);
						Object[] itensObj = (Object[])obj;
						dados.add(new DadosGrafico(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), NumberTools.format(Double.parseDouble(itensObj[2].toString()))));
					}
				return dados;
	}
	

	private List<DadosGrafico> getRelatorioAssuntoSecretaria(Long orgao, Date dataInicial, Date dataFinal, Long area) {
		
		  String q = (" SELECT OASS.VADESCRICAO, " + 
		    		 " COUNT(DISTINCT(OATT.INATENDIMENTOID)) as CONT,  " +
				  	 " CAST(COUNT(DISTINCT(OATT.INATENDIMENTOID)) AS  DECIMAL(12,2))*100 / " +
			  		 " (SELECT Count(DISTINCT(AT.INATENDIMENTOID)) " +
				     " FROM OUVIDORIA_ASSUNTO ASS (NOLOCK) " +
				     " INNER JOIN OUVIDORIA_ATENDIMENTO AT (NOLOCK) ON (ASS.INCODIGOASSUNTONOVO = AT.INCODIGOASSUNTONOVO AND ASS.INCODIGOORGAO = AT.INCODIGOORGAO) " + 
				     " INNER JOIN OUVIDORIA_AREAASSUNTO AR ON ASS.INCODIGOAREANOVO = AR.INCODIGOAREA " +
				     " WHERE (AT.INCODIGOORGAO =  ?) " +
				     " AND ASS.INCODIGOORGAO = AT.INCODIGOORGAO " +
				     " AND AR.INCODIGOORGAO = AT.INCODIGOORGAO " +
				     " AND AR.INCODIGOAREA = ? " +
				     "  AND (AT.DAENTRADA >= ?)  AND (AT.DAENTRADA <= ?) ) AS PERCENTUAL, OASS.INCODIGOASSUNTONOVO " + 
				     "  FROM OUVIDORIA_ASSUNTO OASS(NOLOCK) " +
				     "  INNER JOIN OUVIDORIA_ATENDIMENTO OATT (NOLOCK) ON (OASS.INCODIGOASSUNTONOVO = OATT.INCODIGOASSUNTONOVO AND OASS.INCODIGOORGAO = OATT.INCODIGOORGAO) " + 
				     " INNER JOIN OUVIDORIA_AREAASSUNTO ARR ON OASS.INCODIGOAREANOVO = ARR.INCODIGOAREA " +
				     "  WHERE (OATT.INCODIGOORGAO = ?)   " +
				     " AND OASS.INCODIGOORGAO = OATT.INCODIGOORGAO " +
				     " AND ARR.INCODIGOORGAO = OATT.INCODIGOORGAO " +
				     " AND ARR.INCODIGOAREA = ? " +
				     "  AND (OATT.DAENTRADA >= ?)   AND (OATT.DAENTRADA <= ?) " + 
				     "  AND (OATT.SMSTATUS <> 2) " +
				     "  GROUP BY OASS.VADESCRICAO, OASS.INCODIGOASSUNTONOVO " + 
				     "  ORDER BY CONT DESC ");
		
	       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   
					Query query =  manager.createNativeQuery(q);
					query.setParameter(1, orgao);
					query.setParameter(2, area);
					query.setParameter(3, dateFormat.format(dataInicial));
					query.setParameter(4, dateFormat.format(dataFinal));
					query.setParameter(5, orgao);
					query.setParameter(6, area);
					query.setParameter(7, dateFormat.format(dataInicial));
					query.setParameter(8, dateFormat.format(dataFinal));

					@SuppressWarnings("unchecked")
					List<Object> lista = query.getResultList();
					List<DadosGrafico> dados = new ArrayList<>(lista.size());
					DecimalFormat frmt = new DecimalFormat();
					frmt.setMaximumFractionDigits(2);
					for (int i = 0; i < lista.size(); i++) {
			    		Object obj = lista.get(i);
						Object[] itensObj = (Object[])obj;
						dados.add(new DadosGrafico(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), NumberTools.format(Double.parseDouble(itensObj[2].toString()))));
					}
				return dados;
	}

	@Override
	public DadosRelatorioUsuario getRelatorioUsuario(Long orgao, Date dataInicial, Date dataFinal, Long usuario) {
		
	    String q = ("SELECT A.INATENDIMENTOID, A.SMANOATENDIMENTO, A.INNUMEROATENDIMENTO, A.VANUMPROTOCOLO, CONVERT(varchar, A.DACRIACAO, 103) as 'DATACRIACAO', U.NOME, " + 
	    		" CONVERT(varchar, A.DAALTERACAO, 103) as 'DATAALTERACAO', CASE WHEN A.SMSTATUSATENDIMENTO = 0 THEN 'Aberto' WHEN A.SMSTATUSATENDIMENTO = 1 THEN 'Concluido' END  " +
	    		" FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) , GLOB_USUARIO U " +
	    	    " WHERE (A.INCODIGOORGAO = ?) " +
	    		" AND A.INCODIGOUSUARIO = U.CODIGO_USUARIO " +
	    	    " AND (A.DAENTRADA >= ?) " +
	    	    " AND (A.DAENTRADA <= ?) " +
	    	    " AND (A.SMSTATUS <> 2) " +
	    	    " AND (A.INCODIGOUSUARIO = ? OR A.INCODIGOUSUARIOALTERACAO = ?) " + 
	    	    " ORDER BY A.SMANOATENDIMENTO, A.INNUMEROATENDIMENTO ASC ");

	      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	      
	      DadosRelatorioUsuario dadosUsuario = new DadosRelatorioUsuario();
		   
						Query query =  manager.createNativeQuery(q);
						query.setParameter(1, orgao);
						query.setParameter(2, dateFormat.format(dataInicial));
						query.setParameter(3, dateFormat.format(dataFinal));
						query.setParameter(4, usuario);
						query.setParameter(5, usuario);

						@SuppressWarnings("unchecked")
						List<Object> lista = query.getResultList();
						List<RelatorioUsuario> dados = new ArrayList<>(lista.size());
						for (int i = 0; i < lista.size(); i++) {
				    		Object obj = lista.get(i);
							Object[] itensObj = (Object[])obj;
							String prot = itensObj[1].toString() + itensObj[2].toString();
							dados.add(new RelatorioUsuario(itensObj[0].toString(), prot, itensObj[4].toString(), itensObj[5].toString(), itensObj[6].toString(), itensObj[7].toString()));
						}
					
						dadosUsuario.setNome(usuarioRepository.findById(usuario).get().getNome());
						dadosUsuario.setLista(dados);
		
		return dadosUsuario;
	}

	@Override
	public List<DadosGrafico> getRelatorioMediaResposta(Long orgao, Date dataInicial, Date dataFinal) {
		
		String q = " SELECT S.DESCRICAO as 'descricao', E.INCODIGOSETORDESTINO as 'codigo', SUM(DATEDIFF(DAY,E.DAENCAMINHAMENTO,R.DARESPOSTA))/CAST(COUNT(E.INCODIGOENCAMINHAMENTOENVIO) AS DECIMAL(12,2)) "
		    	   +" FROM OUVIDORIA_ENCAMINHAMENTO_ENVIO E (NOLOCK) "
		    	   +" INNER JOIN OUVIDORIA_ENCAMINHAMENTO_RESPOSTA R (NOLOCK) ON (E.INCODIGOENCAMINHAMENTOENVIO = R.INCODIGOENCAMINHAMENTO) "
		    	   +" INNER JOIN GLOB_SETOR S (NOLOCK) ON E.INCODIGOSETORDESTINO = S.CODIGO_SETOR "
		    	   +" INNER JOIN OUVIDORIA_ATENDIMENTO A (NOLOCK) ON (A.INATENDIMENTOID = E.INATENDIMENTOID) "
		    	   +" WHERE A.INCODIGOORGAO = ? AND A.DAENTRADA >= ? AND A.DAENTRADA <= ? AND A.SMSTATUS <> 2 "
		    	   +" GROUP BY S.DESCRICAO, E.INCODIGOSETORDESTINO "
		    	   +" ORDER BY 2 DESC";
		
		       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
				Query query =  manager.createNativeQuery(q);
				query.setParameter(1, orgao);
				query.setParameter(2, dateFormat.format(dataInicial));
				query.setParameter(3, dateFormat.format(dataFinal));

				@SuppressWarnings("unchecked")
				List<Object> lista = query.getResultList();
				List<DadosGrafico> dados = new ArrayList<>(lista.size());
				DecimalFormat frmt = new DecimalFormat();
				frmt.setMaximumFractionDigits(2);
				BigDecimal percentual = new BigDecimal(0);
				DecimalFormat df = new DecimalFormat("#,###.00");
				for (int i = 0; i < lista.size(); i++) {
		    		Object obj = lista.get(i);
					Object[] itensObj = (Object[])obj;
					percentual = new BigDecimal(itensObj[2].toString()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
					dados.add(new DadosGrafico(itensObj[0].toString(), percentual.doubleValue(), NumberTools.format(percentual.doubleValue())));
				}
			return dados;
	}

	@Override
	public List<DadosGrafico> getRelatorioEficienciaOuvidoria(Long orgao, Date dataInicial, Date dataFinal) {
	
		
		String q = " SELECT COUNT(*) AS 'TOTAL MANIFESTAÇÕES', " + 
		   " (SELECT COUNT(*) FROM OUVIDORIA_ATENDIMENTO A WHERE A.DACRIACAO BETWEEN ? AND ? " +
		   " AND A.SMSTATUS <> 2 AND SMSTATUS = 1 AND SMSTATUSATENDIMENTO = 1 AND A.INCODIGOORGAO = ? " +
		   " ) AS 'MANIFESTCOES CONCLUIDAS', " +
		   " (SELECT COUNT(*)  FROM OUVIDORIA_ATENDIMENTO A WHERE A.DACRIACAO BETWEEN ? AND ? " +
		   " AND A.SMSTATUS <> 2 AND SMSTATUS = 0 AND A.INCODIGOORGAO = ?) AS 'MANIFESTCOES EM ABERTO', " +
		   " (SELECT COUNT(*) FROM OUVIDORIA_ATENDIMENTO A WHERE A.DACRIACAO BETWEEN ? AND ? " +
		   " AND A.SMSTATUS <> 2 AND SMSTATUS = 1 AND SMSTATUSATENDIMENTO = 1 AND A.DACONCLUSAO <= A.DADATA_PRAZO AND A.INCODIGOORGAO = ?) AS 'CONCLUIDAS NO PRAZO', " +
		   " (SELECT COUNT(*) FROM OUVIDORIA_ATENDIMENTO A WHERE A.DACRIACAO BETWEEN ? AND ? " +
		   " AND A.SMSTATUS <> 2 AND SMSTATUS = 1 AND SMSTATUSATENDIMENTO = 1 AND A.DACONCLUSAO > A.DADATA_PRAZO AND A.INCODIGOORGAO = ?) AS 'CONCLUIDAS FORA PRAZO' " +
		   " FROM OUVIDORIA_ATENDIMENTO A WHERE A.DACRIACAO BETWEEN ? AND ? " +
		   " AND A.SMSTATUS <> 2 " + 
		   " AND A.INCODIGOORGAO = ? ";

	       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   
			Query query =  manager.createNativeQuery(q);
			query.setParameter(1, dateFormat.format(dataInicial));
			query.setParameter(2, dateFormat.format(dataFinal));
			query.setParameter(3, orgao);
			query.setParameter(4, dateFormat.format(dataInicial));
			query.setParameter(5, dateFormat.format(dataFinal));
			query.setParameter(6, orgao);
			query.setParameter(7, dateFormat.format(dataInicial));
			query.setParameter(8, dateFormat.format(dataFinal));
			query.setParameter(9, orgao);
			query.setParameter(10, dateFormat.format(dataInicial));
			query.setParameter(11, dateFormat.format(dataFinal));
			query.setParameter(12, orgao);
			query.setParameter(13, dateFormat.format(dataInicial));
			query.setParameter(14, dateFormat.format(dataFinal));
			query.setParameter(15, orgao);

			@SuppressWarnings("unchecked")
			List<Object> lista = query.getResultList();
			List<DadosGrafico> dados = new ArrayList<>(lista.size());
			DecimalFormat frmt = new DecimalFormat();
			frmt.setMaximumFractionDigits(2);
			double percentualConcluido = 0d;
			double percentualConcluidoAberto = 0d;
			double percentualConcluidoPrazo = 0d;
			double percentualConcluidoForaPrazo = 0d;
			
			for (int i = 0; i < lista.size(); i++) {
	    		Object obj = lista.get(i);
				Object[] itensObj = (Object[])obj;
				
				if (!itensObj[1].toString().equals("0")) {
					percentualConcluido = Double.parseDouble(itensObj[1].toString())*100/Double.parseDouble(itensObj[0].toString());
				}
				
				if (!itensObj[2].toString().equals("0")) {
					percentualConcluidoAberto = Double.parseDouble(itensObj[2].toString())*100/Double.parseDouble(itensObj[0].toString());
				}

				if (!itensObj[3].toString().equals("0")) {
					percentualConcluidoPrazo = Double.parseDouble(itensObj[3].toString())*100/Double.parseDouble(itensObj[0].toString());
				}

				
				if (!itensObj[4].toString().equals("0")) {
					percentualConcluidoForaPrazo = Double.parseDouble(itensObj[4].toString())*100/Double.parseDouble(itensObj[0].toString());
				}
				
				dados.add(new DadosGrafico("Total de Manifestações", Double.parseDouble(itensObj[0].toString()), "100%"));
				dados.add(new DadosGrafico("Total de Manifestações Concluídas", Double.parseDouble(itensObj[1].toString()), NumberTools.format(new BigDecimal(percentualConcluido).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue())));
				dados.add(new DadosGrafico("Total de Manifestações em Aberto", Double.parseDouble(itensObj[2].toString()), NumberTools.format(new BigDecimal(percentualConcluidoAberto).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue())));
				dados.add(new DadosGrafico("Total de Manifestações Concluídas no Prazo", Double.parseDouble(itensObj[3].toString()), NumberTools.format(new BigDecimal(percentualConcluidoPrazo).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue())));
				dados.add(new DadosGrafico("Total de Manifestações Concluídas fora do Prazo", Double.parseDouble(itensObj[4].toString()), NumberTools.format(new BigDecimal(percentualConcluidoForaPrazo).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue())));
			}
		return dados;
	}

	@Override
	public DadosComparativoPeriodo getRelatorioComparativo(Long orgao, Date dataInicial, Date dataFinal, Date dataInicialAnterior, Date dataFinalAnterior) {
		String q = " SELECT 'Período 1' as Perido, COUNT(A.INATENDIMENTOID) AS QTD FROM OUVIDORIA_ATENDIMENTO A (NOLOCK) " + 
				   " WHERE A.DAENTRADA >= ? AND A.DAENTRADA <= ? AND A.INCODIGOORGAO = ? " + 
			 	   " AND A.SMSTATUS <> 2 " + 
				   " UNION " + 
				   " SELECT 'Período 2' as periodo, COUNT(AA.INATENDIMENTOID) as QTD FROM OUVIDORIA_ATENDIMENTO AA (NOLOCK) " + 
				   " WHERE AA.DAENTRADA >= ? AND AA.DAENTRADA <= ? AND AA.INCODIGOORGAO = ? " + 
				   " AND AA.SMSTATUS <> 2 ";
		
		       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
				Query query =  manager.createNativeQuery(q);
				query.setParameter(1, dateFormat.format(dataInicialAnterior));
				query.setParameter(2, dateFormat.format(dataFinalAnterior));
				query.setParameter(3, orgao);
				query.setParameter(4, dateFormat.format(dataInicial));
				query.setParameter(5, dateFormat.format(dataFinal));
				query.setParameter(6, orgao);

				@SuppressWarnings("unchecked")
				List<Object> lista = query.getResultList();
				List<DadosGrafico> dados = new ArrayList<>(lista.size());
				DecimalFormat frmt = new DecimalFormat();
				DadosComparativoPeriodo dadosComparativo = new DadosComparativoPeriodo();
				frmt.setMaximumFractionDigits(2);
				for (int i = 0; i < lista.size(); i++) {
		    		Object obj = lista.get(i);
					Object[] itensObj = (Object[])obj;
					dados.add(new DadosGrafico(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), null));
				}
				
				dadosComparativo.setListaManifestacoes(dados);
			    dadosComparativo.setListaNaturezaPeriodo1(getRelatorioNatureza(orgao, dataInicialAnterior, dataFinalAnterior));
			    dadosComparativo.setListaNaturezaPeriodo2(getRelatorioNatureza(orgao, dataInicial, dataFinal));	
			    
			return dadosComparativo;
	}

	@Override
	public DadosRelatorio getRelatorioProdutividadeCallCenter(Long orgao, Date dataInicial, Date dataFinal, String area) {
		
		
		   DadosRelatorio dadosRel = new DadosRelatorio();
		  
		  
		   String q = " SELECT u.nome as ATENDENTE,count(INATENDIMENTOID) as QTD_ATENDIMENTOS " + 
				   " FROM ouvidoria_atendimento oa(nolock) " +
				   " inner join OUVIDORIA_ASSUNTO assunto(nolock) " +
				   " on oa.INCODIGOASSUNTONOVO = assunto.INCODIGOASSUNTONOVO " +
				   " and oa.INCODIGOORGAO = assunto.INCODIGOORGAO " +
				   " inner join OUVIDORIA_AREAASSUNTO areaassunto(nolock) " +
				   " on assunto.INCODIGOAREANOVO = areaassunto.INCODIGOAREA " +
				   " and assunto.INCODIGOORGAO = areaassunto.INCODIGOORGAO " +
				   " inner join dbo.GLOB_USUARIO u(nolock) " +
				   " on oa.INCODIGOUSUARIO = u.CODIGO_USUARIO " +
				   " inner join GLOB_GRUPO_USUARIO gu(nolock) " +
				   " on u.CODIGO_GRUPO = gu.CODIGO_GRUPO " +
				   " inner join GLOB_PERFIL gp(nolock) on " +
				   " u.CODIGO_PERFIL = gp.CODIGO_PERFIL " +
				   " WHERE oa.INCODIGOORGAO = ? " +
				   " and gp.NOME = 'Atendente' " +
				   " and dacriacao >= ? " +
				   " and dacriacao <= ? ";
		   
				   if (!area.equals("")) {
					   q = q + " and areaassunto.INCODIGOAREA = ? ";
				   }
				   
				   q = q +
				   " GROUP BY u.nome " +
				   " ORDER BY count(INATENDIMENTOID) ";
			       
			       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				   
					Query query =  manager.createNativeQuery(q);
					query.setParameter(1, orgao);
					query.setParameter(2, dateFormat.format(dataInicial));
					query.setParameter(3, dateFormat.format(dataFinal));
					
					if (!area.equals("")) {
						query.setParameter(4, area);
						dadosRel.setArea(areaRepository.findById(Long.parseLong(area)).get().getDescricao());
				    }

					@SuppressWarnings("unchecked")
					List<Object> lista = query.getResultList();
					List<DadosGrafico> dados = new ArrayList<>(lista.size());
					DecimalFormat frmt = new DecimalFormat();
					frmt.setMaximumFractionDigits(2);
					for (int i = 0; i < lista.size(); i++) {
			    		Object obj = lista.get(i);
						Object[] itensObj = (Object[])obj;
						dados.add(new DadosGrafico(itensObj[0].toString(), Double.parseDouble(itensObj[1].toString()), null));
					}
					
                    dadosRel.setLista(dados);
					
				return dadosRel;
						
	}
	
	
	@Override
	public RetornoRelatorioGeral getListaGeral(RelatorioGeralRequest request) {
		String sql="";
        List<DadosRelatorioGeral> listaDados = new ArrayList<>();
        List<String> colunas = new ArrayList<>();
        colunas.add("Protocolo");
		int total = request.getCamposTipoExpressao().size();
		    for (int i=0; i<request.getCamposTipoExpressao().size(); i++) {
		    	
		    	colunas.add(request.getCamposTipoExpressao().get(i).getLabel());
		    	
		    	if (request.getCamposTipoExpressao().size() > 1) {
				      if (total >= i) {
				    	  sql +=", ";
				      }
		    	}else {
			    	  sql +=", ";
		    	}

		if(request.getCamposTipoExpressao().get(i).getValue().equals("DATARETORNO")){
		  sql += " CASE WHEN CONVERT(varchar(30),(SELECT     TOP 1 OUVIDORIA_ENCAMINHAMENTO.DAENCAMINHAMENTO "+
		                 " FROM OUVIDORIA_ENCAMINHAMENTO(NOLOCK) WHERE      (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND  "+
		                 " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND "+
		                 " OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM <> OUVIDORIA_ATENDIMENTO.incodigosetor AND OUVIDORIA_ENCAMINHAMENTO.SMCANCELADO=0 "+
		                 " ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC), 103) IS NULL  "+
		                 " THEN  '' ELSE CONVERT(varchar(30),(SELECT     TOP 1 OUVIDORIA_ENCAMINHAMENTO.DAENCAMINHAMENTO "+
		                 " FROM OUVIDORIA_ENCAMINHAMENTO(NOLOCK) WHERE (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND "+
		                 " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND "+
		                 " OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM <> OUVIDORIA_ATENDIMENTO.incodigosetor AND OUVIDORIA_ENCAMINHAMENTO.SMCANCELADO=0 "+
		                 " ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC), 103) END as 'Data_Retorno' ";
		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("DATAENCAMINHAMENTO")){

			sql +=" CASE WHEN CONVERT(varchar(30),(SELECT     TOP 1 OUVIDORIA_ENCAMINHAMENTO.DAENCAMINHAMENTO as 'Data_Encaminhamento' "+
		               " FROM OUVIDORIA_ENCAMINHAMENTO(NOLOCK)  WHERE      (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND  "+
		               " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND OUVIDORIA_ENCAMINHAMENTO.INSTATUS_ENVIO !=0 AND"+
		               " OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM = OUVIDORIA_ATENDIMENTO.incodigosetor AND OUVIDORIA_ENCAMINHAMENTO.SMCANCELADO=0 "+
		               " ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC), 103) IS NULL THEN '' ELSE CONVERT(varchar(30), "+
		               " (SELECT     TOP 1 OUVIDORIA_ENCAMINHAMENTO.DAENCAMINHAMENTO as 'Data2'  FROM    OUVIDORIA_ENCAMINHAMENTO(NOLOCK) "+
		               " WHERE   (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND  "+
		               " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND OUVIDORIA_ENCAMINHAMENTO.INSTATUS_ENVIO !=0 AND"+
		               " OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM = OUVIDORIA_ATENDIMENTO.incodigosetor AND OUVIDORIA_ENCAMINHAMENTO.SMCANCELADO=0 "+
		               " ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC), 103) END as 'Data_Encaminhamento'";
		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("SETOR_ENCAMINHADO")){

			sql +=" CASE WHEN (SELECT     TOP 1 GLOB_SETOR.DESCRICAO FROM OUVIDORIA_ENCAMINHAMENTO(NOLOCK) "+
		               " LEFT OUTER JOIN GLOB_SETOR  ON (OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORDESTINO = GLOB_SETOR.CODIGO_SETOR ) "+
		               " WHERE      (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND "+
		               " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND OUVIDORIA_ENCAMINHAMENTO.INSTATUS_ENVIO !=0 AND"+
		               " OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM = OUVIDORIA_ATENDIMENTO.incodigosetor AND OUVIDORIA_ENCAMINHAMENTO.SMCANCELADO=0 "+
		               " ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC) IS NULL THEN '' ELSE "+
		               " (SELECT     TOP 1 GLOB_SETOR.DESCRICAO FROM OUVIDORIA_ENCAMINHAMENTO(NOLOCK) "+
		               " LEFT OUTER JOIN GLOB_SETOR  ON (OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORDESTINO = GLOB_SETOR.CODIGO_SETOR )  "+
		               " WHERE  (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND  "+
		               " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND OUVIDORIA_ENCAMINHAMENTO.INSTATUS_ENVIO !=0 AND "+
		               " OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM = OUVIDORIA_ATENDIMENTO.incodigosetor AND OUVIDORIA_ENCAMINHAMENTO.SMCANCELADO=0 "+
		               " ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC) END as 'Setor_Encaminhado'";


		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("DESCRICAO_ENCAMINHAMENTO")){

			sql += " CASE WHEN CONVERT(varchar(8000), "+
		                         " (SELECT     TOP 1 cast(OUVIDORIA_ENCAMINHAMENTO.vadespacho AS varchar(8000)) "+
		                         "   FROM          OUVIDORIA_ENCAMINHAMENTO(NOLOCK) "+
		                         "   WHERE      (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND  "+
		                         " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND "+
		                         "                         OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM <> OUVIDORIA_ATENDIMENTO.incodigosetor AND OUVIDORIA_ENCAMINHAMENTO.SMCANCELADO=0 "+
		                          "  ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC), 103) IS NULL THEN '' ELSE CONVERT(varchar(8000), "+
		                          " (SELECT     TOP 1 cast(OUVIDORIA_ENCAMINHAMENTO.vadespacho AS varchar(8000)) "+
		                          "  FROM          OUVIDORIA_ENCAMINHAMENTO(NOLOCK) "+
		                          "  WHERE      (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND  "+
		                          " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND "+
		                          "  OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM <> OUVIDORIA_ATENDIMENTO.incodigosetor AND OUVIDORIA_ENCAMINHAMENTO.SMCANCELADO=0 "+
		                          " ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC), 103) END as 'Descricao_Encaminhamento'";

		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("TEMPO_RESPOSTA")){


			sql +=" CASE WHEN CONVERT(varchar(30),(SELECT     TOP 1 OUVIDORIA_ENCAMINHAMENTO.DAENCAMINHAMENTO "+
		               " FROM OUVIDORIA_ENCAMINHAMENTO(NOLOCK)  WHERE      (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND  "+
		               " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND "+
		               " OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM = OUVIDORIA_ATENDIMENTO.incodigosetor "+
		               " ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC), 103) IS NULL THEN '' ELSE CONVERT(varchar(30), "+
		               " (SELECT     TOP 1 OUVIDORIA_ENCAMINHAMENTO.DAENCAMINHAMENTO  FROM    OUVIDORIA_ENCAMINHAMENTO(NOLOCK) "+
		               " WHERE   (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND  "+
		               " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND "+
		               " OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM = OUVIDORIA_ATENDIMENTO.incodigosetor "+
		               " ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC), 103) END as 'Tempo_Resposta', ";


			sql +=" CASE WHEN CONVERT(varchar(30),(SELECT     TOP 1 OUVIDORIA_ENCAMINHAMENTO.DAENCAMINHAMENTO "+
		                 " FROM OUVIDORIA_ENCAMINHAMENTO(NOLOCK) WHERE      (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND  "+
		                 " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND "+
		                 " OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM <> OUVIDORIA_ATENDIMENTO.incodigosetor "+
		                 " ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC), 103) IS NULL  "+
		                 " THEN  '' ELSE CONVERT(varchar(30),(SELECT     TOP 1 OUVIDORIA_ENCAMINHAMENTO.DAENCAMINHAMENTO "+
		                 " FROM OUVIDORIA_ENCAMINHAMENTO(NOLOCK) WHERE (OUVIDORIA_ENCAMINHAMENTO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.innumeroatendimento) AND "+
		                 " OUVIDORIA_ENCAMINHAMENTO.SMANOATENDIMENTO = OUVIDORIA_ATENDIMENTO.SMANOATENDIMENTO AND "+
		                 " OUVIDORIA_ENCAMINHAMENTO.INCODIGOSETORORIGEM <> OUVIDORIA_ATENDIMENTO.incodigosetor "+
		                 " ORDER BY OUVIDORIA_ENCAMINHAMENTO.INCODIGOENCAMINHAMENTO DESC), 103) END as 'Tempo_Resposta2'";

		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("DATA_RESPOSTA_PARCIAL")){

			sql += " CASE WHEN CONVERT(varchar(30), (SELECT TOP 1 DADATA "+
		                " FROM OUVIDORIA_RESPOSTA_PARCIAL "+
		                " WHERE      OUVIDORIA_RESPOSTA_PARCIAL.INCODIGOATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO AND "+
		                " OUVIDORIA_RESPOSTA_PARCIAL.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO "+
		                " ORDER BY INCODIGORESPOSTA), 103) IS NULL THEN '' ELSE CONVERT(varchar(30), "+
		                " (SELECT     TOP 1 DADATA FROM  OUVIDORIA_RESPOSTA_PARCIAL "+
		                " WHERE      OUVIDORIA_RESPOSTA_PARCIAL.INCODIGOATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO AND "+
		                " OUVIDORIA_RESPOSTA_PARCIAL.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO "+
		                " ORDER BY INCODIGORESPOSTA), 103) END as 'Data_Resposta_Parcial'";

		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("DESCRICAO_RESPOSTA_PARCIAL")){

			sql += " CASE WHEN CONVERT(varchar(8000), "+
		               " (SELECT     TOP 1 cast(DESCRICAO AS varchar(8000)) FROM  OUVIDORIA_RESPOSTA_PARCIAL "+
		               " WHERE      OUVIDORIA_RESPOSTA_PARCIAL.INCODIGOATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO AND  "+
		               " OUVIDORIA_RESPOSTA_PARCIAL.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO "+
		               " ORDER BY INCODIGORESPOSTA), 103) IS NULL THEN '' ELSE CONVERT(varchar(8000), "+
		               " (SELECT     TOP 1 cast(DESCRICAO AS varchar(8000)) FROM   OUVIDORIA_RESPOSTA_PARCIAL " +
		               " WHERE      OUVIDORIA_RESPOSTA_PARCIAL.INCODIGOATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO AND  "+
		               " OUVIDORIA_RESPOSTA_PARCIAL.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO "+
		               " ORDER BY INCODIGORESPOSTA)) END as 'Descricao_Resposta_Parcial'";

		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("DATA_DESPACHO")){

			sql += " CASE WHEN CONVERT(varchar(30), (SELECT     TOP 1 DADESPACHO "+
		               " FROM OUVIDORIA_DESPACHO "+
		               " WHERE  OUVIDORIA_DESPACHO.INCODIGO_ATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO AND  "+
		               " OUVIDORIA_DESPACHO.INCODIGO_ORGAO_ORIGEM = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO "+
		               " ORDER BY INCODIGO_DESPACHO), 103) IS NULL THEN '' ELSE CONVERT(varchar(30), "+
		               " (SELECT     TOP 1 DADESPACHO  FROM OUVIDORIA_DESPACHO "+
		               " WHERE  OUVIDORIA_DESPACHO.INCODIGO_ATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO AND "+
		               " OUVIDORIA_DESPACHO.INCODIGO_ORGAO_ORIGEM = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO "+
		               " ORDER BY INCODIGO_DESPACHO), 103) END as 'Data_Despacho'";

		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("DESCRICAO_DESPACHO")){


			sql +=" CASE WHEN CONVERT(varchar(8000), (SELECT     TOP 1 cast(VADESPACHO AS varchar(8000)) "+
		               " FROM          OUVIDORIA_DESPACHO "+
		               " WHERE      OUVIDORIA_DESPACHO.INCODIGO_ATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO AND  "+
		               " OUVIDORIA_DESPACHO.INCODIGO_ORGAO_ORIGEM = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO "+
		               " ORDER BY INCODIGO_DESPACHO), 103) IS NULL THEN '' ELSE CONVERT(varchar(8000), "+
		               " (SELECT     TOP 1 cast(VADESPACHO AS varchar(8000)) FROM OUVIDORIA_DESPACHO "+
		               " WHERE      OUVIDORIA_DESPACHO.INCODIGO_ATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO AND "+
		               " OUVIDORIA_DESPACHO.INCODIGO_ORGAO_ORIGEM = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO "+
		               " ORDER BY INCODIGO_DESPACHO), 103) END as 'Descricao_despacho'";

//		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("PERGUNTA_1")){
//
//			sql += "  CASE WHEN (SELECT     TOP 1 CASE WHEN INTIPOUSUARIO = 1 THEN 'Servidor Público' WHEN INTIPOUSUARIO = 2 "+
//		               " THEN 'Cidadão Usuário' ELSE 'pesquisa Não Realizada' END "+
//		               " FROM  OUVIDORIA_SATISFACAO "+
//		               " WHERE      OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		               " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) IS NULL "+
//		               " THEN '' ELSE "+
//		               " (SELECT     TOP 1 CASE WHEN INTIPOUSUARIO = 1 THEN 'Servidor Público' WHEN INTIPOUSUARIO = 2 "+
//		               " THEN 'Cidadão Usuário' ELSE 'pesquisa Não Realizada' END "+
//		               " FROM  OUVIDORIA_SATISFACAO "+
//		               " WHERE      OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		               " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) END ";
//
//		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("PERGUNTA_2")){
//
//			sql +="  CASE WHEN (SELECT TOP 1 "+
//		              " CASE WHEN INCOMOSOUBEOUVIDORIA = 1 THEN 'Amigos'  "+
//		              " WHEN INCOMOSOUBEOUVIDORIA  = 2 THEN 'Órgãos do Governo'  "+
//		              " WHEN INCOMOSOUBEOUVIDORIA  = 3 THEN 'Panfleto/Cartazes:'  "+
//		              " WHEN INCOMOSOUBEOUVIDORIA  = 4 THEN 'Internet'  "+
//		              " WHEN INCOMOSOUBEOUVIDORIA  = 5 THEN 'Mídia (jornais, televisão, rádio)'  "+
//		              " ELSE 'pesquisa Não Realizada' END "+
//		              " FROM OUVIDORIA_SATISFACAO "+
//		              " WHERE      OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		              " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) IS NULL "+
//		              " THEN '' ELSE "+
//		              " (SELECT TOP 1 "+
//		              " CASE WHEN INCOMOSOUBEOUVIDORIA = 1 THEN 'Amigos'  "+
//		              " WHEN INCOMOSOUBEOUVIDORIA  = 2 THEN 'Órgãos do Governo'  "+
//		              " WHEN INCOMOSOUBEOUVIDORIA  = 3 THEN 'Panfleto/Cartazes:'  "+
//		              " WHEN INCOMOSOUBEOUVIDORIA  = 4 THEN 'Internet'  "+
//		              " WHEN INCOMOSOUBEOUVIDORIA  = 5 THEN 'Mídia (jornais, televisão, rádio)'  "+
//		              " ELSE 'pesquisa Não Realizada' END "+
//		              " FROM OUVIDORIA_SATISFACAO "+
//		              " WHERE      OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		              " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) END";
//
//
//		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("PERGUNTA_3")){
//
//			sql += "  CASE WHEN (SELECT     TOP 1  "+
//		                 " CASE WHEN INQUALIDADERESPOSTA = 1 THEN 'Satisfatória'  "+
//		                 " WHEN INQUALIDADERESPOSTA = 2 THEN 'Parcialmente Satisfatória'  "+
//		                 " WHEN INQUALIDADERESPOSTA = 3 THEN 'Insatisfatória'  "+
//		                 " ELSE 'pesquisa Não Realizada' END "+
//		                 " FROM          OUVIDORIA_SATISFACAO "+
//		                 " WHERE      OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		                 " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) IS NULL "+
//		                 " THEN '' ELSE "+
//		                 " (SELECT     TOP 1  "+
//		                 " CASE WHEN INQUALIDADERESPOSTA = 1 THEN 'Satisfatória'  "+
//		                 " WHEN INQUALIDADERESPOSTA = 2 THEN 'Parcialmente Satisfatória'  "+
//		                 " WHEN INQUALIDADERESPOSTA = 3 THEN 'Insatisfatória'  "+
//		                 " ELSE 'pesquisa Não Realizada' END "+
//		                 " FROM          OUVIDORIA_SATISFACAO "+
//		                 " WHERE      OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		                 " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) END ";
//
//
//
//		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("PERGUNTA_4")){
//
//			sql += "  CASE WHEN (SELECT     TOP 1  "+
//		                 " CASE WHEN INPAPELOUVIDORIA = 1 THEN 'Importante'  "+
//		                 " WHEN INPAPELOUVIDORIA = 2 THEN 'Muito Importante'  "+
//		                 " WHEN INPAPELOUVIDORIA = 3 THEN 'Pouco Importante' "+
//		                 " ELSE 'pesquisa Não Realizada' END "+
//		                 " FROM          OUVIDORIA_SATISFACAO "+
//		                 " WHERE      OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		                 " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) IS NULL "+
//		                 " THEN '' ELSE "+
//		                 " (SELECT     TOP 1  "+
//		                 " CASE WHEN INPAPELOUVIDORIA = 1 THEN 'Importante'  "+
//		                 " WHEN INPAPELOUVIDORIA = 2 THEN 'Muito Importante'  "+
//		                 " WHEN INPAPELOUVIDORIA = 3 THEN 'Pouco Importante' "+
//		                 " ELSE 'pesquisa Não Realizada' END "+
//		                 " FROM          OUVIDORIA_SATISFACAO "+
//		                 " WHERE      OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		                 " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) END ";
//
//
//
//		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("PERGUNTA_5")){
//
//			sql += "  CASE WHEN (SELECT     TOP 1  "+
//		                 " CASE WHEN INTEMPORESPOSTA = 1 THEN 'Rápido'  "+
//		                 " WHEN INTEMPORESPOSTA = 2 THEN 'Normal'  "+
//		                 " WHEN INTEMPORESPOSTA = 3 THEN 'Demorado'  "+
//		                 " ELSE 'pesquisa Não Realizada' END "+
//		                 " FROM OUVIDORIA_SATISFACAO "+
//		                 " WHERE OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		                 " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) IS NULL "+
//		                 " THEN '' ELSE "+
//		                 " (SELECT     TOP 1  "+
//		                 " CASE WHEN INTEMPORESPOSTA = 1 THEN 'Rápido'  "+
//		                 " WHEN INTEMPORESPOSTA = 2 THEN 'Normal'  "+
//		                 " WHEN INTEMPORESPOSTA = 3 THEN 'Demorado'  "+
//		                 " ELSE 'pesquisa Não Realizada' END "+
//		                 " FROM OUVIDORIA_SATISFACAO "+
//		                 " WHERE OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		                 " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) END";
//
//
//
//
//		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("PERGUNTA_6")){
//
//			sql += "  CASE WHEN (SELECT     TOP 1  "+
//		                 " CASE WHEN INREUTILIZAOUVIDORIA = 1 THEN 'Sim'  "+
//		                 " WHEN INREUTILIZAOUVIDORIA = 2 THEN 'Não'  "+
//		                 " ELSE 'Pesquisa não realizada' END "+
//		                 " FROM  OUVIDORIA_SATISFACAO "+
//		                 " WHERE OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		                 " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) IS NULL "+
//		                 " THEN '' ELSE "+
//		                 " (SELECT     TOP 1  "+
//		                 " CASE WHEN INREUTILIZAOUVIDORIA = 1 THEN 'Sim'  "+
//		                 " WHEN INREUTILIZAOUVIDORIA = 2 THEN 'Não'  "+
//		                 " ELSE 'Pesquisa não realizada' END "+
//		                 " FROM  OUVIDORIA_SATISFACAO "+
//		                 " WHERE OUVIDORIA_SATISFACAO.INCODIGOORGAO = OUVIDORIA_ATENDIMENTO.INCODIGOORGAO AND  "+
//		                 " OUVIDORIA_SATISFACAO.INNUMEROATENDIMENTO = OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO) END";




		}else if(request.getCamposTipoExpressao().get(i).getValue().equals("CASE WHEN OUVIDORIA_ATENDIMENTO.DACONCLUSAO  &lt; '01/01/1990' THEN 'Em aberto' ELSE convert(varchar(30),OUVIDORIA_ATENDIMENTO.DACONCLUSAO,103)  END")){

			sql += "CASE WHEN OUVIDORIA_ATENDIMENTO.DACONCLUSAO  < '01/01/1990' THEN 'Em aberto' ELSE convert(varchar(30),OUVIDORIA_ATENDIMENTO.DACONCLUSAO,103)  END";

		}else {
			sql += request.getCamposTipoExpressao().get(i).getValue();
		}
		
    }
		    
		    
	 String sqlTotal =  " SELECT OUVIDORIA_ATENDIMENTO.INATENDIMENTOID, OUVIDORIA_ATENDIMENTO.VANUMPROTOCOLO"+sql
              +" FROM  OUVIDORIA_ATENDIMENTO(NOLOCK) "
              +" LEFT OUTER JOIN GLOB_ORGAO(NOLOCK) " 
              +" ON (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = GLOB_ORGAO.CODIGO_ORGAO ) "
              +" LEFT OUTER JOIN OUVIDORIA_ORIGEM_MANIFESTACAO(NOLOCK) " 
              +" ON (OUVIDORIA_ATENDIMENTO.INCODIGOORIGEMMANIFESTACAO = OUVIDORIA_ORIGEM_MANIFESTACAO.INCODIGOORIGEMMANIFESTACAO ) "
              + " LEFT OUTER JOIN OUVIDORIA_PRIORIZACAO(NOLOCK) " 
              +" ON (OUVIDORIA_ATENDIMENTO.INCODIGOPRIORIZACAO = OUVIDORIA_PRIORIZACAO.INCODIGOPRIORIZACAO) "
              + " LEFT OUTER JOIN OUVIDORIA_NATUREZA(NOLOCK) " 
              +" ON (OUVIDORIA_ATENDIMENTO.INCODIGONATUREZA = OUVIDORIA_NATUREZA.INCODIGONATUREZA) "
              +" LEFT OUTER JOIN GLOB_USUARIO(NOLOCK) " 
              +" ON (OUVIDORIA_ATENDIMENTO.INCODIGOUSUARIO = GLOB_USUARIO.CODIGO_USUARIO ) " 
              +" LEFT OUTER JOIN OUVIDORIA_ASSUNTO(NOLOCK) " 
              +" ON  (OUVIDORIA_ATENDIMENTO.INCODIGOASSUNTONOVO    = OUVIDORIA_ASSUNTO.INCODIGOASSUNTONOVO) " 
              +" AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO    = OUVIDORIA_ASSUNTO.INCODIGOORGAO) "
              +" LEFT OUTER JOIN OUVIDORIA_AREAASSUNTO(NOLOCK) " 
              +" ON  (OUVIDORIA_ASSUNTO.INCODIGOAREANOVO    = OUVIDORIA_AREAASSUNTO.INCODIGOAREA) " 
              +" AND (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO    = OUVIDORIA_AREAASSUNTO.INCODIGOORGAO) "
              +" LEFT OUTER JOIN GLOB_MUNICIPIO(NOLOCK) "
              +" ON (OUVIDORIA_ATENDIMENTO.INCODIGOMUNICIPIO = GLOB_MUNICIPIO.CODIGO_MUNICIPIO ) "
            + " WHERE     (OUVIDORIA_ATENDIMENTO.INCODIGOORGAO = ?)  AND (OUVIDORIA_ATENDIMENTO.DAENTRADA  >= ? AND OUVIDORIA_ATENDIMENTO.DAENTRADA  <= ?) "
            + " AND (OUVIDORIA_ATENDIMENTO.SMSTATUS <> 2) "
            +" ORDER BY OUVIDORIA_ATENDIMENTO.INNUMEROATENDIMENTO DESC";
	 
	 
     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  
	  
		Query query =  manager.createNativeQuery(sqlTotal);
		query.setParameter(1, request.getOrgao());
		query.setParameter(2, dateFormat.format(request.getDataInicial()));
		query.setParameter(3, dateFormat.format(request.getDataFinal()));



		@SuppressWarnings("rawtypes")
		List lista = query.getResultList();
		List<DadosRelatorioGeral> novaLista = new ArrayList<>(lista.size());
		
		for (int i = 0; i < lista.size(); i++) {
    		Object obj = lista.get(i);
			Object[] itensObj = (Object[])obj;


		        switch (itensObj.length){
		            case 3:
		    			novaLista.add(new DadosRelatorioGeral(
		    					itensObj[0].toString(), itensObj[1].toString(), itensObj[2].toString(), null, null, null, null, null, null, null, null
    					));					
		            break;
		            case 4:
		    			novaLista.add(new DadosRelatorioGeral(
		    					itensObj[0].toString(), 
		    					itensObj[1] == null? null : itensObj[1].toString(),
		    				    itensObj[2] == null? null : itensObj[2].toString(),
		    				    itensObj[3] == null? null : itensObj[3].toString(),
		    					null, null, null, null, null, null, null
    					));					
		            break;
		            case 5:
		    			novaLista.add(new DadosRelatorioGeral(
		    					itensObj[0].toString(), itensObj[1] == null? null : itensObj[1].toString(),
		    					itensObj[2] == null? null : itensObj[2].toString(),
		    					itensObj[3] == null? null : itensObj[3].toString(), 
		    					itensObj[4] == null? null : itensObj[4].toString(),
		    					null, null, null, null, null, null
    					));
		    		break;
		            case 6:
		    			novaLista.add(new DadosRelatorioGeral(
		    					itensObj[0].toString(), itensObj[1] == null? null : itensObj[1].toString(),
		    					itensObj[2] == null? null : itensObj[2].toString(),
		    					itensObj[3] == null? null : itensObj[3].toString(), 
		    					itensObj[4] == null? null : itensObj[4].toString(), 
		    					itensObj[5] == null? null : itensObj[5].toString(),
		    					null, null, null, null, null
    					));
		    		break;
		            case 7: 
		    			novaLista.add(new DadosRelatorioGeral(
		    					itensObj[0].toString(), itensObj[1] == null? null : itensObj[1].toString(),
		    					itensObj[2] == null? null : itensObj[2].toString(),
		    					itensObj[3] == null? null : itensObj[3].toString(), 
		    					itensObj[4] == null? null : itensObj[4].toString(),
		    				    itensObj[5] == null? null : itensObj[5].toString(),
		    				    itensObj[6] == null? null : itensObj[6].toString(),
		    					null, null, null, null
    					));		
		    		break;
		            case 8: 
		    			novaLista.add(new DadosRelatorioGeral(
		    					itensObj[0].toString(), 
		    					itensObj[1] == null? null : itensObj[1].toString(),
		    					itensObj[2] == null? null : itensObj[2].toString(),
		    					itensObj[3] == null? null : itensObj[3].toString(), 
		    					itensObj[4] == null? null : itensObj[4].toString(),
		    					itensObj[5] == null? null : itensObj[5].toString(),
		    					itensObj[6] == null? null : itensObj[6].toString(),
		    					itensObj[7] == null? null : itensObj[7].toString(),
		    					null, null, null
    				));		
		    			break;
		            case 9:
		    			novaLista.add(new DadosRelatorioGeral(
		    					itensObj[0].toString(), 
		    					itensObj[1] == null? null : itensObj[1].toString(),
		    					itensObj[2] == null? null : itensObj[2].toString(),
		    					itensObj[3] == null? null : itensObj[3].toString(), 
		    					itensObj[4] == null? null : itensObj[4].toString(),
		    					itensObj[5] == null? null : itensObj[5].toString(),
		    					itensObj[6] == null? null : itensObj[6].toString(),
		    					itensObj[7] == null? null : itensObj[7].toString(),
		    					itensObj[8] == null? null : itensObj[8].toString(),
		    					null, null
    					));		
		    		break;
		            case 10:
		    			novaLista.add(new DadosRelatorioGeral(
		    					itensObj[0].toString(), 
		    					itensObj[1] == null? null : itensObj[1].toString(),
		    					itensObj[2] == null? null : itensObj[2].toString(),
		    					itensObj[3] == null? null : itensObj[3].toString(), 
		    					itensObj[4] == null? null : itensObj[4].toString(),
		    					itensObj[5] == null? null : itensObj[5].toString(),
		    					itensObj[6] == null? null : itensObj[6].toString(),
		    					itensObj[7] == null? null : itensObj[7].toString(),
		    					itensObj[8] == null? null : itensObj[8].toString(),
		    					itensObj[9] == null? null : itensObj[9].toString(),
		    					null
    					));		
		    		break;
		            case 11:
		    			novaLista.add(new DadosRelatorioGeral(
		    					itensObj[0].toString(), 
		    					itensObj[1] == null? null : itensObj[1].toString(),
		    					itensObj[2] == null? null : itensObj[2].toString(),
		    					itensObj[3] == null? null : itensObj[3].toString(), 
		    					itensObj[4] == null? null : itensObj[4].toString(),
		    					itensObj[5] == null? null : itensObj[5].toString(),
		    					itensObj[6] == null? null : itensObj[6].toString(),
		    					itensObj[7] == null? null : itensObj[7].toString(),
		    					itensObj[8] == null? null : itensObj[8].toString(),
		    					itensObj[9] == null? null : itensObj[9].toString()		    							,
		    					itensObj[10] == null? null : itensObj[10].toString()
    					));		
		    		break;
		        }
			
		}
		
		RetornoRelatorioGeral retorno = new RetornoRelatorioGeral(colunas, novaLista);
	
		return retorno;
	}
	

	
	

}
