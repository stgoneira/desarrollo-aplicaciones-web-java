package modelo;

public class Rectangulo {

	private int base;
	private int altura;
		
	// Constructores 
	public Rectangulo() {
		super();
	}

	public Rectangulo(int base, int altura) {
		super();
		this.base = base;
		this.altura = altura;
	}

	// cálculos 
	public int calcularArea() {
		return base * altura;
	}
	
	public int calcularPerimetro() {
		return (base * 2) + (altura * 2);
	}
	
	// getters y setters 
	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}	
}
