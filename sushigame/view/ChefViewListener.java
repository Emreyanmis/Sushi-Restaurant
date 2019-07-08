package sushigame.view;

import sushi.Plate;
import sushi.Sushi;

public interface ChefViewListener {

	void handleRedPlateRequest(Sushi plate_sushi, int plate_position);
	void handleGreenPlateRequest(Sushi plate_sushi, int plate_position);
	void handleBluePlateRequest(Sushi plate_sushi, int plate_position);
	void handleGoldPlateRequest(Sushi plate_sushi, int plate_position, double price);
}
