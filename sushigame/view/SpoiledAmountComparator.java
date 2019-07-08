package sushigame.view;

import java.util.Comparator;
import sushigame.model.Chef;

public class SpoiledAmountComparator implements Comparator<Chef> {
	@Override
	public int compare(Chef x, Chef y) {
		return (int) (Math.round(y.getFoodSpoiled() * 100.0) -  Math.round(x.getFoodSpoiled() * 100));
	}			
}