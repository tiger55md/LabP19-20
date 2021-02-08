/**
 * 
 * @author fc54412 Daniel Levandovschi
 *
 */
public class Fish implements FishInterface {
	
	private String designation;
	private double weight;
	
	/**
	 * Construtor que da um nome e o peso ao peixe
	 * @param name nome da especie
	 * @param weight peso
	 */
	public Fish(String name, double weight) {
		designation = name;
		this.weight = (double)weight;
	}

	@Override
	public String getDesignation() {
		return designation;
	}

	@Override
	public double getWeight() {
		return (double)weight;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fish other = (Fish) obj;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  designation + ": " + weight + "kg" ;
	}
	

}
