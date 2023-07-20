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

public class ConversorIDisenio {

	private JFrame frame;
	private JTextArea txtNombre;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConversorIDisenio window = new ConversorIDisenio();
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
	public ConversorIDisenio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 345, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Menu principal");
		
		JLabel lblNewLabel = new JLabel("Seleccione ");
		lblNewLabel.setBounds(10, 36, 59, 14);
		frame.getContentPane().add(lblNewLabel);
		
		String[] opciones = {"Conversor de monedas", "Conversor de temperatura"};
		JComboBox cbxTipoConversion = new JComboBox();
		cbxTipoConversion.setModel(new DefaultComboBoxModel<>(opciones));
		cbxTipoConversion.setBounds(82, 32, 160, 22);
		
		frame.getContentPane().add(cbxTipoConversion);
		
		//Abrir form
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxTipoConversion.getSelectedItem().toString() == opciones[0]) {//Formulario de conversor de moneda.
					SwingConversorTemperatura conversor = new SwingConversorTemperatura();
					conversor.frame.setVisible(true);//Abrir el form
					
					ConversorIDisenio thisForm = new ConversorIDisenio();
					thisForm.frame.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "Ese form no ha sido creado.");
				}
			}
		});
			
		btnNewButton.setBounds(153, 72, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
