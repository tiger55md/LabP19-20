package semana9;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * @author Daniel Levandovschi fc54412
 */

public class Testes {
	@Test
	public void testPIN1() {
		assertEquals(Padroes.matchPIN("o meu pin é 1234"),true);
	}
	
	@Test
	public void testPIN2() {
		assertEquals(Padroes.matchPIN("o meu pin é 12aC"),false);
	}
	
	@Test
	public void testPIN3() {
		assertEquals(Padroes.matchPIN("o meu pin é 12"),false);
	}
	
	@Test
	public void testPIN4() {
		assertEquals(Padroes.matchPIN("o meu pin é 1234abc"),false);
	}
	
	@Test
	public void testTEL1() {
		assertEquals(Padroes.matchTEL("o meu tel é 00263799293323"),true);
	}

	@Test
	public void testTEL2() {
		assertEquals(Padroes.matchTEL("o meu tel é 263099293323"),false);
	}
	
	@Test
	public void testTEL3() {
		assertEquals(Padroes.matchTEL("o meu tel é 0026309929332"),false);
	}
	
	@Test
	public void testTEL4() {
		assertEquals(Padroes.matchTEL("o meu tel é 00263099293323412312"),false);
	}
	
	@Test
	public void testTEL5() {
		assertEquals(Padroes.matchTEL("o meu tel é 00099293323"),false);
	}
	
	@Test
	public void testTEL6() {
		assertEquals(Padroes.matchTEL("o meu tel é +263799293323"),true);
	}
	@Test
	public void testGPS1() {
		assertEquals(Padroes.matchGPS("9°9'9,4\"N 179°9'9\"E"),true);
	}
	@Test
	public void testGPS2() {
		assertEquals(Padroes.matchGPS("24°12'2.4S 181°12'2.4\"E"),false);
	}
	@Test
	public void testGPS3() {
		assertEquals(Padroes.matchGPS("24°12'2.4 24°12'2.4W"),false);
	}
	@Test
	public void testGPS4() {
		assertEquals(Padroes.matchGPS("243212°12'2.4N 24°12'2.4W"),false);
	}
	
	@Test
	public void testGPS5() {
		assertEquals(Padroes.matchGPS("24°12222'2.4N 24°12'2.4W"),false);
	}
	
	@Test
	public void testGPS6() {
		assertEquals(Padroes.matchGPS("24°12'2.4N 24°12'24444.42W"),false);
	}
	
	@Test 
	public void testPERM1() {
		assertEquals(Padroes.matchPERM("rwxrwxr--"),true);
	}
	
	@Test 
	public void testPERM2() {
		assertEquals(Padroes.matchPERM("xwr----ra"),false);
	}
	
	@Test 
	public void testPERM3() {
		assertEquals(Padroes.matchPERM("xwr----r---"),false);
	}
	
	@Test 
	public void testHASK1() {
		assertEquals(Padroes.matchHASKELL("-- |Comment"),true);
	}
	
	@Test 
	public void testHASK2() {
		assertEquals(Padroes.matchHASKELL("-Comment"),false);
	}
	
	@Test 
	public void testHASK3() {
		assertEquals(Padroes.matchHASKELL("-- ^Comment"),true);
	} 
	
	@Test 
	public void testCOVID1() {
		assertEquals(Padroes.matchCOVID("COVID-19"),true);
	}
	
	@Test 
	public void testCOVID2() {
		assertEquals(Padroes.matchCOVID("COVID19"),false);
	}
	
	@Test 
	public void testCOVID3() {
		assertEquals(Padroes.matchCOVID("COVID-1"),false);
	}
	
	@Test 
	public void testCOVID4() {
		assertEquals(Padroes.matchCOVID("COVI21"),false);
	}
	
	@Test 
	public void testCOVID5() {
		assertEquals(Padroes.matchCOVID("COVID-18"),false);
	}
}
