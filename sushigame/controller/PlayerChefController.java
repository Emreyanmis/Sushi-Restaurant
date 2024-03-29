package sushigame.controller;

import sushi.BluePlate;
import sushi.GoldPlate;
import sushi.GreenPlate;
import sushi.Plate;
import sushi.PlatePriceException;
import sushi.RedPlate;
import sushi.Sushi;
import sushi.Plate.Color;
import sushigame.model.AlreadyPlacedThisRotationException;
import sushigame.model.BeltFullException;
import sushigame.model.Chef;
import sushigame.model.InsufficientBalanceException;
import sushigame.view.ChefViewListener;
import sushigame.view.SushiGameView;

public class PlayerChefController implements ChefViewListener {

	private Chef chef;
	private SushiGameView game_view;
	
	public PlayerChefController(Chef playerChef, SushiGameView gv) {
		chef = playerChef;
		this.game_view = gv;
	}

	private void placePlate(Plate plate, int position) {
		try {
			chef.makeAndPlacePlate(plate, position);
		} catch (InsufficientBalanceException e) {
			game_view.setControllerMessage("Not Enough balance");
		} catch (BeltFullException e) {
			game_view.setControllerMessage("Belt is full");
		} catch (AlreadyPlacedThisRotationException e) {
			game_view.setControllerMessage("Already placed a plate this rotation");
		} catch (Exception e) {
			game_view.setControllerMessage(e.getMessage());
		}
	}

	@Override
	public void handleRedPlateRequest(Sushi plate_sushi, int plate_position) {
		try {
			Plate p = new RedPlate(chef, plate_sushi);
			placePlate(p, plate_position);
		} catch (PlatePriceException e) {
			game_view.setControllerMessage("Sushi is expensive for red plate.");
		}
	}

	@Override
	public void handleGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		try {
			Plate p = new GreenPlate(chef, plate_sushi);
			placePlate(p, plate_position);
		} catch (PlatePriceException e) {
			game_view.setControllerMessage("Sushi is expensive for green plate.");
		}
	}

	@Override
	public void handleBluePlateRequest(Sushi plate_sushi, int plate_position) {
		try {
			Plate p = new BluePlate(chef, plate_sushi);
			placePlate(p, plate_position);
		} catch (PlatePriceException e) {
			game_view.setControllerMessage("Sushi is expensive for blue plate.");
		}
	}

	@Override
	public void handleGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		try {
			Plate p = new GoldPlate(chef, plate_sushi, price);
			placePlate(p, plate_position);
		} catch (PlatePriceException e) {
			game_view.setControllerMessage("Sushi is expensive for gold plate.");
		}		
	}

}
