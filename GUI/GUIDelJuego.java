package GUI;
import Logica.Juego;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import Logica.*;


public class GUIDelJuego extends JFrame {

	private Juego juego;
	private JFrame frame;
	private Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIDelJuego window = new GUIDelJuego();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIDelJuego() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel controles = new JPanel();
		JPanel grilla = new JPanel();
		
		JPanel reloj = new JPanel();
		juego = new Juego();
		
		frame.getContentPane().setLayout(new BorderLayout());
		grilla.setLayout(new GridLayout(juego.getCantFilas(),juego.getCantFilas()));
		controles.setLayout(new BorderLayout());
		reloj.setLayout(new FlowLayout());
		
		//creo la grilla y le agrego los listeners
		for (int i = 0; i <juego.getCantFilas(); i++) {
			for(int j =0; j<juego.getCantFilas(); j++) {
				Celda c = juego.getCelda(i,j);
			    ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel();
				grilla.add(label);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				label.addComponentListener(new ComponentAdapter() {
		    	    public void componentResized(ComponentEvent e) {
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					  }
				    });
				
				label.addMouseListener(new MouseAdapter() {
					
					public void mouseClicked(MouseEvent e) {
						juego.accionar(c);
						reDimensionar(label,grafico);
					}
				});
				
				
			}
		}
		
		JLabel label1 = new JLabel();
		reloj.add(label1);
		JLabel label2 = new JLabel();
		reloj.add(label2);
		JLabel label3 = new JLabel();
		reloj.add(label3);
	  	JLabel label4 = new JLabel();
		reloj.add(label4);
		JLabel label5 = new JLabel();
		reloj.add(label5);
		JLabel label6 = new JLabel();
		reloj.add(label6);
	  	JLabel label7 = new JLabel();
		reloj.add(label7);
		JLabel label8 = new JLabel();
		reloj.add(label8);  		
		
		JButton botonFinalizar = new JButton("Fin del Juego");
		botonFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean gano = false;
				boolean tableroLleno = juego.comprobarTableroLleno();
				if (!tableroLleno)
					JOptionPane.showMessageDialog(frame,"No has completado todas las casillas","Por favor completalo",JOptionPane.PLAIN_MESSAGE);
				else {
				
					gano = juego.comprobarSolucion();
				    if (gano)
					   JOptionPane.showMessageDialog(frame,"Ganaste","Felicitaciones!!", JOptionPane.PLAIN_MESSAGE);
				    else
					   JOptionPane.showMessageDialog(frame,"No has ganado","Intentalo nuevamente",JOptionPane.PLAIN_MESSAGE);
				    juego.marcarRepetidos();
				    juego.deshabilitarCeldas();
				    botonFinalizar.setEnabled(false);
					timer.cancel();
				    
				}
			}
		});
		
		timer =  new Timer();
		TimerTask tt = new TimerTask() {
		    public void run() {
		    	Duration d = juego.duracion();
		    	String hms = String.format("%02d:%02d:%02d",d.toHours(), d.toMinutesPart(), d.toSecondsPart());
		    	ImageIcon imagen1 = new ImageIcon("C:\\Users\\Gabriela\\Documents\\eclipse-workspace2020\\Proyecto2TDP\\src\\ImagenesDelReloj\\"+hms.charAt(0)+".png");
		        label1.setIcon(imagen1);
		        ImageIcon imagen2 = new ImageIcon("C:\\Users\\Gabriela\\Documents\\eclipse-workspace2020\\Proyecto2TDP\\src\\ImagenesDelReloj\\"+hms.charAt(1)+".png");
		        label2.setIcon(imagen2);
		        ImageIcon imagen3 = new ImageIcon("C:\\Users\\Gabriela\\Documents\\eclipse-workspace2020\\Proyecto2TDP\\src\\ImagenDelReloj\\dosPuntos.png");
		        label3.setIcon(imagen3);
		        ImageIcon imagen4 = new ImageIcon("C:\\Users\\Gabriela\\Documents\\eclipse-workspace2020\\Proyecto2TDP\\src\\ImagenesDelReloj\\"+hms.charAt(3)+".png");
		        label4.setIcon(imagen4);
		        ImageIcon imagen5 = new ImageIcon("C:\\Users\\Gabriela\\Documents\\eclipse-workspace2020\\Proyecto2TDP\\src\\ImagenesDelReloj\\"+hms.charAt(4)+".png");
		        label5.setIcon(imagen5);
		        ImageIcon imagen6 = new ImageIcon("C:\\Users\\Gabriela\\Documents\\eclipse-workspace2020\\Proyecto2TDP\\src\\ImagenDelReloj\\dosPuntos.png");
		        label6.setIcon(imagen6);
		        ImageIcon imagen7 = new ImageIcon("C:\\Users\\Gabriela\\Documents\\eclipse-workspace2020\\Proyecto2TDP\\src\\ImagenesDelReloj\\"+hms.charAt(6)+".png");
		        label7.setIcon(imagen7);
		        ImageIcon imagen8 = new ImageIcon("C:\\Users\\Gabriela\\Documents\\eclipse-workspace2020\\Proyecto2TDP\\src\\ImagenesDelReloj\\"+hms.charAt(7)+".png");
		        label8.setIcon(imagen8);
		    };
		};
		timer.schedule(tt, 0, 1000);
		
		controles.add(botonFinalizar,BorderLayout.WEST);
		controles.add(reloj,BorderLayout.CENTER);
		frame.getContentPane().add(controles,BorderLayout.NORTH);
		frame.getContentPane().add(grilla,BorderLayout.CENTER);
	}

	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
	
	
}
