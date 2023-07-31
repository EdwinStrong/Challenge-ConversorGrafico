package br.com.alura.temperatura;

public class TemperaturaValor {

	private String[] tipoTemperatura = {"Celsius", "Farenheit", "Kelvin"};

	/**
	 * Este método se encarga de aplicar las fórmulas respectivas para realizar las diferentes conversiones entre temperaturas.
	 * @param tipoConversion Esta variable define el tipo de conversión a realizar, con el formato: "De temperatura1 a temperatura2".
	 * @param valorTemperatura Esta variable es la que almacena el valor de la temperatura a convertir.
	 * @return
	 * @throws MonedaException 
	 */
	public Float conversion(String tipoConversion, Float valorTemperatura){
		Float resultado = 0f;

		switch (tipoConversion) {
		case "celsius a farenheit": 
			resultado = (float) (valorTemperatura * 1.8 + 32);
			//System.out.println("celsius a farenheit");
			break;
		case "celsius a kelvin": 
			resultado = (float) (valorTemperatura + 273.15);
			//System.out.println("celsius a kelvin");
			break;

		case "farenheit a celsius": 
			resultado = (float) ((valorTemperatura - 32)/1.8);
			//System.out.println("farenheit a celsius");
			break;

		case "farenheit a kelvin": 
			resultado = (float) ((valorTemperatura + 459.67)/1.8);
			//System.out.println("farenheit a kelvin");
			break;

		case "kelvin a celsius": 
			resultado = (float) (valorTemperatura - 273.15);
			//System.out.println("kelvin a celsius");
			break;

		case "kelvin a farenheit": 
			resultado = (float) (valorTemperatura * 9 - 459.67);
			//System.out.println("kelvin a farenheit");
			break;

		}
		return resultado;
	}
}