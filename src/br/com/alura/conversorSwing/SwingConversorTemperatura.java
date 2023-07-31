package br.com.alura.conversorSwing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.alura.excepciones.TemperaturaException;
import br.com.alura.metodosGenerales.Metodos;
import br.com.alura.temperatura.TemperaturaValor;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SwingConversorTemperatura extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTemperatura1;
	private JTextField txtTemperatura2;
	protected static SwingConversorTemperatura frameConversorTemperatura = new SwingConversorTemperatura();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameConversorTemperatura.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SwingConversorTemperatura() {
		setTitle("Conversor de temperatura");

		//Variable que almacena los tipos de temperatura que maneja el sistema (Se debe actualizar según cada tipo de temperatura nuevo agregado en la clase).
		String[] opcionesConversion = {"Celsius", "Farenheit", "Kelvin", "Rankine"};

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> cbxOpciones1 = new JComboBox<String>();
		cbxOpciones1.setBackground(new Color(248, 248, 255));
		cbxOpciones1.setForeground(new Color(0, 0, 0));
		cbxOpciones1.setFont(new Font("Arial", Font.PLAIN, 11));
		cbxOpciones1.setBounds(120, 75, 126, 22);
		cbxOpciones1.setModel(new DefaultComboBoxModel<>(opcionesConversion));
		contentPane.add(cbxOpciones1);

		txtTemperatura2 = new JTextField();
		txtTemperatura2.setFont(new Font("Arial", Font.PLAIN, 11));
		txtTemperatura2.setBounds(283, 100, 126, 20);
		contentPane.add(txtTemperatura2);
		txtTemperatura2.setColumns(10);

		JComboBox<String> cbxOpciones2 = new JComboBox<String>();
		cbxOpciones2.setForeground(new Color(0, 0, 0));
		cbxOpciones2.setBackground(new Color(240, 255, 240));
		cbxOpciones2.setFont(new Font("Arial", Font.PLAIN, 11));
		cbxOpciones2.setBounds(283, 75, 126, 22);
		cbxOpciones2.setModel(new DefaultComboBoxModel<>(opcionesConversion));
		contentPane.add(cbxOpciones2);

		JLabel lblNewLabel = new JLabel("Temperatura");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setBounds(28, 102, 89, 14);
		contentPane.add(lblNewLabel);

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(new Color(0, 139, 139));
		btnRegresar.setForeground(new Color(255, 255, 255));
		btnRegresar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameConversorTemperatura.setVisible(false);
				ConversorDisenio.window.frame.setVisible(true);
			}
		});
		btnRegresar.setBounds(11, 16, 89, 23);
		contentPane.add(btnRegresar);

		txtTemperatura1 = new JTextField();
		txtTemperatura1.setFont(new Font("Arial", Font.PLAIN, 11));
		txtTemperatura1.setBounds(120, 100, 126, 20);
		contentPane.add(txtTemperatura1);
		txtTemperatura1.setColumns(10);

		JLabel lblError1 = new JLabel("No es un número.");
		lblError1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblError1.setBounds(142, 123, 85, 14);
		lblError1.setVisible(false);
		contentPane.add(lblError1);

		JLabel lblError2 = new JLabel("No es un número.");
		lblError2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblError2.setBounds(304, 123, 85, 14);
		lblError2.setVisible(false);
		contentPane.add(lblError2);

		JLabel lblNoEsUn_2 = new JLabel(" =");
		lblNoEsUn_2.setBounds(256, 99, 21, 18);
		lblNoEsUn_2.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(lblNoEsUn_2);

		/*Conversiones*/

		//Evento para convertir la temperatura.
		txtTemperatura1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(Metodos.esNumero(txtTemperatura1.getText())) {
					realizarConversion(cbxOpciones1, cbxOpciones2, txtTemperatura1, txtTemperatura2);
					Metodos.ocultarLabel(lblError1, lblError2);
				}else {
					Metodos.mostrarLabel(lblError1);
				}
			}
		});

		//Evento para convertir la temperatura.
		txtTemperatura2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(Metodos.esNumero(txtTemperatura2.getText())) {
					realizarConversion(cbxOpciones2, cbxOpciones1, txtTemperatura2, txtTemperatura1);
					Metodos.ocultarLabel(lblError1, lblError2);
				}else {
					Metodos.mostrarLabel(lblError2);
				}
			}
		});
		
		//Acción que harán los combobox para manejar la conversión de temperaturas.
		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Metodos.esNumero(txtTemperatura1.getText())) {
					realizarConversion(cbxOpciones1, cbxOpciones2, txtTemperatura1, txtTemperatura2);
					Metodos.ocultarLabel(lblError1, lblError2);
				}else {
					Metodos.mostrarLabel(lblError2);
				}
			}
		};
		
		//Accción de los combobox para que, cuando se escoja una opción, realice una conversión.
		cbxOpciones1.addActionListener(action);
		cbxOpciones2.addActionListener(action);
	}

	/**
	 * Este método se encarga de realizar la respectiva converisón entre temperaturas.
	 * @param cbxConversionOrigen Es el combobox que contiene la opción de temperatura inicial a convertir.
	 * @param cbxConversionDestino Es el combobox que contiene la opción de la temperatura final.
	 * @param txtTempOrigen Es la cantidad de temperatura a convertir.
	 * @param txtTempDestino Es la cantidad convertida de la temperatura.
	 */
	public static void realizarConversion(JComboBox<String> cbxConversionOrigen, JComboBox<String> cbxConversionDestino, JTextField txtTempOrigen, JTextField txtTempDestino) {
		if(cbxConversionOrigen.getSelectedItem() == cbxConversionDestino.getSelectedItem()) {
			txtTempDestino.setText(txtTempOrigen.getText());	
		}else {
			TemperaturaValor temperatura = new TemperaturaValor();
			String opc= String.format("%s a %s", cbxConversionOrigen.getSelectedItem().toString(), cbxConversionDestino.getSelectedItem().toString());	
			String respuesta = "Error en la conversión.";
			try {
				respuesta = String.format("%f", temperatura.conversion(opc.toLowerCase(), Float.parseFloat(txtTempOrigen.getText())));
			} catch (NumberFormatException | TemperaturaException e) {
				// TODO Auto-generated catch block
				respuesta=String.format("%f", 0);
			}finally {
				txtTempDestino.setText(respuesta);	
			}
		}
	}
}
