package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class FoodSoldComparator implements Comparator<Chef> {
	
	@Override
	public int compare(Chef x, Chef y) {
		return (int) (Math.round(y.getFoodSold() * 100.0) - Math.round(x.getFoodSold() * 100));
	}			
}