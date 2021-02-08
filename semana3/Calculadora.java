/**
 * 
 * @author fc54412
 *
 */

public class Calculadora {

	//o valor do numero Pi
	private static final double PI = 3.14; 
	private static final int DEZ = 10;
	private int numeroDeCalculos;

	/**
	 * Construtor de calculadora
	 */
	public Calculadora() {
		this.numeroDeCalculos = 0;
	}

	/**
	 * Verifica se valor é multiplo de n
	 * @param valor
	 * @param n
	 * @return se valor é multiplo de n
	 */
	public boolean ehMultiplo (int valor, int n) {
		this.numeroDeCalculos++;
		return valor % n == 0;
	}

	/**
	 * 
	 * @return retorna pi
	 */
	public double numeroPi() {
		return PI;
	}

	/**
	 * 
	 * @return numero de calculos
	 */
	public int numeroCalculos() {
		return numeroDeCalculos;
	}


	/**
	 * verifica se valor é potencia de 10
	 * @param potencias potencias de 10
	 * @param valor valor a verificar
	 */
	public static void ehPotenciaDe10 (int[] potencias, int valor) {
		int power10 = 1;
		for (int i = 0; i < potencias.length; i++) {
			if(power10 == valor){
				potencias[i]++;
			}else{

				power10 *= DEZ;
			}
			System.out.println("Linha para controlo do que eh executado. "
					+ "Encontrei um problema neste metodo que nao faz o que eh suposto fazer!");
		}
	}

}
