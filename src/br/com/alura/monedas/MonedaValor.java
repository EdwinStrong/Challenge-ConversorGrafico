package br.com.alura.monedas;

import br.com.alura.excepciones.MonedaException;

public class MonedaValor {

	//Valor de 1 peso mexicano a respectiva moneda
	private float[] valorMoneda = {0.0584233f, 0.0534826f, 0.0458863f, 8.44455f, 76.81f};
	/**
	 * Realiza la conversión de pesos a la moneda (Y viceversa):
	 * 0. Dolar,
	 * 1. Euro,
	 * 2. Libra esterlina,
	 * 3. Yen Japonés,
	 * 4. Won Coreano.
	 * @param esPeso Define si la conversión es de peso a divisa (TRUE), o de una divisa a peso (FALSE).
	 * @param cantidadConvertir Cantidad de dinero a convertir.
	 * @param tipoConversion Numero que define a cuál moneda se va a convertir.
	 * @return Cantidad de peso mexicano convertido.
	 * @throws MonedaException Si la opción del tipo de conversión es inválida.
	 */
	public float convertirMoneda(Boolean esPeso, int tipoConversion, float cantidadConvertir) throws MonedaException {
		//Peso a divisa
		if(esPeso) {
			for(int i=0; i<this.valorMoneda.length; i++) {
				if(tipoConversion==i) {
					return cantidadConvertir*valorMoneda[i];//Retorna el valor de la moneda convertida.
				}
			}
		}
		
		//Divisa a peso.
		if(!esPeso) {
			int cantidadMonedas = valorMoneda.length;
			for(int i=cantidadMonedas; i<cantidadMonedas*2; i++) {
				if(tipoConversion==i) {
					//JOptionPane.showMessageDialog(null, "Lo que se ejecuta es: "+ (i-valorMoneda.length));
					//JOptionPane.showMessageDialog(null, cantidadConvertir + " / "+valorMoneda[i-valorMoneda.length]);
					return cantidadConvertir/valorMoneda[i-cantidadMonedas];//Retorna el valor de la moneda convertida.
				}
			}
		}
		throw new MonedaException("Opcion de conversión no valida.");
	}
}
