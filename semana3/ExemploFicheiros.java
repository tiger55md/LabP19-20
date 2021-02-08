import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author fc54412
 *
 */
public class ExemploFicheiros {

	private static final double QUADRADO = 2;
	private static final int DEZ = 10;

	/**
	 * Copia texto do fileIn para fileOn
	 * @param fileIn ficheiro de entrada
	 * @param fileOut ficheiro de saida
	 * @throws FileNotFoundException
	 */
	public static void copiaTexto (String fileIn, String fileOut) throws FileNotFoundException {
		Scanner in = new Scanner (new File(fileIn));
		PrintWriter out = new PrintWriter(fileOut);
		// enquanto o ficheiro nao terminar 
		while (in.hasNext()){   
			out.println(in.nextLine());
		}
		in.close();
		out.close();

	}

	/**
	 * regista os quadrados dos numeros de in em out
	 * @param in ficheiro de entrada
	 * @param out ficheiro de saida
	 * @requires in!=null && out != null e in so com numeros  
	 */
	public static void escreveQuadrados(String fileIn, String fileOut)throws FileNotFoundException {
		Scanner in = new Scanner (new File(fileIn));
		PrintWriter out = new PrintWriter(fileOut);
		while(in.hasNext()) {
			out.println((int)Math.pow((double) in.nextInt(), QUADRADO));
		}
		in.close();
		out.close();
	}

	/**
	 * guarda multiplos de n de fileIn em fileOut
	 * @param fileIn ficheiro de entrada
	 * @param fileOut ficheiro de saida
	 * @param n numero de qual se quer saber se são multiplo
	 * @throws FileNotFoundException
	 */
	public static void guardaMultiplos(String fileIn, String fileOut, int n)throws FileNotFoundException {
		Scanner in = new Scanner(new File(fileIn));
		PrintWriter out = new PrintWriter(new File(fileOut));
		// enquanto o ficheiro nao terminar
		while (in.hasNextInt()){    
			int valorobtido = in.nextInt();
			Calculadora aminhacalculadora = new Calculadora();
			aminhacalculadora.ehMultiplo(valorobtido,n);
			if (aminhacalculadora.ehMultiplo(valorobtido,n)) {
				out.println(valorobtido);
			}
		}
		in.close();
		out.close();
	}

	/**
	 * 
	 * @param fileIn ficheiroDeEntrada
	 * @param vals valores que queremos verificar se existem no ficheiro de entrada
	 * @throws FileNotFoundException
	 */
	public static void elementosEmComum(String fileIn, int[] vals)throws FileNotFoundException {
		Scanner leitor = new Scanner (new File(fileIn));

		while(leitor.hasNext()){
			int valor = leitor.nextInt();
			for (int i = 0; i < vals.length ; i++) {
				if(vals[i] == valor){
					System.out.println(valor);
				}
			}
		}
		leitor.close();
	}  


	/**
	 * Faz a copia das linhas de um ficheiro escrevendo-as noutro ficheiro
	 * com as letras todas minusculas e as letras todas maiusculas
	 * linha sim linha nao
	 * @param fileIn - o nome do ficheiro origem
	 * @param fileOut - o nome do ficheiro destino
	 * @throws FileNotFoundException
	 * @requires fileIn != null && fileOut != null
	 */
	public static void minusculasMaiusculas (String fileIn, String fileOut) 

			throws FileNotFoundException{
		Scanner in = new Scanner (new File(fileIn));
		PrintWriter out = new PrintWriter(fileOut);
		boolean flag = true;
		// enquanto o ficheiro nao terminar 
		while (in.hasNextLine()){  
			if(flag) {
				out.write(in.nextLine().toLowerCase() + "\n");
			} else if (!flag) {
				out.write(in.nextLine().toUpperCase() + "\n");
			}
			flag = !flag;          
		}

		out.close();
		in.close();
	}
	/**
	 * Calcula o arredondamento dado um algarismo para a dezena mais proxima 
	 * @param algarismo - representacao de um algarismo em String
	 * @return 0 se 0 <= algarismo < 5 e 10 caso contrario
	 * @requires 0 <= algarismo < 10
	 */
	public static int metodo(String algarismo) {
		if((algarismo == "2" || algarismo == "3" || algarismo == "0" || 
				algarismo == "1" || algarismo == "4" )){
			return 0;
		}
		if(algarismo == "5" || algarismo == "6" || algarismo == "7" ||
				algarismo == "8" || algarismo == "9") {
			return DEZ;
		}
		return 0;
	}

}
