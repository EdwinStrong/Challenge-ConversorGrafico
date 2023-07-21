package br.com.alura.conversorSwing;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.alura.excepciones.MonedaException;
import br.com.alura.monedas.Metodos;
import br.com.alura.monedas.MonedaNombreEnum;
import br.com.alura.monedas.MonedaValor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class SwingConversorMonedas {

	protected JFrame frame;
	private JTextField txtCantidadDinero;
	private JTextField txtResultadoConversion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingConversorMonedas window = new SwingConversorMonedas();
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
	public SwingConversorMonedas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Variables
		List<String> opcionesConversion = new ArrayList<>();
		HashMap<Integer, String> convertirDinero = new HashMap<>();
		MonedaValor monedaConvertir = new MonedaValor();
		String[] nombreMonedas = {"Dolar", "Euros", "Libra esterlina", "Yen japonés", "Won coreano"};

		//Llenar opciones
		int tamanioOpciones = nombreMonedas.length;
		for(int i=0; i<tamanioOpciones ; i++) {
			String variable = "De pesos a "+nombreMonedas[i];
			opcionesConversion.add(variable);
			convertirDinero.put(i, variable);
		}

		//Frame
		frame = new JFrame();
		frame.setBounds(100, 100, 796, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Conversor de monedas.");
		frame.getContentPane().setLayout(null);

		//Label
		JLabel lblNewLabel = new JLabel("Cantidad de moneda a convertir");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(89, 72, 196, 17);
		frame.getContentPane().add(lblNewLabel);


		//Combobox
		JComboBox<Object> cbxOpciones =  new JComboBox<Object>();
		cbxOpciones.setBounds(285, 42, 155, 22);
		frame.getContentPane().add(cbxOpciones);
		cbxOpciones.setModel(new DefaultComboBoxModel<>(opcionesConversion.toArray()));

		//Label
		JLabel lblSeleccioneElTipo = new JLabel("Tipo de conversión");
		lblSeleccioneElTipo.setVerticalAlignment(SwingConstants.TOP);
		lblSeleccioneElTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneElTipo.setBounds(103, 46, 172, 17);
		frame.getContentPane().add(lblSeleccioneElTipo);

		//Textbox
		txtResultadoConversion = new JTextField();
		txtResultadoConversion.setColumns(10);
		txtResultadoConversion.setBounds(482, 69, 155, 20);
		frame.getContentPane().add(txtResultadoConversion);

		//Botón
		JButton btnConvertirMoneda = new JButton("Convertir");
		btnConvertirMoneda.setBounds(351, 115, 89, 23);
		frame.getContentPane().add(btnConvertirMoneda);

		JComboBox<Object> cbxConversionInversa = new JComboBox<Object>();
		cbxConversionInversa.setBounds(482, 42, 155, 22);
		frame.getContentPane().add(cbxConversionInversa);

		JLabel lblMensajeError = new JLabel("No es un número.");
		lblMensajeError.setForeground(new Color(255, 0, 0));
		lblMensajeError.setBounds(285, 96, 155, 14);
		frame.getContentPane().add(lblMensajeError);

		//Textbox que almacena la cantidad de la moneda a convertir
		txtCantidadDinero = new JTextField();
		txtCantidadDinero.addKeyListener(new KeyAdapter() {
			@Override
			//Evento que se ejecuta cuando se deja de presionar (Empezas a escribir y luego dejas de escribir)
			public void keyReleased(KeyEvent e) {
				//JOptionPane.showMessageDialog(null, "KEY PRESSED");
				String textBoxDinero = txtCantidadDinero.getText();
				if(textBoxDinero.isEmpty()) {
					
				}
				else if(Metodos.esNumero(textBoxDinero)) {
					//lblMensajeError.setText("SI ES UN NUMERO");
					lblMensajeError.setVisible(false);//Ocultamos el texto
					conversionPesos_a_Divisas(txtCantidadDinero, txtResultadoConversion, convertirDinero, cbxOpciones);
				}
				else {
					lblMensajeError.setVisible(true);
				}
			}
		});
		txtCantidadDinero.setBounds(285, 69, 155, 20);
		frame.getContentPane().add(txtCantidadDinero);
		txtCantidadDinero.setColumns(10);
	}
	
	/**
	 * Este método se encarga de realizar la conversión de monedas de un textbox a otro textbox.
	 * @param textBoxOrigen Es el textbox que almacena la cantidad de la moneda a convertir.
	 * @param textBoxDestino Es el textbox al cual se le almacena el resultado de la conversión.
	 * @param metodo Es un hashmap que almacena las opciones posibles de los métodos para realizar la conversión.
	 * @param opciones Es un combobox que almacena las opciones posibles de conversión.
	 */
	public void conversionPesos_a_Divisas(JTextField textBoxOrigen, JTextField textBoxDestino, HashMap<Integer, String> metodo, JComboBox<Object> opciones) {
		MonedaValor monedaConvertir = new MonedaValor();
		for(Integer opc: metodo.keySet()) {
			if(opciones.getSelectedItem().toString() == metodo.get(opc)) {
				//JOptionPane.showMessageDialog(null,"Lo que has seleccionado es: "+convertirDinero.get(opc)+" con el indice: "+opc);
				try {
					String resultadoConversion = " "+monedaConvertir .convertirMoneda(opc, Float.parseFloat(txtCantidadDinero.getText().toString()));
					txtResultadoConversion.setText(resultadoConversion);
				} catch (NumberFormatException | MonedaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "UPS "+e1.getMessage());
				}
			}
		}
	}
	
	/**
	 * Este método se encarga de realizar la conversión de monedas de un textbox a otro textbox.
	 * @param textBoxOrigen Es el textbox que almacena la cantidad de la moneda a convertir.
	 * @param textBoxDestino Es el textbox al cual se le almacena el resultado de la conversión.
	 * @param metodo Es un hashmap que almacena las opciones posibles de los métodos para realizar la conversión.
	 * @param opciones Es un combobox que almacena las opciones posibles de conversión.
	 */
	public void conversionDivisas_a_Pesos(JTextField textBoxOrigen, JTextField textBoxDestino, HashMap<Integer, String> metodo, JComboBox<Object> opciones) {
		MonedaValor monedaConvertir = new MonedaValor();
		MonedaNombreEnum[] nombreMoneda = MonedaNombreEnum.values();
		for(Integer opc: metodo.keySet()) {
			if(opciones.getSelectedItem().toString() == metodo.get(opc)) {
				//JOptionPane.showMessageDialog(null,"Lo que has seleccionado es: "+convertirDinero.get(opc)+" con el indice: "+opc);
				try {
					String resultadoConversion = " "+monedaConvertir .convertirMoneda(opc, Float.parseFloat(txtCantidadDinero.getText().toString()));
					txtResultadoConversion.setText(resultadoConversion);
				} catch (NumberFormatException | MonedaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "UPS "+e1.getMessage());
				}
			}
		}
	}
}
