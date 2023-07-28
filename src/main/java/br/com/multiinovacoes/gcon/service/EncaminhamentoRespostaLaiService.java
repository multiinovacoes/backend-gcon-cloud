package br.com.multiinovacoes.gcon.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.multiinovacoes.gcon.mail.Mailer;
import br.com.multiinovacoes.gcon.model.AnexoLai;
import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.EncaminhamentoLai;
import br.com.multiinovacoes.gcon.model.EncaminhamentoRespostaLai;
import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoRespostaLaiDto;
import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoRespostaLaiSetorDto;
import br.com.multiinovacoes.gcon.model.dto.ListaAnexoDto;
import br.com.multiinovacoes.gcon.model.mapper.EncaminhamentoRespostaLaiMapper;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoRespostaLaiRequest;
import br.com.multiinovacoes.gcon.repository.AnexoLaiRepository;
import br.com.multiinovacoes.gcon.repository.AtendimentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoRespostaLaiRepository;
import br.com.multiinovacoes.gcon.repository.SetorRepository;

@Service
@Transactional
public class EncaminhamentoRespostaLaiService {
	
	
	@Autowired
	EncaminhamentoRespostaLaiMapper encaminhamentoRespostaLaiMapper;
	
	@Autowired
	EncaminhamentoRespostaLaiRepository encaminhamentoRespostaRepository;

	@Autowired
	SetorRepository setorRepository;
	
	@Autowired
	EncaminhamentoLaiRepository encaminhamentoRepository;
	
	@Autowired
	AtendimentoLaiRepository atendimentoRepository;
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	private AnexoLaiRepository anexoLaiRepository;
	
	
	
	public void salvarEncaminhamentoResposta(EncaminhamentoRespostaLaiRequest request) {
	
		EncaminhamentoLai encaminhamento = encaminhamentoRepository.getById(request.getEncaminhamento());
		AtendimentoLai atendimento = atendimentoRepository.getById(encaminhamento.getAtendimento());
		EncaminhamentoRespostaLai resposta = new EncaminhamentoRespostaLai();
		resposta.setEncaminhamento(request.getEncaminhamento());
		resposta.setDataResposta(LocalDateTime.now());
		resposta.setDespacho(request.getDespacho());
		resposta.setModeloDocumento(request.getModeloDocumento());
		resposta.setSatisfaz(request.getSatisfaz());
		resposta.setStatus(EncaminhamentoRespostaLai.STATUS_RETORNO_MANUAL);
		encaminhamentoRespostaRepository.save(resposta);
        
        atendimento.setSatisfaz(request.getSatisfaz());
		atendimentoRepository.save(atendimento);

		
	}
	
	public void salvarEncaminhamentoResposta(EncaminhamentoRespostaLaiSetorDto encaminhamentoRespostaSetor) throws IOException {
		EncaminhamentoLai encaminhamento = encaminhamentoRepository.findByParametro(encaminhamentoRespostaSetor.getParametro());
		if (encaminhamento != null) {
			AtendimentoLai atendimento = atendimentoRepository.getById(encaminhamento.getAtendimento());
			EncaminhamentoRespostaLai resposta = new EncaminhamentoRespostaLai();
			resposta.setEncaminhamento(encaminhamento.getId());
			resposta.setDataResposta(LocalDateTime.now());
			resposta.setDespacho(encaminhamentoRespostaSetor.getResposta());
			resposta.setSetorOrigem(encaminhamento.getSetorDestino());
			resposta.setSetorDestino(encaminhamento.getSetorOrigem());
			
			resposta.setModeloDocumento(0l);
			resposta.setSatisfaz(0);
			resposta.setStatus(EncaminhamentoRespostaLai.STATUS_RETORNO);
			encaminhamentoRespostaRepository.save(resposta);
			
			encaminhamentoRepository.save(encaminhamento);
			
			String[] anexosEmail = null;
		    
		    if (encaminhamentoRespostaSetor.getListaAnexoDto() != null) {
		    	anexosEmail = new String[encaminhamentoRespostaSetor.getListaAnexoDto().size()];
		    	for (int i = 0; i < anexosEmail.length; i++) {
		    		ListaAnexoDto anexos = encaminhamentoRespostaSetor.getListaAnexoDto().get(i); 					
			    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			    	String nomeArq = timeStamp + "_" + anexos.getNomeArquivo();
			        buildFileUBEC(anexos.getStringBase64(), nomeArq, atendimento.getOrgao());
			    	AnexoLai anexo = new AnexoLai();
			    	anexo.setAtendimento(atendimento.getId());
			    	anexo.setDataAnexo(LocalDate.now());
			    	anexo.setNomeArquivo(nomeArq);
			    	anexo.setResponsavel(2);
			    	anexoLaiRepository.save(anexo);
			    	anexosEmail[i] = "C:\\jboss-4.2.1.GA_CLOUD\\server\\default\\deploy\\multiwork.war\\arquivos\\"+atendimento.getOrgao().toString()+"\\lai\\" + anexo.getNomeArquivo();
			    }
		    }
		    	
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    
	        String conteudo = "Prezado(a) Senhor(a), sua resposta do encaminhamento referente ao atendimento nº " +atendimento.getAnoAtendimento()+""+atendimento.getNumeroAtendimento() +" enviado pelo SAC a seus cuidados em " + encaminhamento.getDataEncaminhamento().format(formatter)+" foi recebida com sucesso pelo sistema em " +  resposta.getDataResposta().format(formatter) +  ".";

	    	Map<String, Object> variaveis = new HashMap<>();
	    	variaveis.put("conteudo", conteudo);
			variaveis.put("despacho", resposta.getDespacho());
			String template = "mail/emailrespostasetor";
			mailer.enviarEmail(Arrays.asList(encaminhamento.getEmailEnviado()), template, variaveis, "Confirmação de Resposta de encaminhamento do atendimento N° " + atendimento.getNumeroProtocolo(), "Resposta do Encaminhamento", anexosEmail);
		}
	}
	
	
	public EncaminhamentoRespostaLaiDto getEncaminhamentoResposta(Long codigoEncaminhamento) {
		EncaminhamentoRespostaLai enc = encaminhamentoRespostaRepository.consultaEncaminhamentoResposta(codigoEncaminhamento);
		EncaminhamentoRespostaLaiDto encDto = encaminhamentoRespostaLaiMapper.toDto(enc);
		encDto.setDescricaoSetorOrigem(setorRepository.getById(encDto.getSetorOrigem()).getDescricao());
		return encDto;
	}


	public EncaminhamentoRespostaLai findByCodigoEncaminhamento(Long codigoEncaminhamento) {
		return encaminhamentoRespostaRepository.findByEncaminhamento(codigoEncaminhamento);
	}

	public void atualizar(EncaminhamentoRespostaLaiRequest resposta) {
		EncaminhamentoRespostaLaiDto encaminhamentoRespostaDto = encaminhamentoRespostaLaiMapper.fromEncaminhamentoRespostaLai(resposta); 
		EncaminhamentoLai encaminhamento = encaminhamentoRepository.getById(resposta.getEncaminhamento());
		AtendimentoLai atendimento = atendimentoRepository.getById(encaminhamento.getAtendimento());
		encaminhamentoRespostaRepository.save(encaminhamentoRespostaLaiMapper.toEncaminhamentoLai(encaminhamentoRespostaDto));
		atendimento.setSatisfaz(resposta.getSatisfaz());
		atendimentoRepository.save(atendimento);
	}
	
	
	private void buildFileUBEC(String base64, String urlAnexo, Long codigoOrgao)throws IOException {
		try {
			byte[] anexo2 = Base64.decodeBase64(base64.split(",")[1]);
			OutputStream out = new FileOutputStream(
					"C:\\jboss-4.2.1.GA_CLOUD\\server\\default\\deploy\\multiwork.war\\arquivos\\"+codigoOrgao.toString()+"\\lai\\"+urlAnexo);
			out.write(anexo2);
			out.close();
		}catch (Exception e) {
			e.getMessage();
		}
			
	}	

}
