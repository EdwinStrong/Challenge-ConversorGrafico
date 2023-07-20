package br.com.alura.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.alura.excepciones.MonedaException;
import br.com.alura.monedas.MonedaNombreEnum;
import br.com.alura.monedas.MonedaValor;


public class ConversorMain {

	/**-
	 * Este método se encarga de definir el nombre de las monedas en plural para mostrar la conversión..
	 * @param nombreMoneda proviene de la clase enum encargada de definir la cantidad de monedas utilizadas.
	 * @return String que contiene el nombre en plural de cada moneda.
	 */
	public static String obtenerNombreMonedas(MonedaNombreEnum nombreMoneda) {
		String moneda = null;
		switch (nombreMoneda) {
		case DOLAR:
			moneda="Dólares";
			break;
		case EURO:
			moneda="Euros";
			break;
		case LIBRA_ESTERLINA:
			moneda="Libras esterlinas";
			break;
		case YEN_JAPONES:
			moneda = "Yen Japonés";
			break;
		case WON_COREANO:
			moneda = "Won coreano";
			break;
		default:
			moneda="Dolares";
			break;
		}
		return moneda;
	}

	/**
	 * Este método contiene toda la lógica detrás del proceso de la conversión de una moneda. Realizado mediante indices.
	 * @throws MonedaException Encargada de validar que los indices de las opciones de conversión de la clase MonedaValor sean correctas.
	 */
	public static void conversionMoneda() throws MonedaException {
		HashMap<Integer, String> opcConversion = new HashMap<>();//Hashmap de opciones de conversión de monedas.

		MonedaValor monedaConversion=new MonedaValor();//Instancia de clase para conversiones.

		MonedaNombreEnum[] nombreMoneda=MonedaNombreEnum.values();//Array de valores constantes de nombres de monedas.

		//Llenar hashmap que contiene opciones de conversión.

		int indice=nombreMoneda.length;

		for (int i=0; i<indice; i++) {
			opcConversion.put(i, "De pesos a " + nombreMoneda[i].toString().toLowerCase().replace("_", " "));//De pesos a moneda
			opcConversion.put(indice+i, "De " + nombreMoneda[i].toString().toLowerCase().replace("_", " ") + " a pesos.");//De moneda a pesos
		}

		//Variables de apoyo
		float dineroConvertir=-1;//Cantidad de pesos mexicanos que se quieren convertir.
		boolean exception=true;;

		while(exception || dineroConvertir < 0) {
			try {
				//Solicitar cantidad de dinero a convertir.
				String input=(String) JOptionPane.showInputDialog(null, "Ingrese la cantidad de dinero que desea convertir: ", "Ingreso de datos",
						JOptionPane.INFORMATION_MESSAGE, null, null, 0.00);
				if(input==null) {//Opción de cancelar.
					System.exit(0);//Parar el programa
				}
				else {
					dineroConvertir=Float.parseFloat(input);
					exception=false;
				}

			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Ingrese un dato numérico.");
			}
		}

		//Convertir hashmap a Array

		List<String> valores = new ArrayList<>(opcConversion.values());//Hashmap a lista dinámica

		Object[] opcionesArray = valores.toArray();//lista dinámica a array

		String seleccionConversion = (String) JOptionPane.showInputDialog(null, "Elija la moneda a la que deseas convertir tu dinero", "Monedas", JOptionPane.INFORMATION_MESSAGE,
				null, opcionesArray, opcionesArray[0]);//Me retorna el valor del String del hashmap

		if(seleccionConversion != null) {//Diferente a "Cancelar"
			for (int i=0; i<opcConversion.size(); i++) {
				if(opcConversion.get(i)==seleccionConversion) {//Obtener indice de conversion
					String mensaje = "Ninguna opción ha sido ejecutada.";
					try {//Indice normal (Conversión de pesos a otra moneda)
						mensaje = String.format("$%.3f pesos mexicanos son $%.3f %s", dineroConvertir, monedaConversion.convertirMoneda(i, dineroConvertir), 
								obtenerNombreMonedas(nombreMoneda[i]));//Mensaje de pesos mexicanos a otra moneda.
					} catch (MonedaException e) {//Fuera de indice normal (Conversión de otra moneda a pesos)
						mensaje = String.format("$%.3f %s son $%.3f pesos.", dineroConvertir,obtenerNombreMonedas(nombreMoneda[i-indice]), 
								monedaConversion.conversionInversa(nombreMoneda[i-indice].toString(), dineroConvertir));//Mensaje de convertir una moneda a pesos mexicanos.
					}finally {//Impresión de mensaje.
						JOptionPane.showMessageDialog(null, mensaje);
					}
				}
			}
		}
	}

	/**
	 * Este es el método principal main, encargado de la compilación de todo el código.
	 * @param args parámetro del main.
	 */
	
	public static void conversionTemperatura() {
		
		Float temperaturaValor = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese la cantidad de temperatura a converitr"));
		
		HashMap<String, Float> tipoTemperatura = new HashMap<>();
		
		tipoTemperatura.put("De Celsius a Farenheit", (float) (temperaturaValor*1.8 + 32));
		tipoTemperatura.put("De Celsius a Kelvin", (float) (temperaturaValor + 273.15));
		
		tipoTemperatura.put("De Farenheit a Celsius", (float) ((temperaturaValor - 32)/1.8));
		tipoTemperatura.put("De Farenheit a Kelvin", (float) ((temperaturaValor + 459.67)/1.8));
		
		tipoTemperatura.put("De Kelvin a Celsius", (float) (temperaturaValor - 273.15));
		tipoTemperatura.put("De Kelvin a Farenheit", (float) (temperaturaValor*9 - 459.67));
		
		
		List<String> nombreTemperatura = new ArrayList<>(tipoTemperatura.keySet());//Convertir las claves String a Lista.
				
		Object[] opciones = nombreTemperatura.toArray();//Hashmap a Arrau

		String opcion = (String) JOptionPane.showInputDialog(null, "Seleccione la opción.", "Temperatura", JOptionPane.INFORMATION_MESSAGE, null,
				opciones, opciones[0]);
		
		if(opcion!=null) {
			if(tipoTemperatura.containsKey(opcion)) {
				String mensaje = String.format("%3.2f celsius son %3.2fF", temperaturaValor, tipoTemperatura.get(opcion));//Obtener valores de clave (El entero)
				JOptionPane.showMessageDialog(null, mensaje);
			}
		}
		
	}
	
	public static void main(String[] args){
		int respuesta=0;

		do {
			String[] opcMenuPrincipal = {"Conversor de moneda", "Conversor de temperatura"};

			//Opciones de conversiones
			String seleccion=(String) JOptionPane.showInputDialog(null, "Seleccione una opción de conversión", "Menu", JOptionPane.INFORMATION_MESSAGE,
					null, opcMenuPrincipal, opcMenuPrincipal[0]);

			if(seleccion==opcMenuPrincipal[0]) {//Convertir moneda
				try {
					conversionMoneda();
				} catch (MonedaException e) {
					JOptionPane.showMessageDialog(null, "Error en los indices para la conversión.", null, JOptionPane.ERROR_MESSAGE);
				}
			}

			else if(seleccion==opcMenuPrincipal[1]) {//Convertir temperatura
				
				try {
					conversionTemperatura();
				}catch(Exception e) {
					
				}
			}
			
			respuesta=JOptionPane.showConfirmDialog(null, "¿Desea continuar?");
			
		}while(respuesta==JOptionPane.YES_OPTION);
		
		JOptionPane.showMessageDialog(null, "Programa finalizado.");//Programa finalizado.
		System.exit(0);
	}
}
