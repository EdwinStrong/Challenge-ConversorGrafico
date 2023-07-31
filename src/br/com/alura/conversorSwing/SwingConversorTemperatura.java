package br.com.alura.conversorSwing;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.alura.monedas.Metodos;
import br.com.alura.temperatura.TemperaturaValor;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class SwingConversorTemperatura extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTemperatura1;
	private JTextField txtTemperatura2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingConversorTemperatura frame = new SwingConversorTemperatura();
					frame.setVisible(true);
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

		String[] opcionesConversion = {"Celsius", "Farenheit", "Kelvin"};

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> cbxOpciones1 = new JComboBox<String>();
		cbxOpciones1.setBounds(117, 70, 126, 22);
		cbxOpciones1.setModel(new DefaultComboBoxModel<>(opcionesConversion));
		contentPane.add(cbxOpciones1);

		txtTemperatura2 = new JTextField();
		txtTemperatura2.setBounds(282, 93, 126, 20);
		contentPane.add(txtTemperatura2);
		txtTemperatura2.setColumns(10);

		JComboBox<String> cbxOpciones2 = new JComboBox<String>();
		cbxOpciones2.setBounds(282, 70, 125, 22);
		cbxOpciones2.setModel(new DefaultComboBoxModel<>(opcionesConversion));
		contentPane.add(cbxOpciones2);

		JLabel lblNewLabel = new JLabel("Temperatura");
		lblNewLabel.setBounds(8, 96, 89, 14);
		contentPane.add(lblNewLabel);

		JButton btnRegresar = new JButton("New button");
		btnRegresar.setBounds(8, 11, 89, 23);
		contentPane.add(btnRegresar);
		
		txtTemperatura1 = new JTextField();
		txtTemperatura1.setBounds(117, 93, 126, 20);
		contentPane.add(txtTemperatura1);
		txtTemperatura1.setColumns(10);
		
		JLabel lblError1 = new JLabel("No es un número.");
		lblError1.setBounds(139, 115, 104, 14);
		lblError1.setVisible(false);
		contentPane.add(lblError1);
		
		JLabel lblError2 = new JLabel("No es un número.");
		lblError2.setBounds(304, 115, 104, 14);
		lblError2.setVisible(false);
		contentPane.add(lblError2);
		
		JLabel lblNoEsUn_2 = new JLabel(" =");
		lblNoEsUn_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNoEsUn_2.setBounds(251, 95, 21, 14);
		contentPane.add(lblNoEsUn_2);
		
		txtTemperatura1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(Metodos.esNumero(txtTemperatura1.getText())) {
					realizarConversion(cbxOpciones1, cbxOpciones2, txtTemperatura1, txtTemperatura2);
					lblError1.setVisible(false);
				}else {
					lblError1.setVisible(true);
				}
			}
		});
		
		txtTemperatura2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(Metodos.esNumero(txtTemperatura2.getText())) {
					realizarConversion(cbxOpciones2, cbxOpciones1, txtTemperatura2, txtTemperatura1);
					lblError2.setVisible(false);
				}else {
					lblError2.setVisible(true);
				}
			}
		});
	}
	
	/**
	 * Este método se encarga de realizar la respectiva converisón entre temperaturas.
	 * @param cbxConversionOrigen Es el combobox que contiene la opción de temperatura inicial a convertir.
	 * @param cbxConversionDestino Es el combobox que contiene la opción de la temperatura final.
	 * @param txtTempOrigen Es la cantidad de temperatura a convertir.
	 * @param txtTempDestino Es la cantidad convertida de la temperatura.
	 */
	public static void realizarConversion(JComboBox<String> cbxConversionOrigen, JComboBox<String> cbxConversionDestino, JTextField txtTempOrigen, JTextField txtTempDestino) {
		TemperaturaValor temperatura = new TemperaturaValor();
		String opc= String.format("%s a %s", cbxConversionOrigen.getSelectedItem().toString(), cbxConversionDestino.getSelectedItem().toString());	
		String respuesta = String.format("%f", temperatura.conversion(opc.toLowerCase(), Float.parseFloat(txtTempOrigen.getText())));;
		txtTempDestino.setText(respuesta);	
	}

	public boolean validarTextbox(JTextField textbox) {
		String textoValidar = textbox.getText();
		if(Metodos.esNumero(textoValidar)) {
			return true;
		}
		else {
			return false; 
		}
	}
}
