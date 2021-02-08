import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParTest {
	private static final String IDC01250 = "IDC01250";
	private static final int ENCOMENDA = 450;
	private static final int ITEM = 3250;
	private static final String MENSAGEM_IGUAIS = "devem ser iguais";
	private static final String MENSAGEM_DIFERENTES = "devem ser diferentes";

	@Test
	public void testPrimeiro() {
		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(ENCOMENDA, ITEM);
		Par<Cliente, ItemEncomendado> par = new Par<>(cliente1, item1);

		Cliente esperado = cliente1;

		Cliente actual = par.primeiro();

		assertEquals(MENSAGEM_IGUAIS, esperado, actual);
	}

	@Test
	public void testSegundo() {
		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(ENCOMENDA, ITEM);
		Par<Cliente, ItemEncomendado> par = new Par<>(cliente1, item1);

		ItemEncomendado expected = item1;

		ItemEncomendado actual = par.segundo();

		assertEquals(MENSAGEM_IGUAIS, expected, actual);
	}

	@Test
	public void testEqualsObject() {
		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(ENCOMENDA, ITEM);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente1, item1);

		boolean esperado = true;

		boolean actual = par1.equals(par2);

		assertEquals(MENSAGEM_IGUAIS, esperado, actual);
	}

	@Test
	public void testEqualsObject1() {
		Cliente cliente1 = new Cliente(IDC01250);
		Cliente cliente2 = new Cliente("IDC04750");
		ItemEncomendado item1 = new ItemEncomendado(ENCOMENDA, ITEM);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente2, item1);

		boolean esperado = false;

		boolean actual = par1.equals(par2);

		assertEquals(MENSAGEM_DIFERENTES, esperado, actual);
	}

	@Test
	public void testEqualsObject2() {
		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(ENCOMENDA, ITEM);
		ItemEncomendado item2 = new ItemEncomendado(ENCOMENDA, 8431);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente1, item2);

		boolean esperado = false;

		boolean actual = par1.equals(par2);

		assertEquals(MENSAGEM_DIFERENTES, esperado, actual);
	}

	@Test
	public void testEqualsObject3() {
		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(ENCOMENDA, ITEM);
		ItemEncomendado item2 = new ItemEncomendado(700, 3250);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = new Par<>(cliente1, item2);

		boolean esperado = true;

		boolean actual = par1.equals(par2);

		assertEquals(MENSAGEM_IGUAIS, esperado, actual);
	}

	@Test
	public void testEqualsObject4() {
		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(ENCOMENDA, ITEM);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<Cliente, ItemEncomendado> par2 = null;

		boolean esperado = false;

		boolean actual = par1.equals(par2);

		assertEquals(MENSAGEM_DIFERENTES, esperado, actual);
	}

	@Test
	public void testEqualsObject5() {
		Cliente cliente1 = new Cliente(IDC01250);
		ItemEncomendado item1 = new ItemEncomendado(ENCOMENDA, ITEM);
		Par<Cliente, ItemEncomendado> par1 = new Par<>(cliente1, item1);
		Par<String, ItemEncomendado> par2 = new Par<>(cliente1.idCliente(), item1);

		boolean esperado = false;

		boolean actual = par1.equals(par2);

		assertEquals(MENSAGEM_DIFERENTES, esperado, actual);
	}
}
