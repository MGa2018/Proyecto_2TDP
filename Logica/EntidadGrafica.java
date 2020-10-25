package Logica;

import javax.swing.ImageIcon;

public class EntidadGrafica{
	private ImageIcon grafico;
	private String[] imagenes;
	private String imagenRepetida;
	private String inicial;
	
	public EntidadGrafica() {
		this.grafico = new ImageIcon();
		this.imagenes = new String[]{"/ImagenesDelJuego/hipo.png", "/ImagenesDelJuego/dino.png", "/ImagenesDelJuego/mario_bros.png",
				"/ImagenesDelJuego/hongo_rosa.png","/ImagenesDelJuego/hongo_rojo.png","/ImagenesDelJuego/mario_bros_verde.png",
				"/ImagenesDelJuego/princesa2.png","/ImagenesDelJuego/tiburon.png","/ImagenesDelJuego/princesa.png"};
	    this.imagenRepetida = "C:\\Users\\Gabriela\\Documents\\eclipse-workspace2020\\Proyecto2TDP\\src\\ImagenRepetida\\repetida.png";
	    this.inicial = "C:\\Users\\Gabriela\\Documents\\eclipse-workspace2020\\Proyecto2TDP\\src\\ImagenRepetida\\libre.png";
	    
	}
	
	//actualiza la imagen de la celda con el valor que recibe
	public void actualizar(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}else if (indice == 10) {//si es imagen repetida
			ImageIcon imageIcon = new ImageIcon(imagenRepetida);
			this.grafico.setImage(imageIcon.getImage());
		}else if (indice == 11) {//imagen para completar
			ImageIcon imageIcon = new ImageIcon(inicial);
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	
	public String[] getImagenes() {
		return this.imagenes;
	}
	
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
	
	
}



