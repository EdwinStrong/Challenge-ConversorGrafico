package br.com.alura.conversorSwing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class ConversorDisenio {

	protected JFrame frame;
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
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frame.getContentPane().setBackground(new Color(153, 204, 204));
		frame.setBounds(100, 100, 418, 216);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Menu principal");
		
		JLabel lblNewLabel = new JLabel("Seleccione ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setBounds(73, 36, 63, 14);
		frame.getContentPane().add(lblNewLabel);
		
		String[] opciones = {"Conversor de monedas", "Conversor de temperatura"};
		JComboBox<String> cbxTipoConversion = new JComboBox<String>();
		cbxTipoConversion.setFont(new Font("Arial", Font.PLAIN, 11));
		cbxTipoConversion.setBackground(new Color(255, 255, 204));
		cbxTipoConversion.setModel(new DefaultComboBoxModel<>(opciones));
		cbxTipoConversion.setBounds(146, 32, 160, 22);
		
		frame.getContentPane().add(cbxTipoConversion);
		
		//Abrir form
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 11));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(102, 102, 153));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxTipoConversion.getSelectedItem().toString() == opciones[0]) {//Formulario de conversor de moneda.
					SwingConversorMonedas conversor = new SwingConversorMonedas();
					conversor.frmConversorDeMonedas.setVisible(true);//Abrir el form
				}
				else {
					SwingConversorTemperatura conversor = new SwingConversorTemperatura();
					conversor.setVisible(true);//Abrir el form
				}
			}
		});
			
		btnNewButton.setBounds(153, 72, 116, 31);
		frame.getContentPane().add(btnNewButton);
	}
}
