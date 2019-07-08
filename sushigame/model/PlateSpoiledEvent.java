package sushigame.model;

import sushi.Plate;

public class PlateSpoiledEvent extends PlateEvent {

	public PlateSpoiledEvent (Plate p, int position) {
		super(BeltEvent.EventType.PLATE_SPOILED, p, position);
	}
}
