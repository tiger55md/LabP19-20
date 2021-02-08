package semana9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @author Daniel Levandovschi fc54412
 */


public class Padroes {
	/**
	 * Verifica se a string dada é um pin
	 * @param s string dada
	 * @return se a string é pin ou nao
	 */
	public static boolean matchPIN(String s) {
		Pattern pin = Pattern.compile("\\b[0-9]{4}\\b");
		Matcher pinMatch = pin.matcher(s);
		return pinMatch.find();
	}
	
	/**
	 * Verifica se a string dada é um no de telefone zimbabweano
	 * @param s string dada
	 * @return se a string é um no zimbabweano ou nao
	 */
	public static boolean matchTEL(String s) {
		Pattern tel = Pattern.compile("(00|[+])(263)([7])([0-9]{8})\\b");
		Matcher telMatch = tel.matcher(s);
		return telMatch.find();
		
	}
	
	/**
	 * Verifica se a string dada é uma coordenada GPS
	 * @param s string dada
	 * @return se a string é uma coordenada GPS ou nao
	 */
	public static boolean matchGPS(String s) {
		Pattern gps = Pattern.compile("([0-8]?[0-9]|90)°([0-5]?[0-9]')?([0-5]?[0-9](,[0-9])?\\\")(S|N) ([0-1]?[0-7]?[0-9]|180)°([0-5]?[0-9]')?([0-5]?[0-9](,[0-9])?\\\")(W|E)$");
		Matcher gpsMatch = gps.matcher(s);
		return gpsMatch.find();
	}
	
	/**
	 * Verifica se a string dada são permissões unix
	 * @param s string dada
	 * @return se a string são permissões unix ou nao
	 */
	public static boolean matchPERM(String s) {
		Pattern perm = Pattern.compile("(-|r)(-|w)(-|x)(-|r)(-|w)(-|x)(-|r)(-|w)(-|x)$");
		Matcher permMatch = perm.matcher(s);
		return permMatch.find();
	}
	
	/**
	 * Verifica se a string dada é um comentario haskell
	 * @param s string dada
	 * @return se a string é um comentario haskell ou nao
	 */
	public static boolean matchHASKELL(String s) {
		Pattern perm = Pattern.compile("[-]{2} \\||\\^");
		Matcher permMatch = perm.matcher(s);
		return permMatch.find();
		
	}
	
	/**
	 * Verifica se a string dada é uma designação do virus COVID
	 * @param s string dada
	 * @return se a string é uma designação de COVID ou nao
	 */
	public static boolean matchCOVID(String s) {
		Pattern pin = Pattern.compile("(COVID-19)|(COVID)([-])([2-9][0-9])");
		Matcher pinMatch = pin.matcher(s);
		return pinMatch.find();
		
	}

}
