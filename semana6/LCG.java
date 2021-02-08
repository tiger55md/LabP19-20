import java.util.ArrayList;

/**
 * 
 * @author Daniel Levandovschi fc54412
 *
 */

public class LCG {

	private static int seed;
	private int a;
	private int b;
	private int m;
	private int x0;
	private ArrayList<Integer> calculados;

	/**
	 * Construtor da classe LCG
	 * @param a numero por qual se multiplica
	 * @param b numero que se adiciona
	 * @param m numero por qual se faz modulo
	 * @param x0  seed/1o numero da sequencia
	 */
	public LCG(int a,int b, int m, int x0) {
		seed = x0;
		this.x0 = x0;
		this.a = a;
		this.b = b;
		this.m = m;
		calculados = new ArrayList<Integer>();
	}

	/**
	 * 
	 * @return a seed 
	 */
	public static int getSeed() {
		return seed;
	}

	/**
	 * 
	 * @return o proximo numero da sequencia
	 */
	public int next() {
		x0 = (a*x0 + b) % m;
		return x0;
	}

	/**
	 * 
	 * @return tamanho do ciclo
	 */
	public int cycleLength() {
		int next = next();
		while(!calculados.contains(next)) {
			calculados.add(next);
			next = next();
		}
		calculados.subList(0,calculados.indexOf(next)).clear();
		x0 = getSeed();
		return calculados.size();
	}
}
