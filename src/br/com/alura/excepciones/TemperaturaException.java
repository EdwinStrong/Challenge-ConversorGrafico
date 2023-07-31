package br.com.alura.excepciones;

public class TemperaturaException extends Exception{
	
	/**
	 * Clase de excepciones para la clase de TemperaturaValor.
	 */
	private static final long serialVersionUID = 1L;

	public TemperaturaException(String mensaje){
		super(mensaje);
	}
}
