import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author fc54412 Daniel Levandovschi
 *
 */
public class FishingBoat implements FishingBoatInterface {
	private Set<Pair> fishLoc;
	private Pair boatLoc;
	private FishBucket boatBucket;
	/**
	 * Cria um barco
	 * @param fishLoc localização dos peixes
	 * @param boatLoc localização do barco
	 */
	public FishingBoat(Set<Pair> fishLoc, Pair boatLoc) {
		this.boatLoc = boatLoc;
		this.fishLoc = fishLoc;
		boatBucket = new FishBucket();
	}

	@Override
	public FishBucket getBucket() {
		return boatBucket;

	}

	@Override
	public BoatAction nextMove() {
		if(fishLoc.isEmpty()) {
			return BoatAction.END_TRIP;
		}
		else if(fishLoc.contains(boatLoc)) {
			return BoatAction.FISH;
		}
		else {
			return nearFish();
		}

	}
	/**
	 * Verifica qual o peixe mais proximo
	 * @return peixe mais proximo
	 */
	private BoatAction nearFish() {
		int xDis = boatLoc.getX(), yDis = boatLoc.getY(), fishX = 0, fishY = 0;
		double minDist = 999999999;
		for(Pair fishLocs : fishLoc) {
			double dist = Math.sqrt(Math.sqrt((fishLocs.getY() - yDis)) + Math.sqrt(fishLocs.getX() - xDis)); //calcula a distancia entre o barco e o peixe
			if(minDist > dist) {
				minDist = dist; // se a distancia minima for maior que a distancia entre o barco e o peixe, a distancia minima passa a ser a dist entre barco e peixe
				fishX = fishLocs.getX();
				fishY = fishLocs.getY();
			}
		}
		if(fishX > xDis) return BoatAction.RIGHT;
		else if(fishX < xDis) return BoatAction.LEFT;
		else {
			if(fishY > yDis) return BoatAction.DOWN;
			else if(fishY < yDis) return BoatAction.UP;
		}
		return null;
	}


	@Override
	public void addFish(Fish f) {
		boatBucket.addFish(f);

	}

}
