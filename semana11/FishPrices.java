import java.util.HashMap;

/**
 * 
 * @author fc54412 Daniel Levandovschi
 *
 */
public class FishPrices implements FishPricesInterface {
	private HashMap<Fish, Double> prices;


	/**
	 * Cria a tabela dos preços dos peixes
	 */
	public FishPrices() {
		prices= new HashMap<Fish, Double> ();
	}

	@Override
	public void addFishPrice(Fish f, double price) {
		if(!prices.containsKey(f))
			prices.put(f, price);
		else {
			prices.put(f, prices.get(f) + price);
		}


	}

	@Override
	public double getFishPrice(Fish f) {
		if(prices.containsKey(f)) {
			return prices.get(f);
		}
		else {
			return 1.0;
		}
	}

}
