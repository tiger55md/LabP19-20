import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GestorPacotesTest {
	private static final String IDC01250 = "IDC01250";
	private static final String IDC02000 = "IDC02000";
	private static final String IDC03140 = "IDC03140";
	private static final String MENSAGEM = "devem ser iguais";

	@Test
	public void testCriaPacotes1() {

		List<Par<Cliente, ItemEncomendado>> listaDeItems = new ArrayList<>();

		Cliente cliente1 = new Cliente(IDC01250);
		Cliente cliente2 = new Cliente(IDC02000);
		Cliente cliente3 = new Cliente(IDC03140);
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		ItemEncomendado item2 = new ItemEncomendado(450, 1186);
		ItemEncomendado item3 = new ItemEncomendado(451, 3250);
		ItemEncomendado item4 = new ItemEncomendado(452, 1186);
		ItemEncomendado item5 = new ItemEncomendado(450, 9340);
		ItemEncomendado item6 = new ItemEncomendado(452, 3250);

		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente2, item3);
		Par<Cliente, ItemEncomendado> par3 = new Par<>(cliente3, item4);
		Par<Cliente, ItemEncomendado> par4 = new Par<>(cliente1, item2);
		Par<Cliente, ItemEncomendado> par5 = new Par<>(cliente1, item5);
		Par<Cliente, ItemEncomendado> par6 = new Par<>(cliente3, item6);

		listaDeItems.add(par1);
		listaDeItems.add(par2);
		listaDeItems.add(par3);
		listaDeItems.add(par4);
		listaDeItems.add(par5);
		listaDeItems.add(par6);

		GestorPacotes<Cliente, ItemEncomendado> gestor = new GestorPacotes<>(2);

		gestor.criaPacotes(listaDeItems);

		String esperada = "[[ [ idCliente IDC01250]   [item encomendado:450, 3250] ] , [ [ idCliente IDC01250]   [item encomendado:450, 1186] ] ]\n"
				+ "[[ [ idCliente IDC02000]   [item encomendado:451, 3250] ] ]\n"
				+ "[[ [ idCliente IDC03140]   [item encomendado:452, 1186] ] , [ [ idCliente IDC03140]   [item encomendado:452, 3250] ] ]\n"
				+ "[[ [ idCliente IDC01250]   [item encomendado:450, 9340] ] ]\n";
		String actual = gestor.toString();

		assertEquals(MENSAGEM, esperada, actual);
	}

	@Test
	public void testNumeroPacotes() {

		List<Par<Cliente, ItemEncomendado>> listaDeItems = new ArrayList<>();

		Cliente cliente1 = new Cliente(IDC01250);
		Cliente cliente2 = new Cliente(IDC02000);
		Cliente cliente3 = new Cliente(IDC03140);
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		ItemEncomendado item2 = new ItemEncomendado(450, 1186);
		ItemEncomendado item3 = new ItemEncomendado(451, 3250);
		ItemEncomendado item4 = new ItemEncomendado(452, 1186);
		ItemEncomendado item5 = new ItemEncomendado(450, 9340);
		ItemEncomendado item6 = new ItemEncomendado(452, 3250);

		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente2, item3);
		Par<Cliente, ItemEncomendado> par3 = new Par<>(cliente3, item4);
		Par<Cliente, ItemEncomendado> par4 = new Par<>(cliente1, item2);
		Par<Cliente, ItemEncomendado> par5 = new Par<>(cliente1, item5);
		Par<Cliente, ItemEncomendado> par6 = new Par<>(cliente3, item6);

		listaDeItems.add(par1);
		listaDeItems.add(par2);
		listaDeItems.add(par3);
		listaDeItems.add(par4);
		listaDeItems.add(par5);
		listaDeItems.add(par6);

		GestorPacotes<Cliente, ItemEncomendado> gestor = new GestorPacotes<>(2);

		gestor.criaPacotes(listaDeItems);

		int esperado = 4;
		int actual = gestor.numeroPacotes();

		assertEquals(MENSAGEM, esperado, actual);
	}

	@Test
	public void testNumeroElementosEmpacotados() {

		List<Par<Cliente, ItemEncomendado>> listaDeItems = new ArrayList<>();

		Cliente cliente1 = new Cliente(IDC01250);
		Cliente cliente2 = new Cliente(IDC02000);
		Cliente cliente3 = new Cliente(IDC03140);
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		ItemEncomendado item2 = new ItemEncomendado(450, 1186);
		ItemEncomendado item3 = new ItemEncomendado(451, 3250);
		ItemEncomendado item4 = new ItemEncomendado(452, 1186);
		ItemEncomendado item5 = new ItemEncomendado(450, 9340);
		ItemEncomendado item6 = new ItemEncomendado(452, 3250);

		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente2, item3);
		Par<Cliente, ItemEncomendado> par3 = new Par<>(cliente3, item4);
		Par<Cliente, ItemEncomendado> par4 = new Par<>(cliente1, item2);
		Par<Cliente, ItemEncomendado> par5 = new Par<>(cliente1, item5);
		Par<Cliente, ItemEncomendado> par6 = new Par<>(cliente3, item6);

		listaDeItems.add(par1);
		listaDeItems.add(par2);
		listaDeItems.add(par3);
		listaDeItems.add(par4);
		listaDeItems.add(par5);
		listaDeItems.add(par6);

		GestorPacotes<Cliente, ItemEncomendado> gestor = new GestorPacotes<>(2);

		gestor.criaPacotes(listaDeItems);

		int esperado = 6;
		int actual = gestor.numeroElementosEmpacotados();

		assertEquals(MENSAGEM, esperado, actual);
	}

	@Test
	public void testNumeroElementosK() {

		List<Par<Cliente, ItemEncomendado>> listaDeItems = new ArrayList<>();

		Cliente cliente1 = new Cliente(IDC01250);
		Cliente cliente2 = new Cliente(IDC02000);
		Cliente cliente3 = new Cliente(IDC03140);
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		ItemEncomendado item2 = new ItemEncomendado(450, 1186);
		ItemEncomendado item3 = new ItemEncomendado(451, 3250);
		ItemEncomendado item4 = new ItemEncomendado(452, 1186);
		ItemEncomendado item5 = new ItemEncomendado(450, 9340);
		ItemEncomendado item6 = new ItemEncomendado(452, 3250);

		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente2, item3);
		Par<Cliente, ItemEncomendado> par3 = new Par<>(cliente3, item4);
		Par<Cliente, ItemEncomendado> par4 = new Par<>(cliente1, item2);
		Par<Cliente, ItemEncomendado> par5 = new Par<>(cliente1, item5);
		Par<Cliente, ItemEncomendado> par6 = new Par<>(cliente3, item6);

		listaDeItems.add(par1);
		listaDeItems.add(par2);
		listaDeItems.add(par3);
		listaDeItems.add(par4);
		listaDeItems.add(par5);
		listaDeItems.add(par6);

		GestorPacotes<Cliente, ItemEncomendado> gestor = new GestorPacotes<>(2);

		gestor.criaPacotes(listaDeItems);

		int esperado = 2;
		int actual = gestor.numeroElementosK(par1.primeiro());

		assertEquals(MENSAGEM, esperado, actual);
	}

	@Test
	public void testListaElementosK() {

		List<Par<Cliente, ItemEncomendado>> listaDeItems = new ArrayList<>();

		Cliente cliente1 = new Cliente(IDC01250);
		Cliente cliente2 = new Cliente(IDC02000);
		Cliente cliente3 = new Cliente(IDC03140);
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		ItemEncomendado item2 = new ItemEncomendado(450, 1186);
		ItemEncomendado item3 = new ItemEncomendado(451, 3250);
		ItemEncomendado item4 = new ItemEncomendado(452, 1186);
		ItemEncomendado item5 = new ItemEncomendado(450, 9340);
		ItemEncomendado item6 = new ItemEncomendado(452, 3250);

		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente2, item3);
		Par<Cliente, ItemEncomendado> par3 = new Par<>(cliente3, item4);
		Par<Cliente, ItemEncomendado> par4 = new Par<>(cliente1, item2);
		Par<Cliente, ItemEncomendado> par5 = new Par<>(cliente1, item5);
		Par<Cliente, ItemEncomendado> par6 = new Par<>(cliente3, item6);

		listaDeItems.add(par1);
		listaDeItems.add(par2);
		listaDeItems.add(par3);
		listaDeItems.add(par4);
		listaDeItems.add(par5);
		listaDeItems.add(par6);

		GestorPacotes<Cliente, ItemEncomendado> gestor = new GestorPacotes<>(2);

		gestor.criaPacotes(listaDeItems);

		// "[[ idCliente IDC01250] [ idCliente IDC02000] [ idCliente IDC03140]]"
		int esperado = 3;
		int actual = (gestor.listaElementosK(par1.segundo())).size();

		assertEquals(MENSAGEM, esperado, actual);
	}
}
