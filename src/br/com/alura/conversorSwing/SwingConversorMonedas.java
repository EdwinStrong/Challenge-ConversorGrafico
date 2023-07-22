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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;

public class SwingConversorMonedas {

	protected JFrame frame;
	private JTextField txtCantidadPesosDivisa;
	private JTextField txtCantidadDivisaPesos;

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
		List<String> opcionesConversionInversa = new ArrayList<>();

		HashMap<Integer, String> convertirDinero = new HashMap<>();
		//String[] nombreMonedas = {"Dolar", "Euros", "Libra esterlina", "Yen japonés", "Won coreano"};
		MonedaNombreEnum[] nombreMonedas = MonedaNombreEnum.values();
		
		boolean conversion_a_Peso = true;
		//Llenar opciones
		int tamanioOpciones = nombreMonedas.length;
		for(int i=0; i<tamanioOpciones ; i++) {
			String variable = "De pesos a "+nombreMonedas[i].toString().toLowerCase().replace("_", " ");
			opcionesConversion.add(variable);
			convertirDinero.put(i, variable);

			variable="De "+nombreMonedas[i].toString().toLowerCase().replace("_", " ")+" a pesos";
			opcionesConversionInversa.add(variable);
			convertirDinero.put(i+tamanioOpciones, variable);//Se mantiene una simetría de tamanioOpciones (Que significaría lo inverso)
		}

		//Frame
		frame = new JFrame();
		frame.setBounds(100, 100, 796, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Conversor de monedas.");
		frame.getContentPane().setLayout(null);

		//Label
		JLabel lblNewLabel = new JLabel("Pesos");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(126, 72, 106, 17);
		frame.getContentPane().add(lblNewLabel);


		//Combobox
		JComboBox<Object> cbxOpciones =  new JComboBox<Object>();
		cbxOpciones.setBounds(224, 42, 155, 22);
		frame.getContentPane().add(cbxOpciones);
		cbxOpciones.setModel(new DefaultComboBoxModel<>(opcionesConversion.toArray()));

		//Label
		JLabel lblSeleccioneElTipo = new JLabel("Tipo de conversión");
		lblSeleccioneElTipo.setVerticalAlignment(SwingConstants.TOP);
		lblSeleccioneElTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneElTipo.setBounds(102, 46, 117, 17);
		frame.getContentPane().add(lblSeleccioneElTipo);
		
		JLabel lblMensajeError_1 = new JLabel("No es un número.");
		lblMensajeError_1.setForeground(Color.RED);
		lblMensajeError_1.setBounds(492, 96, 155, 14);
		lblMensajeError_1.setVisible(false);
		frame.getContentPane().add(lblMensajeError_1);
		
		JComboBox<Object> cbxConversionInversa = new JComboBox<Object>();
		cbxConversionInversa.setBounds(482, 42, 155, 22);
		frame.getContentPane().add(cbxConversionInversa);
		cbxConversionInversa.setModel(new DefaultComboBoxModel<>(opcionesConversionInversa.toArray()));

		JLabel lblMensajeError = new JLabel("No es un número.");
		lblMensajeError.setForeground(new Color(255, 0, 0));
		lblMensajeError.setBounds(248, 96, 106, 14);
		lblMensajeError.setVisible(false);;
		frame.getContentPane().add(lblMensajeError);

		//Textbox que almacena la cantidad de pesos a convertir a divisa.
		txtCantidadPesosDivisa = new JTextField();
		txtCantidadPesosDivisa.addKeyListener(new KeyAdapter() {
			@Override
			//Evento que se ejecuta cuando se deja de presionar (Empezas a escribir y luego dejas de escribir)
			public void keyReleased(KeyEvent e) {
				//JOptionPane.showMessageDialog(null, "KEY PRESSED");
				String textBoxDinero = txtCantidadPesosDivisa.getText();
				if(textBoxDinero.isEmpty()) {

				}
				else if(Metodos.esNumero(textBoxDinero)) {
					//lblMensajeError.setText("SI ES UN NUMERO");
					lblMensajeError.setVisible(false);//Ocultamos el texto
					conversionDivisas(conversion_a_Peso,txtCantidadPesosDivisa, txtCantidadDivisaPesos, convertirDinero, cbxOpciones);
				}
				else {
					lblMensajeError.setVisible(true);
				}
			}
		});
		txtCantidadPesosDivisa.setBounds(224, 69, 155, 20);
		frame.getContentPane().add(txtCantidadPesosDivisa);
		txtCantidadPesosDivisa.setColumns(10);

		//Textbox que almacena la cantidad de divisa a convertir a pesos.
		txtCantidadDivisaPesos = new JTextField();
		txtCantidadDivisaPesos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//JOptionPane.showMessageDialog(null, "KEY PRESSED");
				String textBoxDinero = txtCantidadDivisaPesos.getText();
				if(textBoxDinero.isEmpty()) {

				}
				else if(Metodos.esNumero(textBoxDinero)) {
					//lblMensajeError.setText("SI ES UN NUMERO");
					lblMensajeError_1.setVisible(false);//Ocultamos el texto
					conversionDivisas(!conversion_a_Peso, txtCantidadDivisaPesos,txtCantidadPesosDivisa, convertirDinero, cbxConversionInversa);
				}
				else {
					lblMensajeError_1.setVisible(true);
				}
			}
		});
		txtCantidadDivisaPesos.setColumns(10);
		txtCantidadDivisaPesos.setBounds(482, 69, 155, 20);
		frame.getContentPane().add(txtCantidadDivisaPesos);
		
		JLabel lblNewLabel_1 = new JLabel("<-- -->");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(388, 72, 84, 38);
		frame.getContentPane().add(lblNewLabel_1);
	}

	/**
	 * Este método se encarga de realizar la conversión de monedas de un textbox a otro textbox.
	 * @param textBoxOrigen Es el textbox que almacena la cantidad de la moneda a convertir.
	 * @param textBoxDestino Es el textbox al cual se le almacena el resultado de la conversión.
	 * @param metodo Es un hashmap que almacena las opciones posibles de los métodos para realizar la conversión.
	 * @param opciones Es un combobox que almacena las opciones posibles de conversión.
	 */
	public void conversionDivisas(Boolean convertir_a_Peso, JTextField textBoxOrigen, JTextField textBoxDestino, HashMap<Integer, String> metodo, JComboBox<Object> opciones) {
		MonedaValor monedaConvertir = new MonedaValor();
		for(Integer opc: metodo.keySet()) {
			if(opciones.getSelectedItem().toString() == metodo.get(opc)) {
				//JOptionPane.showMessageDialog(null,"Lo que has seleccionado es: "+metodo.get(opc)+" con el indice: "+opc);
				try {
					String resultadoConversion = " "+monedaConvertir .convertirMoneda(convertir_a_Peso,opc, Float.parseFloat(textBoxOrigen.getText().toString()));//Origen da valor a convertir
					textBoxDestino.setText(resultadoConversion);//Destino es el que almacena el resultado de la conversión.
				} catch (NumberFormatException | MonedaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "UPS "+e1.getMessage());
				}
			}
		}
	}
}
