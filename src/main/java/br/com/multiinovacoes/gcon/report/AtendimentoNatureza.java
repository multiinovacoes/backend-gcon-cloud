package br.com.multiinovacoes.gcon.report;

public class AtendimentoNatureza {
	
	private String label;
	
	private Double value;
	
	private Long area;
	
	public AtendimentoNatureza(String label, Double value, Long area) {
		this.label = label;
		this.value = value;
		this.area = area;
	}

	public AtendimentoNatureza(String label, Double value) {
		this.label = label;
		this.value = value;
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

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	
	

}
