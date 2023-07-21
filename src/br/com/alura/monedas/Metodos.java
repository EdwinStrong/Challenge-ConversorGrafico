package br.com.alura.monedas;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * Este método define si una cadena de caracteres contiene solo números..
	 * @param palabra
	 * @return True SI la cadena de caracteres contiene SOLO numeros. False si NO contiene SOLO numeros.
	 */
	public static boolean esNumero(String palabra){
		// Expresión regular para identificar si hay un espacio en blanco en el texto
        String regex = "[^0-9.]";

        // Compilar la expresión regular en un patrón
        Pattern pattern = Pattern.compile(regex);

        // Crear un objeto Matcher para buscar coincidencias en el texto
        Matcher matcher = pattern.matcher(palabra);

        // Devolver FALSE si se encuentra al menos un carácter que no sea número ni punto en el texto
        return !matcher.find();
	}
}
