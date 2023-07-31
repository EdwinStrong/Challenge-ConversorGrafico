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
import br.com.alura.metodosGenerales.Metodos;
import br.com.alura.monedas.MonedaNombreEnum;
import br.com.alura.monedas.MonedaValor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingConversorMonedas {

	protected JFrame frmConversorDeMonedas;
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
					window.frmConversorDeMonedas.setVisible(true);
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
		frmConversorDeMonedas = new JFrame();
		frmConversorDeMonedas.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frmConversorDeMonedas.getContentPane().setBackground(new Color(255, 245, 238));
		frmConversorDeMonedas.setBounds(100, 100, 740, 345);
		frmConversorDeMonedas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConversorDeMonedas.setTitle("Conversor de monedas");

		//Label
		JLabel lblNewLabel = new JLabel("Pesos");
		lblNewLabel.setBounds(33, 136, 111, 13);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);


		//Combobox
		JComboBox<Object> cbxOpciones =  new JComboBox<Object>();
		
		cbxOpciones.setBounds(154, 112, 178, 21);
		cbxOpciones.setFont(new Font("Arial", Font.PLAIN, 11));
		cbxOpciones.setForeground(new Color(0, 0, 102));
		cbxOpciones.setBackground(new Color(255, 255, 255));
		cbxOpciones.setModel(new DefaultComboBoxModel<>(opcionesConversion.toArray()));
		
		//Label
		JLabel lblSeleccioneElTipo = new JLabel("Tipo de conversión");
		lblSeleccioneElTipo.setBounds(33, 120, 122, 13);
		lblSeleccioneElTipo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSeleccioneElTipo.setForeground(new Color(255, 255, 255));
		lblSeleccioneElTipo.setVerticalAlignment(SwingConstants.TOP);
		lblSeleccioneElTipo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblMensajeError_1 = new JLabel("No es un número.");
		lblMensajeError_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMensajeError_1.setBounds(528, 153, 85, 14);
		lblMensajeError_1.setForeground(Color.RED);
		lblMensajeError_1.setVisible(false);
		
		JComboBox<Object> cbxConversionInversa = new JComboBox<Object>();
		cbxConversionInversa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtCantidadDivisaPesos.getText().isEmpty()) {
					conversionDivisas(!conversion_a_Peso, txtCantidadDivisaPesos,txtCantidadPesosDivisa, convertirDinero, cbxConversionInversa);
				}
			}
		});
		cbxConversionInversa.setFont(new Font("Arial", Font.PLAIN, 11));
		cbxConversionInversa.setBounds(501, 111, 185, 22);
		cbxConversionInversa.setBackground(new Color(255, 255, 0));
		cbxConversionInversa.setModel(new DefaultComboBoxModel<>(opcionesConversionInversa.toArray()));

		//cbx de pesos a divisa
		cbxOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtCantidadPesosDivisa.getText().isEmpty()) {
					conversionDivisas(conversion_a_Peso,txtCantidadPesosDivisa, txtCantidadDivisaPesos, convertirDinero, cbxOpciones);
				}
			}
		});
		
		JLabel lblMensajeError = new JLabel("No es un número.");
		lblMensajeError.setBounds(179, 152, 85, 13);
		lblMensajeError.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMensajeError.setForeground(new Color(255, 255, 255));
		lblMensajeError.setVisible(false);;

		//Textbox que almacena la cantidad de pesos a convertir a divisa.
		txtCantidadPesosDivisa = new JTextField();
		txtCantidadPesosDivisa.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCantidadPesosDivisa.setBounds(154, 133, 178, 20);
		txtCantidadPesosDivisa.addKeyListener(new KeyAdapter() {
			@Override
			//Evento que se ejecuta cuando se deja de presionar (Empezas a escribir y luego dejas de escribir)
			public void keyReleased(KeyEvent e) {
				//JOptionPane.showMessageDialog(null, "KEY PRESSED");
				String textBoxDinero = txtCantidadPesosDivisa.getText();
				if(textBoxDinero.isEmpty()) {

				}
				else if(Metodos.esNumero(textBoxDinero) && !Metodos.contieneLetra(textBoxDinero)) {
					//lblMensajeError.setText("SI ES UN NUMERO");
					Metodos.ocultarLabel(lblMensajeError_1, lblMensajeError);
					conversionDivisas(conversion_a_Peso,txtCantidadPesosDivisa, txtCantidadDivisaPesos, convertirDinero, cbxOpciones);
				}
				else {
					Metodos.mostrarLabel(lblMensajeError);
				}
			}
		});
		txtCantidadPesosDivisa.setColumns(10);

		//Textbox que almacena la cantidad de divisa a convertir a pesos.
		txtCantidadDivisaPesos = new JTextField();
		txtCantidadDivisaPesos.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCantidadDivisaPesos.setBounds(501, 133, 185, 20);
		txtCantidadDivisaPesos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//JOptionPane.showMessageDialog(null, "KEY PRESSED");
				String textBoxDinero = txtCantidadDivisaPesos.getText();
				if(textBoxDinero.isEmpty()) {

				}
				else if(Metodos.esNumero(textBoxDinero) && !Metodos.contieneLetra(textBoxDinero)) {
					//lblMensajeError.setText("SI ES UN NUMERO");
					conversionDivisas(!conversion_a_Peso, txtCantidadDivisaPesos,txtCantidadPesosDivisa, convertirDinero, cbxConversionInversa);
					Metodos.ocultarLabel(lblMensajeError_1, lblMensajeError);
				}
				else {
					Metodos.mostrarLabel(lblMensajeError_1);
				}
			}
		});
		txtCantidadDivisaPesos.setColumns(10);
		
		JLabel lblSeleccioneElTipo_1 = new JLabel("Tipo de conversión");
		lblSeleccioneElTipo_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSeleccioneElTipo_1.setBounds(384, 119, 119, 14);
		lblSeleccioneElTipo_1.setVerticalAlignment(SwingConstants.TOP);
		lblSeleccioneElTipo_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDivisa = new JLabel("Divisa");
		lblDivisa.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDivisa.setBounds(384, 136, 107, 17);
		lblDivisa.setVerticalAlignment(SwingConstants.TOP);
		lblDivisa.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextPane txtpnConversinDePesos = new JTextPane();
		txtpnConversinDePesos.setBounds(33, 71, 310, 110);
		txtpnConversinDePesos.setFont(new Font("Arial", Font.PLAIN, 11));
		txtpnConversinDePesos.setEditable(false);
		txtpnConversinDePesos.setForeground(new Color(255, 255, 255));
		txtpnConversinDePesos.setBackground(new Color(153, 153, 204));
		txtpnConversinDePesos.setText("Pesos a divisa");
		txtpnConversinDePesos.setToolTipText("");
		
		JTextPane txtpnConversinDeDivisa = new JTextPane();
		txtpnConversinDeDivisa.setFont(new Font("Arial", Font.PLAIN, 11));
		txtpnConversinDeDivisa.setBounds(384, 71, 320, 110);
		txtpnConversinDeDivisa.setBackground(new Color(240, 255, 240));
		txtpnConversinDeDivisa.setEditable(false);
		txtpnConversinDeDivisa.setToolTipText("");
		txtpnConversinDeDivisa.setText("Divisa a pesos.");
		frmConversorDeMonedas.getContentPane().setLayout(null);
		frmConversorDeMonedas.getContentPane().add(lblSeleccioneElTipo);
		frmConversorDeMonedas.getContentPane().add(cbxOpciones);
		frmConversorDeMonedas.getContentPane().add(txtCantidadPesosDivisa);
		frmConversorDeMonedas.getContentPane().add(lblMensajeError);
		frmConversorDeMonedas.getContentPane().add(lblNewLabel);
		frmConversorDeMonedas.getContentPane().add(txtpnConversinDePesos);
		frmConversorDeMonedas.getContentPane().add(lblSeleccioneElTipo_1);
		frmConversorDeMonedas.getContentPane().add(lblDivisa);
		frmConversorDeMonedas.getContentPane().add(txtCantidadDivisaPesos);
		frmConversorDeMonedas.getContentPane().add(lblMensajeError_1);
		frmConversorDeMonedas.getContentPane().add(cbxConversionInversa);
		frmConversorDeMonedas.getContentPane().add(txtpnConversinDeDivisa);
		
		JButton btnNewButton = new JButton("Regresar");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(240, 248, 255));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConversorDisenio formPrincipal = new ConversorDisenio();
				formPrincipal.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(33, 11, 97, 27);
		frmConversorDeMonedas.getContentPane().add(btnNewButton);
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
					String resultadoConversion = String.format("%.5f", monedaConvertir .convertirMoneda(convertir_a_Peso,opc, Float.parseFloat(textBoxOrigen.getText().toString())));//Origen da valor a convertir
					textBoxDestino.setText(resultadoConversion);//Destino es el que almacena el resultado de la conversión.
				} catch (NumberFormatException | MonedaException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Asegúrese de ingresar un número.", "Error en conversión "+e1.getMessage(), 1);
				}
			}
		}
	}
}
