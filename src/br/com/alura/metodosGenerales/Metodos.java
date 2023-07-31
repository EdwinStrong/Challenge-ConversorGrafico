package br.com.alura.metodosGenerales;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

public class Metodos {

	/**
	 * Este método define si una cadena de texto contiene una palabra.
	 * @param palabra
	 * @return True SI halla una palabra. False si NO halla una palabra.
	 */
	public static boolean contieneLetra(String palabra){
		// Expresión regular para identificar si hay una letra en el texto
		String regex = "[A-Za-z]";

		// Compilar la expresión regular en un patrón
		Pattern pattern = Pattern.compile(regex);

		// Crear un objeto Matcher para buscar coincidencias en el texto
		Matcher matcher = pattern.matcher(palabra);

		// Devolver true si se encuentra al menos una letra en el texto (False si no)
		return matcher.find();
	}

	/**
	 * Este método define si una cadena de texto contiene un espacio en blanco.
	 * @param palabra
	 * @return True SI halla un espacio en blanco. False si NO halla un espacio en blanco.
	 */
	public static boolean contieneEspacio(String palabra){
		// Expresión regular para identificar si hay un espacio en blanco en el texto
		String regex = "\\s";

		// Compilar la expresión regular en un patrón
		Pattern pattern = Pattern.compile(regex);

		// Crear un objeto Matcher para buscar coincidencias en el texto
		Matcher matcher = pattern.matcher(palabra);

		// Devolver true si se encuentra al menos un espacio en blanco en el texto
		return matcher.find();
	}

	/**
	 * Este método define si una cadena de caracteres contiene solo números.
	 * @param palabra
	 * @return True SI la cadena de caracteres contiene SOLO numeros. False si NO contiene SOLO numeros.
	 */
	public static boolean esNumero(String palabra){
		try {
			Float.parseFloat(palabra);
		}catch(NumberFormatException | NullPointerException e) {
			return false;//Si no se realiza el parseo es porque NO es un número.
		}

		// Devolver FALSE si se encuentra al menos un caracter que no sea número ni punto en el texto, y retorna lo inverso (TRUE).
		return true;
	}
	
	/**
	 * Este método se encarga de ocultar un label.
	 * @param label1 Es el label del mensaje del error a ocultar.
	 */
	public static void ocultarLabel(JLabel label1) {
		label1.setVisible(false);
	}
	/**
	 * Este método se encarga de ocultar dos label.
	 * @param label1 Es el primer label del mensaje del error a ocultar.
	 * @param label2 Es el segundo label del mensaje del error a ocultar.
	 */
	public static void ocultarLabel(JLabel label1, JLabel label2) {
		label1.setVisible(false);
		label2.setVisible(false);
	}
	/**
	 *  Este método se encarga de mostrar un label.
	 * @param label1 Es el label a mostrar.
	 */
	public static void mostrarLabel(JLabel label1) {
		label1.setVisible(true);
	}
}
