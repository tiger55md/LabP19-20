
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
* @author Daniel Levandovschi fc54412
*
*/

public class ExercicioExcecoes {

	/**
	 * Ler vetor de inteiros e uma potencia e imprimir os valores das potencias dos
	 * inteiros
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) {
		Scanner sc = null;
		try {
		sc = new Scanner(new File(args[0]));
		} 
		catch(FileNotFoundException e) {
			System.out.println("Ficheiro não existe");
		}
		
		int tamanho = 0;
		
		try {
			tamanho = lerTamanhoVetor(sc);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Valor não pode ser convertido para inteiro");
		}
		catch(InputMismatchException e) {
			System.out.println("O tamanho do vetor tem que ser positivo e inteiro");
		}
		
		int[] inteiros = new int[tamanho];
		
		try {
			inteiros = lerInteirosVetor(sc, tamanho);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Valor não pode ser convertido para inteiro");
		}
		catch(InputMismatchException e) {
			System.out.println("Vetor tem que ter o tamanho igual a variavel tamanho e tem que ser inteiro");
		}

		
		double potencia = lerValorPotencia(sc);
		if(potencia < 0) {
			throw new IllegalArgumentException("A potencia tem que ser positiva");
		}
		double[] calculados = new double[tamanho];
		try {
		calculados = calculaPotencias(inteiros, potencia);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Valor não pode ser convertido para inteiro");
		}
		catch(ArithmeticException e) {
			System.out.println("A potencia tem que ser um valor positivo");
		}
		
		imprimirVetor(calculados);
	}

	/**
	 * Ler e devolver o tamanho do vetor original
	 * 
	 * @param sc - o scanner usado para a leitura
	 * @return o tamanho do vetor a ler
	 * @requires sc != null
	 */
	public static int lerTamanhoVetor(Scanner sc){
		String n = sc.next();
		if( Integer.parseInt(n) < 0 ) {
			throw new InputMismatchException("Têm que ser só numeros positivos");
		}
		return Integer.parseInt(n);
	}

	/**
	 * Ler um dado numero de inteiros e guardar num vetor
	 * 
	 * @param sc      - o scanner usado para a leitura
	 * @param tamanho - o numero de valores a ler
	 * @return o vetor de inteiros obtidos da leitura
	 * @requires sc != null && tamanho > 0
	 */
	public static int[] lerInteirosVetor(Scanner sc, int tamanho) {
		int[] nums1 = new int[tamanho];
		String a = sc.nextLine();
		a = sc.nextLine();
		System.out.println(tamanho);
		String[] nums = a.split(" ");
		if( nums.length != tamanho) {
			throw new InputMismatchException("O numero de digitos tem que ser igual ao tamanho");
		}
		for(int i = 0; i < nums.length; i++) {
			nums1[i] = Integer.parseInt(nums[i]);
		}
		return nums1;
	}

	/**
	 * Ler um valor que representa a potencia
	 * 
	 * @param sc - o scanner usado para a leitura
	 * @return o valor obtido da leitura
	 * @requires sc != null
	 */
	public static double lerValorPotencia(Scanner sc) {
		return Double.parseDouble(sc.nextLine());
	}

	/**
	 * Determina as potencias de um vector
	 * 
	 * @param inteiros - o vetor original
	 * @param potencia - a potencia que se pretende calcular do vetor de inteiros
	 * @return o vetor com as potencias de inteiros
	 * @requires inteiros != null && potencia >= 0
	 */
	private static double[] calculaPotencias(int[] inteiros, double potencia) {
		double[] potencias = new double[inteiros.length];
		for(int i = 0; i < inteiros.length; i++) {
			if( new Double(Math.pow(inteiros[i], potencia)).isNaN()) {
				throw new ArithmeticException("É impossivel realizar a operação da raiz num numero negativo");
			}
			potencias[i] = Math.pow(inteiros[i], potencia);
		}
		return potencias;
	}

	/**
	 * Imprime os valores de um vetor de doubles
	 * 
	 * @param v - o vetor do qual se quer imprimir os valores
	 */
	private static void imprimirVetor(double[] v) {
		for (double d : v) {
			System.out.println(d);
		}
	}

}
