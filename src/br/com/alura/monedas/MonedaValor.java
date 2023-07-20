package br.com.alura.monedas;

import br.com.alura.excepciones.MonedaException;

public class MonedaValor {
	
	//Valor de 1 peso mexicano a respectiva moneda
	private float[] valorMoneda = {0.0584233f, 0.0534826f, 0.0458863f, 8.44455f, 76.81f};
	
	/**
	 * Realiza la conversión de pesos mexicanos a la moneda:
	 * 0. Dolar,
	 * 1. Euro,
	 * 2. Libra esterlina,
	 * 3. Yen Japonés,
	 * 4. Won Coreano.
	 * @param pesoMexicano Cantidad de dinero a convertir.
	 * @param tipoConversion Numero que define a cuál moneda se va a convertir.
	 * @return Cantidad de peso mexicano convertido.
	 * @throws MonedaException Si la opción del tipo de conversión es inválida.
	 */
	public float convertirMoneda(int tipoConversion, float pesoMexicano) throws MonedaException {
		for(int i=0; i<this.valorMoneda.length; i++) {
			if(tipoConversion==i) {
				return pesoMexicano*valorMoneda[i];//Retorna el valor de la moneda convertida.
			}
		}
		throw new MonedaException("Opcion de conversión no valida.");
	}
	
	/**
	 * Realiza la conversión inversa de la moneda (De cualquier moneda a pesos mexicanos).
	 * @param tipoMoneda Un enum casteado a String que contenda el nombre del tipo de la moneda a convertir.
	 * @param cantidadMoneda Es la cantidad de la moneda a convertir.
	 * @return La conversión inversa (de monedas a pesos mexicanos)
	 * @throws MonedaException Si la opción del tipo de conversión es inválida.
	 */
	public float conversionInversa(String tipoMoneda, float cantidadMoneda) throws MonedaException {
		MonedaNombreEnum[] nombreMoneda=MonedaNombreEnum.values();//Array de valores constantes de nombres de monedas de enum
		for(int i=0; i<this.valorMoneda.length; i++) {
			if(tipoMoneda==nombreMoneda[i].toString()) {
				return cantidadMoneda/valorMoneda[i];//El valor convertido a pesos.
			}
		}
		throw new MonedaException("Opcion de conversión no valida.");
	}
}
