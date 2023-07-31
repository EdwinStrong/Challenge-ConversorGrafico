package br.com.alura.temperatura;

import br.com.alura.excepciones.TemperaturaException;

/**
 * Esta clase se encarga de definir las correspondientes fórmulas de conversión entre temperaturas.
 */

public class TemperaturaValor {

	/**
	 * Este método se encarga de aplicar las fórmulas respectivas para realizar las diferentes conversiones entre temperaturas.
	 * @param tipoConversion Esta variable define el tipo de conversión a realizar, con el formato: "De temperatura1 a temperatura2".
	 * @param valorTemperatura Esta variable es la que almacena el valor de la temperatura a convertir.
	 * @return El valor de la conversión realizada.
	 * @throws TemperaturaException Excepción que es lanzada cuando la opción de conversión no existe.
	 */
	public Float conversion(String tipoConversion, Float valorTemperatura) throws TemperaturaException{
		Float resultado = 0f;

		switch (tipoConversion) {
		
			//Celsius
		case "celsius a farenheit": 
			resultado = (float) (valorTemperatura * 1.8 + 32);
			break;
		case "celsius a kelvin": 
			resultado = (float) (valorTemperatura + 273.15);
			break;
		case "celsius a rankine":
			resultado =(float) ( 1.8 * (valorTemperatura + 273.15));
			break;
			
			//Farenheit
			
		case "farenheit a celsius": 
			resultado = (float) ((valorTemperatura - 32)/1.8);
			break;

		case "farenheit a kelvin": 
			resultado = (float) ((valorTemperatura + 459.67)/1.8);
			break;
			
		case "farenheit a rankine":
			resultado =(float) (valorTemperatura + 459.67);
			break;
			
			//Kelvin
			
		case "kelvin a celsius": 
			resultado = (float) (valorTemperatura - 273.15);
			break;

		case "kelvin a farenheit": 
			resultado = (float) (valorTemperatura * 9 - 459.67);
			break;
			
		case "kelvin a rankine":
			resultado =(float) (1.8 * valorTemperatura);
			break;
			
			//Rankine
		case "rankine a celsius": 
			resultado = (float) ((valorTemperatura - 491.67) / 1.8);
			break;

		case "rankine a farenheit": 
			resultado = (float) (valorTemperatura - 459.67);
			break;
			
		case "rankine a kelvin":
			resultado =(float) (valorTemperatura / 1.8);
			break;
			
			//Si no es una opción válida
			
		default:
			throw new TemperaturaException("Error");
		}
		
		return resultado;
	}
}