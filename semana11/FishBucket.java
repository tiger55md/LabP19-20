
import java.util.HashMap;

/**
 * 
 * @author fc54412 Daniel Levandovschi
 *
 */
public class FishBucket implements FishBucketInterface {
	
	private HashMap<Fish, Double> bucket;
	
	/**
	 * Construtor do balde
	 */
	public FishBucket() {
		bucket = new HashMap<Fish, Double>();
	}

	@Override
	public void addFish(Fish f) {
		if(!bucket.containsKey(f))
			bucket.put(f, f.getWeight());
		else
			bucket.put(f, (double) bucket.get(f) + f.getWeight());
		
	}

	@Override
	public double getFishWeight(Fish f) {
		if(bucket.containsKey(f)) {
			return (double) bucket.get(f);
		}
		else {
			return 0;
		}
	}

	@Override
	public double totalWeight() {
		double weigh = 0;
		for(double val: bucket.values()) {
			weigh += val;
		}
		return weigh;
	}

	@Override
	public void removeFish(Fish f) {
		while(bucket.containsKey(f)) bucket.remove(f);
		
	}

	@Override
	public double bucketValue(FishPrices prices) {
		double value = 0;
		for(Fish f: bucket.keySet()) {
			value += (double)prices.getFishPrice(f) * getFishWeight(f);
		}
		return (double) value;
	}

	@Override
	public String toString() {
		return bucket.keySet() + "; \n" + "Total weight of bucket: " + totalWeight() + "kg";
	}
	

}
