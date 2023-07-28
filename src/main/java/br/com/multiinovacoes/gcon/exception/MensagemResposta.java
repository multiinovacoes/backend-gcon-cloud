package br.com.multiinovacoes.gcon.exception;

import java.time.LocalDateTime;


public class MensagemResposta {
	
	private LocalDateTime data;
	
	private String mensagem;

	public MensagemResposta() {
		super();
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	

}
