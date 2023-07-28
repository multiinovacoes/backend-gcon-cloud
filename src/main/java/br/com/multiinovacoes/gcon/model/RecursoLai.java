package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "LAI_RECURSO")
public class RecursoLai implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 401892489143052481L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO_RECURSO")
	private Long id;
	
	@Column(name = "CODIGO_PEDIDO")
	private Long atendimento;
	
	@Column(name = "TIPO_RECURSO")
	private Integer tipo;
	
	@Column(name = "DATA_RECURSO")
    private LocalDateTime dataRecurso;
    
	@Column(name = "DATA_PRAZO")
    private LocalDate dataPrazo;
    
	@Column(name = "JUSTITICATIVA")
    private String justificativa;
    
	@Column(name = "RESPOSTA")
    private String respostaRecurso;
    
	@Column(name = "DATA_RESPOSTA")
    private LocalDateTime dataResposta;
    
	@Column(name = "CODIGO_USUARIO_RESPOSTA")
    private Long idUsuarioResposta;
    
	@Column(name = "STATUS")
    private Integer status;

	@Column(name = "PARAMETRO")
    private String parametro;

	@Column(name = "RESULTADO")
    private Integer resultado;

    public RecursoLai() {
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


	public Integer getTipo() {
		return tipo;
	}


	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}


	public LocalDateTime getDataRecurso() {
		return dataRecurso;
	}


	public void setDataRecurso(LocalDateTime dataRecurso) {
		this.dataRecurso = dataRecurso;
	}


	public LocalDate getDataPrazo() {
		return dataPrazo;
	}


	public void setDataPrazo(LocalDate dataPrazo) {
		this.dataPrazo = dataPrazo;
	}


	public String getJustificativa() {
		return justificativa;
	}


	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}


	public String getRespostaRecurso() {
		return respostaRecurso;
	}


	public void setRespostaRecurso(String respostaRecurso) {
		this.respostaRecurso = respostaRecurso;
	}


	public LocalDateTime getDataResposta() {
		return dataResposta;
	}


	public void setDataResposta(LocalDateTime dataResposta) {
		this.dataResposta = dataResposta;
	}


	public Long getIdUsuarioResposta() {
		return idUsuarioResposta;
	}


	public void setIdUsuarioResposta(Long idUsuarioResposta) {
		this.idUsuarioResposta = idUsuarioResposta;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
	public Integer getResultado() {
		return resultado;
	}


	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}


	public String getParametro() {
		return parametro;
	}


	public void setParametro(String parametro) {
		this.parametro = parametro;
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
		RecursoLai other = (RecursoLai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
    
    
    
    
    

}
