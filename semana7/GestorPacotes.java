import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe cujas instancias sabem criar pacotes a partir de uma lista de entradas
 * 
 * @author fc54412
 */
public class GestorPacotes<K, F> {
	// Colecao dos pacotes
	private List<Pacote<Par<K, F>>> pacotes;
	// Capacidade maxima de cada pacote
	private int capacidadePacotes;
	//Pares utilizados no criaPacotes
	private ArrayList<Par<K, Pacote<Par<K,F>>>> used;

	/**
	 * Construtor
	 * 
	 * @param capacidadePacotes Capacidade maxima de cada pacote
	 * @requires capacidadePacotes > 0
	 */
	public GestorPacotes(int capacidadePacotes) {
		this.pacotes = new ArrayList<>();
		this.capacidadePacotes = capacidadePacotes;
	}

	/**
	 * Cria pacotes de entradas a partir de uma lista de entradas Cada entrada eh um
	 * par Cada pacote contem apenas entradas com valores iguais dos seus primeiros
	 * elementos
	 * 
	 * @param entradas A lista com as entradas a empacotar
	 */
	public void criaPacotes(List<Par<K, F>> entradas) {
		used = new ArrayList<Par<K, Pacote<Par<K,F>>>>();
		for(int i = 0; i < entradas.size(); i++) {
			Par<K, F> novoPar = entradas.get(i);
			boolean verif = false;
			for(int j = 0; j < used.size(); j++) {
				Par<K, Pacote<Par<K,F>>> novo = used.get(j);
				if(novoPar.primeiro().equals(novo.primeiro())) {
					if(!novo.segundo().estaCheio()) {
						novo.segundo().empacota(novoPar);
						verif = true;
					}
				}
			}
			if(!verif) {
				Pacote<Par<K,F>> pacote = new Pacote<Par<K,F>>(capacidadePacotes);
				pacote.empacota(novoPar);
				used.add(new Par<K, Pacote<Par<K,F>>>(novoPar.primeiro(), pacote));
			}
		}
		
		for(int i = 0; i < used.size(); i++) {
			pacotes.add(used.get(i).segundo());
		}
	}

	/**
	 * O numero total de elementos guardados nos pacotes deste gestor de pacotes
	 */
	public int numeroElementosEmpacotados() {
		int ocupado = 0;
		for(int i = 0; i < pacotes.size(); i++) {
			ocupado += pacotes.get(i).capacidadeOcupada();
		}
		return ocupado;
	}

	/**
	 * Numero de pacotes deste gestor de pacotes
	 */
	public int numeroPacotes() {
		return pacotes.size();
	}

	/**
	 * Numero de pacotes deste gestor de pacotes que teem um dado primeiro elemento
	 * 
	 * @paramp prim Primeiro elemento a pesquisar
	 * @return Numero de pacotes com primeiro elemento igual a prim
	 * 
	 */
	public int numeroElementosK(K prim) {
		int count=0;
		for(int i = 0; i < pacotes.size();i++) {
			Iterator<Par<K,F>> elementos = pacotes.get(i).iterator();
			if(((Par<K, F>) elementos.next()).primeiro().equals(prim)) {
				count++;
			}
		}	
		return count;

	}

	/**
	 * Lista com os primeiros elementos dos pares cujo segundo elemento tem o valor
	 * dado
	 * 
	 * @param seg Valor do segundo elemento do par a procurar
	 * @return A lista de primeiros elementos dos pares que teem seg como segundo
	 *         elemento
	 */
	public List<K> listaElementosK(F seg) {
		List<K> elementos = new ArrayList<K>();
		for(int i = 0; i < pacotes.size();i++) {
			Iterator<Par<K,F>> elements = pacotes.get(i).iterator();
			while(elements.hasNext()) {
				Par<K, F> novo = (Par<K, F>) elements.next();
				if(novo.segundo().equals(seg)) {
					elementos.add(novo.primeiro());
				}
			}
		}
		return elementos;
	}

	/**
	 * Representacao textual dos pacotes criados, um por linha
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Pacote<Par<K, F>> pEmp : pacotes) {
			sb.append(pEmp.toString() + "\n");
		}
		return sb.toString();
	}


}
