package sushigame.model;

import sushi.Plate;
import sushi.Sushi;
import sushi.Plate.Color;

public interface Chef {

	String getName();
	void setName(String name);
	
	void makeAndPlacePlate(Plate plate, int position) 
			throws InsufficientBalanceException, BeltFullException, AlreadyPlacedThisRotationException;
		
	HistoricalPlate[] getPlateHistory(int max_history_length);
	HistoricalPlate[] getPlateHistory();
	
	
	boolean alreadyPlacedThisRotation();
	double getBalance();
	double getFoodSpoiled();
	double getFoodSold();
	
}

