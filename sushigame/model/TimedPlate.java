package sushigame.model;

import sushi.Plate;

public interface TimedPlate extends Plate {
	int getInceptDate();
	Plate getOriginal();
}
