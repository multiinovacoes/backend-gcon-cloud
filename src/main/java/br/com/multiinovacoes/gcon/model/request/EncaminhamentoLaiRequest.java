package br.com.multiinovacoes.gcon.model.request;

import java.time.LocalDateTime;
import java.util.List;


public class EncaminhamentoLaiRequest {
	
	
	private Long id;

	private Long atendimento;

	private Long setorOrigem;

	private Long setorDestino;

	private LocalDateTime dataEncaminhamento;

	private String despacho;
	
	private String resposta;
	
	private String emailEnviado;

	private Long modeloDocumento;

	private Integer recebido;

	private Integer satisfaz;

	private  Integer status;
	
	private boolean enviarRespostaParcial;
	
	private List<Long>selectedAnexos;	

	public List<Long> getSelectedAnexos() {
		return selectedAnexos;
	}

	public void setSelectedAnexos(List<Long> selectedAnexos) {
		this.selectedAnexos = selectedAnexos;
	}

	public boolean isEnviarRespostaParcial() {
		return enviarRespostaParcial;
	}

	public void setEnviarRespostaParcial(boolean enviarRespostaParcial) {
		this.enviarRespostaParcial = enviarRespostaParcial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Long atendimento) {
		this.atendimento = atendimento;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Long getSetorOrigem() {
		return setorOrigem;
	}

	public void setSetorOrigem(Long setorOrigem) {
		this.setorOrigem = setorOrigem;
	}

	public Long getSetorDestino() {
		return setorDestino;
	}

	public void setSetorDestino(Long setorDestino) {
		this.setorDestino = setorDestino;
	}

	public LocalDateTime getDataEncaminhamento() {
		return dataEncaminhamento;
	}

	public void setDataEncaminhamento(LocalDateTime dataEncaminhamento) {
		this.dataEncaminhamento = dataEncaminhamento;
	}

	public String getDespacho() {
		return despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

	public String getEmailEnviado() {
		return emailEnviado;
	}

	public void setEmailEnviado(String emailEnviado) {
		this.emailEnviado = emailEnviado;
	}


	public Long getModeloDocumento() {
		return modeloDocumento;
	}

	public void setModeloDocumento(Long modeloDocumento) {
		this.modeloDocumento = modeloDocumento;
	}

	public Integer getRecebido() {
		return recebido;
	}

	public void setRecebido(Integer recebido) {
		this.recebido = recebido;
	}

	public Integer getSatisfaz() {
		return satisfaz;
	}

	public void setSatisfaz(Integer satisfaz) {
		this.satisfaz = satisfaz;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EncaminhamentoLaiRequest other = (EncaminhamentoLaiRequest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	

}
