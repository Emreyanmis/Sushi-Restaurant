package sushigame.model;

import sushi.Plate;

public class PlatePlacedEvent extends PlateEvent {

	public PlatePlacedEvent (Plate p, int position) {
		super(BeltEvent.EventType.PLATE_PLACED, p, position);
	}
}
