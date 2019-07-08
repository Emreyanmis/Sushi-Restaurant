package sushigame.model;

import sushi.Plate;

public interface HistoricalPlate extends Plate {

	boolean wasSpoiled();
	Customer getConsumer();
}
