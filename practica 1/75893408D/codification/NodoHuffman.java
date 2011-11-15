package codification;

public class NodoHuffman {

	private Character symbol;
	private Float probability;
	private String codification;

	public Character getSymbol() {
		return symbol;
	}

	public void setSymbol(Character symbol) {
		this.symbol = symbol;
	}

	public Float getProbability() {
		return probability;
	}

	public void setProbability(Float probability) {
		this.probability = probability;
	}

	public String getCodification() {
		return codification;
	}

	public void setCodification(String codification) {
		this.codification = codification;
	}

	public NodoHuffman(Character symbol, Float probability, String codification) {
		super();
		this.symbol = symbol;
		this.probability = probability;
		this.codification = codification;
	}

}
