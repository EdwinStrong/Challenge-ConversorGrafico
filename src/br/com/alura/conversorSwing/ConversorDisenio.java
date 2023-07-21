package br.com.alura.conversorSwing;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConversorDisenio {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConversorDisenio window = new ConversorDisenio();
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
	public ConversorDisenio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 418, 315);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Menu principal");
		
		JLabel lblNewLabel = new JLabel("Seleccione ");
		lblNewLabel.setBounds(29, 36, 94, 14);
		frame.getContentPane().add(lblNewLabel);
		
		String[] opciones = {"Conversor de monedas", "Conversor de temperatura"};
		JComboBox<String> cbxTipoConversion = new JComboBox<String>();
		cbxTipoConversion.setModel(new DefaultComboBoxModel<>(opciones));
		cbxTipoConversion.setBounds(146, 32, 160, 22);
		
		frame.getContentPane().add(cbxTipoConversion);
		
		//Abrir form
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxTipoConversion.getSelectedItem().toString() == opciones[0]) {//Formulario de conversor de moneda.
					SwingConversorMonedas conversor = new SwingConversorMonedas();
					conversor.frame.setVisible(true);//Abrir el form
					ConversorDisenio thisForm = new ConversorDisenio();
				}
				else {
					SwingConversorTemperatura conversor = new SwingConversorTemperatura();
					conversor.frame.setVisible(true);//Abrir el form
					ConversorDisenio thisForm = new ConversorDisenio();
				}
			}
		});
			
		btnNewButton.setBounds(153, 72, 116, 31);
		frame.getContentPane().add(btnNewButton);
	}
}
