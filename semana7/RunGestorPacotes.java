import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que permite testar as classes da semana 7
 * 
 * @author fc54412
 */
public class RunGestorPacotes {

	/**
	 * Ler as entradas de um ficheiro de dados, criar um GestorPacotes e invocar
	 * varios metodos desta classe.
	 * 
	 * @param args O primeiro elemento deste array representa o nome do ficheiro de
	 *             dados
	 * @throws FileNotFoundException Se um ficheiro com esse nome nao existe
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// Leitura dos dados
		Scanner leitor = new Scanner(new File(args[0]));
		String linha = leitor.nextLine();
		int capacidade = Integer.parseInt(linha);
		ArrayList<Par<Cliente, ItemEncomendado>> listaDeEntradas = leDados(leitor);
		leitor.close();

		// Cria gestor de pacotes com a capacidade lida do ficheiro
		GestorPacotes<Cliente, ItemEncomendado> gestor = new GestorPacotes<>(capacidade);

		// Cria pacotes
		gestor.criaPacotes(listaDeEntradas);

		// Mostra os pacotes formados
		System.out.println("Pacotes formados: \n" + gestor.toString());

		// Conta numero de pacotes criados
		System.out.println("Numero de pacotes criados: " + gestor.numeroPacotes());

		// Conta numero de itens empacotados
		System.out.println("Numero de itens empacotados: " + gestor.numeroElementosEmpacotados());

		// Cria pares
		Cliente cliente1 = new Cliente("IDC01250");
		Cliente cliente2 = new Cliente("IDC02074");
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		ItemEncomendado item2 = new ItemEncomendado(450, 1186);

		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente2, item2);

		// Conta numero de pacotes enviados para dado cliente
		System.out.println("Numero de pacotes criados para o cliente" + par1.primeiro().toString() + " = "
				+ gestor.numeroElementosK(par1.primeiro()));
		System.out.println("Numero de pacotes criados para o cliente" + par2.primeiro().toString() + " = "
				+ gestor.numeroElementosK(par2.primeiro()));

		// Lista de clientes que encomendaram determinado item
		ArrayList<Cliente> lista1 = (ArrayList<Cliente>) gestor.listaElementosK(par1.segundo());
		System.out.println("Lista de clientes que encomendaram o item com codigo " + par1.segundo().idItem() + ": ");
		for (Cliente c : lista1) {
			System.out.print(c.toString() + " ");
		}
		System.out.println();
	}

	/**
	 * Criacao de um par a partir de linha de dados
	 * 
	 * @param linha A string com a linha de dados
	 * @return Um par com o formato adequado
	 * @requires linha != null
	 */
	private static Par<Cliente, ItemEncomendado> leEntrada(String linha) {
		System.out.println("Entrada: " + linha);

		String[] dados = linha.split(" ");

		Cliente cliente = new Cliente(dados[0]);
		ItemEncomendado item = new ItemEncomendado(Integer.parseInt(dados[1]), Integer.parseInt(dados[2]));
		return new Par<>(cliente, item);
	}

	/**
	 * Para leitura dos items a dar ao construtor
	 * 
	 * @param leitor O scanner usado para o efeito
	 * @return Uma lista com os itens e respetivos tamanhos
	 * @requires leitor != null
	 */
	private static ArrayList<Par<Cliente, ItemEncomendado>> leDados(Scanner leitor) {
		ArrayList<Par<Cliente, ItemEncomendado>> listaDeEntradas = new ArrayList<>();

		String linha;
		while (leitor.hasNextLine()) {
			linha = leitor.nextLine();
			Par<Cliente, ItemEncomendado> entrada = leEntrada(linha);
			listaDeEntradas.add(entrada);
		}
		return listaDeEntradas;
	}

}
