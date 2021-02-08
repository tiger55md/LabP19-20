public class ParDeSimbolos {
	private String inicio;
	private String fim;
	
	/**
	 * 
	 * @param o inicio dos parenteses
	 * @param fim dos parenteses
	 */
	public ParDeSimbolos(String inicio, String fim) {
		this.inicio = inicio;
		this.fim = fim;
	}
	/**
	 * 
	 * @return o inicio
	 */
	public String getInicio() {
		return this.inicio;
	}
	
	/**
	 * 
	 * @return o fim
	 */
	public String getFim() {
		return this.fim;
	}
}
