/**
 * Classe cujas instancias representam itens encomendados
 * 
 * @author fc54412
 */
public class ItemEncomendado {
	int numeroEncomenda;
	int idItem;

	/**
	 * Construtor
	 * 
	 * @param numEncomenda Identificador da encomenda
	 * @param idItem       Identificador do item
	 */
	public ItemEncomendado(int numEncomenda, int idItem) {
		this.numeroEncomenda = numEncomenda;
		this.idItem = idItem;
	}

	/**
	 * O identificador da encomenda
	 */
	public int idEncomenda() {
		return this.numeroEncomenda;
	}

	/**
	 * O identificador do item
	 */

	public int idItem() {
		return this.idItem;
	}

	/**
	 * Representacao textual deste item encomendado
	 */
	@Override
	public String toString() {
		return " [item encomendado:" + numeroEncomenda + ", " + idItem + "] ";
	}

	/**
	 * Este item encomendado eh igual a outro?
	 * 
	 * @param obj O outro objeto
	 * @return true se este objeto eh igual a obj
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ItemEncomendado)) return false;
		else {
			ItemEncomendado obj1 = (ItemEncomendado) obj;
			return this.idItem() == obj1.idItem();
		}
	}

}
