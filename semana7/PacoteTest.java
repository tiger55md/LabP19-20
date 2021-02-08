import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class PacoteTest {
	private static final String IDC01250 = "IDC01250";
	private static final String MENSAGEM = "devem ser iguais";

	@Test
	public void testCapacidadeOcupada1() {
		Pacote<Par<Cliente, ItemEncomendado>> pacote = new Pacote<>(5);

		int esperado = 0;
		int actual = pacote.capacidadeOcupada();

		assertEquals(MENSAGEM, esperado, actual);
	}

	@Test
	public void testObtemCapacidadeOcupada2() {
		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);

		Pacote<Par<Cliente, ItemEncomendado>> pacote = new Pacote<>(5);
		pacote.empacota(par1);

		int esperado = 1;
		int actual = pacote.capacidadeOcupada();

		assertEquals(MENSAGEM, esperado, actual);
	}

	@Test
	public void testEstaCheio1() {

		Pacote<Par<Cliente, ItemEncomendado>> pacote = new Pacote<>(2);

		boolean esperado = false;
		boolean actual = pacote.estaCheio();

		assertEquals(MENSAGEM, esperado, actual);
	}

	@Test
	public void testEstaCheio2() {

		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);

		Pacote<Par<Cliente, ItemEncomendado>> pacote = new Pacote<>(2);

		pacote.empacota(par1);

		boolean esperado = false;
		boolean actual = pacote.estaCheio();

		assertEquals(MENSAGEM, esperado, actual);
	}

	@Test
	public void testEstaCheio3() {

		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);

		Pacote<Par<Cliente, ItemEncomendado>> pacote = new Pacote<>(2);

		pacote.empacota(par1);
		pacote.empacota(par1);

		boolean esperado = true;
		boolean actual = pacote.estaCheio();

		assertEquals(MENSAGEM, esperado, actual);
	}

	@Test
	public void testEmpacota() {
		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);

		Pacote<Par<Cliente, ItemEncomendado>> pacote = new Pacote<>(2);

		pacote.empacota(par1);

		Par<Cliente, ItemEncomendado> esperado = par1;
		Par<Cliente, ItemEncomendado> actual = pacote.iterator().next();

		assertEquals(MENSAGEM, esperado, actual);
	}

	@Test
	public void testIterator1() {

		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		ItemEncomendado item2 = new ItemEncomendado(700, 3250);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente1, item2);

		Pacote<Par<Cliente, ItemEncomendado>> pacote = new Pacote<>(2);

		pacote.empacota(par1);
		pacote.empacota(par2);

		StringBuilder res = new StringBuilder();

		for (Par<Cliente, ItemEncomendado> par : pacote) {
			res.append(par.segundo().idItem());
		}

		String esperada = "32503250";
		String actual = res.toString();

		assertEquals(MENSAGEM, esperada, actual);
	}

	@Test
	public void testIterator2() {
		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(450, 3250);
		ItemEncomendado item2 = new ItemEncomendado(700, 3250);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente1, item2);

		Pacote<Par<Cliente, ItemEncomendado>> pacote = new Pacote<>(2);

		pacote.empacota(par1);
		pacote.empacota(par2);

		Iterator<Par<Cliente, ItemEncomendado>> pacIterador = pacote.iterator();

		StringBuilder res = new StringBuilder();

		while (pacIterador.hasNext()) {
			res.append(pacIterador.next().segundo().idEncomenda());
		}

		String esperado = "450700";
		String actual = res.toString();

		assertEquals(MENSAGEM, esperado, actual);
	}

}
