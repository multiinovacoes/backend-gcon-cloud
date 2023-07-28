package br.com.multiinovacoes.gcon.report;

public class DadosGrafico {
	
	private String label;
	
	private Double value;
	
	private String percentual;

	public DadosGrafico() {
	}
	
	public DadosGrafico(String label, Double value, String percentual) {
		super();
		this.label = label;
		this.value = value;
		this.percentual = percentual;
	}

	public DadosGrafico(String label, String percentual) {
		super();
		this.label = label;
		this.percentual = percentual;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getPercentual() {
		return percentual;
	}

	public void setPercentual(String percentual) {
		this.percentual = percentual;
	}

	
	
	

}
