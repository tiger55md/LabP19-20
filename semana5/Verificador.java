import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Daniel Levandovschi fc54412
 *
 */
public class Verificador {
	public static Stack<String> stack = new Stack<String>();
	private static ArrayList<ParDeSimbolos> list = new ArrayList<ParDeSimbolos>();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner verifier = new Scanner(new File(args[1]));
		PrintWriter pw = new PrintWriter(args[2]);
		lerSimbolos(args[0]);
		while(verifier.hasNextLine()) {
			String linha = verifier.nextLine();
			if(verificador(linha,pw)) {
				pw.println(linha + "==> ok");
			}
		}

		verifier.close();
		pw.close();

	}

	/**
	 * 
	 * @param fileIn ficheiro de entrada
	 * @requires simbolos separados por espaços
	 * @throws FileNotFoundException
	 */
	public static void lerSimbolos(String fileIn) throws FileNotFoundException { 
		Scanner sc = new Scanner(new File(fileIn));
		while(sc.hasNext()) {
			ParDeSimbolos simbolos = new ParDeSimbolos(sc.next(), sc.next());
			list.add(simbolos);			
		}
		sc.close();
	}

	/**
	 * 
	 * @param linha linha a ler, os elementos da expressão devem ser separados por espaços
	 * @param pw printwriter para escrever no ficheiro de saida
	 * @return se a expressão tem parenteses balanceados
	 * @requires elementos da expressão devem ser separados por espaços
	 * @throws FileNotFoundException
	 */
	public static boolean verificador(String linha, PrintWriter pw) throws FileNotFoundException {
		String[] linha2 = linha.split(" ");
		boolean verifier = true;
		for(int i = 0; i < linha2.length && verifier; i++) {
			for(int j = 0; j < list.size(); j++) {
				String pop;
				if(linha2[i].equals(list.get(j).getInicio())) {
					stack.push(linha2[i]);
				}
				if(linha2[i].contains(list.get(j).getFim())) {
					if(stack.isEmpty()){
						pw.println(linha + "==> encontrei " + linha2[i] + " extemporaneo");
						verifier = false;
					}
					else if(!(pop = stack.pop()).contains(list.get(j).getInicio())) {
						pw.println(linha + "==> Esperava " + expected(pop) + " encontrei " + linha2[i] );
						verifier = false;
					}
				}

			}
		}
		stack.clear();
		return verifier;
	}

	/**
	 * 
	 * @param start parenteses que deu erro
	 * @return parenteses esperado
	 */
	public static String expected(String start) {
		String devolve = "a";
		for(int i = 0; i <  list.size(); i++) {
			if(start.contains(list.get(i).getInicio())) {
				devolve = list.get(i).getFim();
			}
		}
		return devolve;
	}
}
