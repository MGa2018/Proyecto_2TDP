package Logica;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

import GUI.GUIDelJuego;


public class Juego {
	private Celda [][] tablero;
	private int cantFilas;
	private LocalTime start;
	private Path file;

	public Juego() {
		file = FileSystems.getDefault().getPath("C:\\Users\\Gabriela\\Documents\\eclipse-workspace2020\\Proyecto2TDP\\src", "archivo.txt");
		this.cantFilas = 9;
		//crea una matriz
		tablero = new Celda[this.cantFilas][this.cantFilas];
		//crea un JLabel en cada celda y le setea un valor.
		for (int i =0; i<cantFilas; i++) {
			for (int j =0; j<cantFilas; j++) {
				Random rand = new Random();
				int value = rand.nextInt(8);
				tablero[i][j] = new Celda();
				//De acuerdo a value decidir si asignar un valor inicial o no
				if (value != 0)
					tablero[i][j].setValorInicial(true);
			}
		}
		//iniciar el reloj
		start = LocalTime.now( );
		llenarTablero();
	}
	
	public boolean comprobarTableroLleno() {
		boolean lleno = true;
		for (int i = 0;i<this.getCantFilas();i++)
			for (int j = 0;j<this.getCantFilas();j++)
				if (tablero[i][j].getValorInicial() == false)
				  if (tablero[i][j].getValor() == 11)
					lleno = false;
		return lleno;
	}
	
	public void marcarRepetidos() {
		for (int i = 0;i<this.getCantFilas();i++)
			for (int j = 0;j<this.getCantFilas();j++)
				if (tablero[i][j].getValor() == 10) {
		            tablero[i][j].getEntidadGrafica().actualizar(10);
				}
	}
	
	public void deshabilitarCeldas() {
		for (int i = 0;i<this.getCantFilas();i++)
			for (int j = 0;j<this.getCantFilas();j++)
				if (tablero[i][j].getValorInicial() == false)
				       tablero[i][j].setValorInicial(true);
	}
	
	
	public boolean comprobarSolucion() {				
		boolean rta = true;
			for(int i=0;i<this.getCantFilas();i++) {
				for (int j = 0;j<this.getCantFilas();j++) {
					if (estaEnLaCol(tablero[i][j].getValor(),j)) { 
				          rta = false;
				          tablero[i][j].setValor(10);
					}
					else if (estaEnLaFila(tablero[i][j].getValor(),i)) { 
				          rta = false;
				          tablero[i][j].setValor(10);
					}
						  
					else if (estaEnElPanel(tablero[i][j].getValor(),i,j)) { 
				          rta = false;
				          tablero[i][j].setValor(10);
					}
						
					
				}
			}
			
			return rta;
		}
		
		private boolean estaEnLaFila(int k, int i) {
			int cant = 0;
			boolean rta = false;
			for (int j = 0;j<this.getCantFilas();j++)
				if (tablero[i][j].getValor() == k)
					cant++;
			if (cant>1)
			     rta = true;
			return rta;
		}
		
		private boolean estaEnLaCol(int k, int j) {
			int cant = 0;
			boolean rta = false;
			for (int i = 0;i<this.getCantFilas();i++) {
			    if (tablero[i][j].getValor() == k)
					cant++;
			}
			if (cant>1)
			     rta = true;
			return rta;
		}
		
		private boolean estaEnElPanel(int k,int i, int j) {
			boolean esta = false;
			int cant = 0;
			int filacuadrante = ((int) (i/ 3)) *3;
			int columnacuadrante = ((int) (j / 3)) * 3;
			int finfila = filacuadrante+3;
			int fincol = columnacuadrante+3;
			
			for (int m = filacuadrante;m<finfila;m++)
				for (int n = columnacuadrante;n<fincol;n++) {
					if (tablero[m][n].getValor() == k)
						cant++;
				}
					if (cant>1)
				esta = true;
		return esta;
		}

	public Duration duracion() {
		  
          LocalTime stop = LocalTime.now( );
          Duration d = Duration.between( start , stop );
          return d;
	}
	
	public void llenarTablero() {
		InputStream in = GUIDelJuego.class.getClassLoader().getResourceAsStream("archivo.txt");
		InputStreamReader inr =new InputStreamReader(in);
		try (BufferedReader reader = new BufferedReader(inr,162)) {
		    String line = null;
		    int m = 0;
		    int j = 0;
		    while ((line = reader.readLine()) != null) {
		       	j = 0;
		    	for(int i = 0;i<line.length();i++) {
		    		if (line.charAt(i) != ' ') {
		        		if (tablero[m][j].getValorInicial()==true) {
        	        		tablero[m][j].setValor(Character.getNumericValue(line.charAt(i)));
		        		}else
		        			tablero[m][j].setValor(11);
		        		j++;

		        	}
		        		
		    	}
		        m++;
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	public void accionar(Celda c) {
		c.actualizar();
	}
	
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}
	
	public int getCantFilas() {
		return this.cantFilas;
	}
}

