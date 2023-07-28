package br.com.multiinovacoes.gcon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.enums.StatusEnum;
import br.com.multiinovacoes.gcon.model.Atendimento;
import br.com.multiinovacoes.gcon.model.PesquisaSatisfacao;
import br.com.multiinovacoes.gcon.model.RespostaSatisfacao;
import br.com.multiinovacoes.gcon.model.dto.PerguntaSatisfacaoDto;
import br.com.multiinovacoes.gcon.model.dto.PesquisaSatisfacaoDto;
import br.com.multiinovacoes.gcon.model.mapper.PerguntaSatisfacaoMapper;
import br.com.multiinovacoes.gcon.model.request.PesquisaSatisfacaoRequest;
import br.com.multiinovacoes.gcon.repository.AtendimentoRepository;
import br.com.multiinovacoes.gcon.repository.PerguntaSatisfacaoRepository;
import br.com.multiinovacoes.gcon.repository.PesquisaSatisfacaoRepository;
import br.com.multiinovacoes.gcon.repository.RespostaSatisfacaoRepository;

@Service
@Transactional
public class PesquisaSatisfacaoService {
	
	@Autowired
	private PesquisaSatisfacaoRepository pesquisaSatisfacaoRepository;
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private PerguntaSatisfacaoRepository perguntaSatisfacaoRepository;
	
	@Autowired
	private RespostaSatisfacaoRepository respostaSatisfacaoRepository;
	
	@Autowired
	private PerguntaSatisfacaoMapper perguntaSatisfacaoMapper;

	
	public Boolean getSalvarPesquisaSatisfacao(PesquisaSatisfacaoRequest pesquisaSatisfacao) {
			try {
				Optional<Atendimento> atendimento = atendimentoRepository.findById(pesquisaSatisfacao.getIdAtendimento());
				if (atendimento.isPresent()) {
					for (PesquisaSatisfacaoDto pergunta : pesquisaSatisfacao.getListaPergunta()) {
						PesquisaSatisfacao pesquisa = new PesquisaSatisfacao();
						pesquisa.setIdAtendimento(pesquisaSatisfacao.getIdAtendimento());
						pesquisa.setIdPergunta(pergunta.getId());
						pesquisa.setIdResposta(pergunta.getIdResposta());
						pesquisa.setId(pesquisaSatisfacaoRepository.getMaxId()+1);
						pesquisaSatisfacaoRepository.save(pesquisa);
					}
					atendimento.get().setSatisfaz(1);
					atendimentoRepository.save(atendimento.get());
					return true;
				}else {
					return false;
			}
		}catch (Exception e) {
			return false;
		}
	}
	
	public List<PerguntaSatisfacaoDto> listaPesquisaSatisfacao(Long orgao){
		List<PerguntaSatisfacaoDto> listaPerguntas = perguntaSatisfacaoMapper.fromResponseList(perguntaSatisfacaoRepository.findByOrgaoAndStatus(orgao, StatusEnum.ATIVO.getCodigo()));
		List<RespostaSatisfacao> listaRespostasSatisfacao = new ArrayList<>();
		
		for (int i = 0; i < listaPerguntas.size(); i++) {
			listaRespostasSatisfacao = respostaSatisfacaoRepository.findByPerguntaSatisfacaoAndStatus(listaPerguntas.get(i).getId(), StatusEnum.ATIVO.getCodigo());
			listaPerguntas.get(i).setListaRespostaSatisfacao(listaRespostasSatisfacao);
		}
		return listaPerguntas;
	}	


}
