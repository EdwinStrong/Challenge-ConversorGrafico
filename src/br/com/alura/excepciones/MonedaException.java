package br.com.alura.excepciones;

public class MonedaException extends Exception{
	/**
	 * Clase de excepciones para la clase de MonedaValor.
	 */
	private static final long serialVersionUID = 1L;

	public MonedaException(String mensaje) {
		super(mensaje);
	}
}
