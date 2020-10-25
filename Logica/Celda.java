package Logica;


public class Celda {
	private Integer valor;
	private EntidadGrafica entidadGrafica;
	private boolean valorInicial;
	
	
	public Celda() {
		this.valor = null;
		this.entidadGrafica = new EntidadGrafica();
		valorInicial = false; //esto es para determinar si la celda va a tener una imagen o no al iniciar el juego
	}
	
	//actualiza el valor de la imagen de la celda.
	public void actualizar() {
		if (valorInicial == false) {//si la celda es para completar
		   if (this.valor != null && this.valor + 1 < this.getCantElementos()) {
			this.valor++;
		   }else {
			this.valor = 0;
		   }
		   entidadGrafica.actualizar(this.valor);
		}
		
		
	}
	
	public int getCantElementos() {
		return this.entidadGrafica.getImagenes().length;
	}
	
	public Integer getValor() {
		return this.valor;
	}
	
	public void setValor(Integer valor) {
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizar(this.valor);
		}else if (valor == 10){//imagen repetida
			this.valor = 10;
			this.entidadGrafica.actualizar(this.valor);
		}else if (valor == 11) {//imagen lisa para que el usuario complete
			this.valor = 11;
			this.entidadGrafica.actualizar(this.valor);
		}
	}
	
	public EntidadGrafica getEntidadGrafica() {
		return this.entidadGrafica;
	}
	
	public void setEntidadGrafica(EntidadGrafica g) {
		this.entidadGrafica = g;
	}
	
	public void setValorInicial(boolean inicial) {
		valorInicial = inicial;
	}
	
	public boolean getValorInicial() {
		return valorInicial;
	}
}

