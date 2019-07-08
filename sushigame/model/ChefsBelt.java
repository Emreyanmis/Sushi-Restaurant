package sushigame.model;

import sushi.Plate;

interface ChefsBelt extends Belt {

	int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException;

	
}
