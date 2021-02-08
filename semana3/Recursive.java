import java.util.Arrays;

/**
 * 
 * @author fc54412
 *
 */

public class Recursive {

	/**
	 * Retorna base elevado ao expoente
	 * @param base base da potencia
	 * @param expoente 
	 * @return base elevado ao expoente
	 */
	public static double potencia(double base, int expoente) {
		if( expoente == 0 ) {
			return 1;
		}
		else {
			base *= potencia(base, expoente -1);
			return base;
		}

	}

	/**
	 * Retorna o numero de vezes que c aparece em s
	 * @param s String onde se pretende verificar se existe o char c
	 * @param c char que se quer verificar
	 * @requires c != null && s != null
	 * @return o numero de char c na string
	 */
	public static int quantosChars(String s, char c) {
		if( s.length() == 0) {
			return 0;
		}
		if(s.substring(0,1).charAt(0) == c) {
			return 1 + quantosChars(s.substring(1), c);
		}
		return quantosChars(s.substring(1),c);
	}

	/**
	 * Diz se n faz parte de v
	 * @param v vetor de ints, onde se pretende verificar se n existe
	 * @param n int que se pretende verificar se existe no vetor
	 * @requires v != null && n != null
	 * @return se n pretence a v
	 */

	public static boolean pertence(int[] v, int n) {
		if(v[0] == n) {
			return true;
		}
		else if(v.length > 1 ) {
			return pertence(Arrays.copyOfRange(v, 1, v.length), n);
		}
		else {
			return false;
		}
	}

	/**
	 * Numero de orelhas
	 * @param l numero de coelhos
	 * @requires l != null
	 * @return numero de orelhas
	 */

	public static int numeroOrelhas(int l) {
		if( l == 0) {
			return 0;
		}
		if( l%2 != 0) {
			return 3 + numeroOrelhas(l-1);
		}
		else {
			return 2 + numeroOrelhas(l-1);
		}
	}

	/**
	 * Soma dos algarismos de l
	 * @param l numero de qual se quer somar os algorismos
	 * @requires l != null
	 * @return soma dos algorimos de l
	 */

	public static int somaAlgarismos(int l) {
		if( l%10 == 0) {
			return 0;
		}
		else {
			return l%10 + somaAlgarismos(l/10);
		}
	}

	/**
	 * n * m
	 * @param n multiplicando
	 * @param m multiplicador
	 * @return n vezes m
	 */
	public static int produto(int n, int m) {
		if( m > 0) {
			return n + produto(n, m-1);
		}
		else if ( m < 0) {
			return -n + produto(n, m+1);
		}
		return 0;

	}
}

