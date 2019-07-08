package sushigame.model;

import sushi.Plate;

public class RandomCustomer implements Customer {

	private double pickiness;
	
	public RandomCustomer(double pickiness) {
		this.pickiness = pickiness;
	}

	@Override
	public boolean consumesPlate(Plate p) {
		return (Math.random() < pickiness);
	}

}
