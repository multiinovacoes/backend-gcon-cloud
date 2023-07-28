package br.com.multiinovacoes.gcon.model.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.multiinovacoes.gcon.model.dto.ListaAnexoDto;
import io.swagger.annotations.ApiModelProperty;

public class RecursoLaiRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 401892489143052481L;
	

	private Long id;
	
	private Long atendimento;
	
	private Integer tipo;
	
    private LocalDateTime dataRecurso;
    
    private LocalDate dataPrazo;
    
    private String justificativa;
    
    private String respostaRecurso;
    
    private LocalDateTime dataResposta;
    
    private Long idUsuarioResposta;
    
    private Integer status;
    
    private Integer resultado;
    
	@ApiModelProperty(value = "Lista de anexos")
	private List<ListaAnexoDto> listaAnexoDto;

	public List<ListaAnexoDto> getListaAnexoDto() {
		return listaAnexoDto;
	}

	public void setListaAnexoDto(List<ListaAnexoDto> listaAnexoDto) {
		this.listaAnexoDto = listaAnexoDto;
	}


    
    public Integer getResultado() {
		return resultado;
	}


	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}


	public RecursoLaiRequest() {
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
		RecursoLaiRequest other = (RecursoLaiRequest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
    
    
    
    
    

}
