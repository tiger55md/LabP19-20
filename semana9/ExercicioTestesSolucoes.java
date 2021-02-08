package semana9;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * fc54412
 */
public class ExercicioTestesSolucoes {
	invertreDobre(double n){
		1. Devemos considerar as carateristicas do tipo de dados (devemos experimentar correr o metodo com ints ou strings), devemos 
		tambem considerar o valor que damos como argumento
		2. O tipo de dados pode ser um qualquer(string,int,etc) e tentar correr com os valores 0, valores negativos e positivos.
		3.
		Carateristica N N1- Dado de parametro é double N2- não é double
		Carateristica M M1- Dado de paramentro não é 0 M2 - é 0;
		4.
		
		@Test
		public void testDobre() {
			assertEquals(inverteDobre(2.4),true);
		}
		@Test
		public void testDobre1() {
			assertEquals(inverteDobre(0.0),false);
		}
		@Test
		public void testDobre2() {
			assertEquals(inverteDobre(0),false);
		}
		@Test
		public void testDobre3() {
			assertEquals(inverteDobre(4),false);
		}
		@Test
		public void testDobre4() {
			assertEquals(inverteDobre("a"),false);
		}
		
	contaMaioresQue(ArrayList<Integer> lista, int x){
		1. Devemos considerar carateristicas como se a lista esta vazia, se os valores da lista sao diferentes de Integer
		2.A lista pode estar ou nao vazia, e o tipo de dados na lista pode ser um qualquer.
		3.Carateristica N lista vazia N1- Nao vazia N2- esta vazia
		Carateristica M tipo de dados M1- Tipo int M2- Nao int
		4.
		@Test
		public void testDobre() {
			assertEquals(contaMaioresQue((new ArrayList(2,3,4,4,5,6)), 4),2);
		}
		@Test
		public void testDobre1() {
			assertEquals(contaMaioresQue((new ArrayList()), 4),0);
		}
		@Test
		public void testDobre3() {
			assertEquals(contaMaioresQue((new ArrayList("2","3","4","4","5","6")), 4),0);
		}
		
		
	}

}
