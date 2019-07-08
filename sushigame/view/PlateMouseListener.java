package sushigame.view;

import java.awt.ComponentOrientation;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import comp401sushi.Plate;
import sushigame.model.Belt;

public class PlateMouseListener implements MouseListener {
	private Plate plate_obj;
	private Belt belt_obj;
	private int position;
	
	public PlateMouseListener(Plate plate, Belt belt, int position) {
		plate_obj = plate;
		belt_obj = belt;
		this.position = position;
	}
	
	public Plate getPlate() {
		return plate_obj;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		JFrame framer = new JFrame();
		JLabel plate_color = new JLabel ("Color of the Plate: " + plate_obj.getColor().toString());
		JPanel info_display = new JPanel();
		JLabel sushi_type = new JLabel ("Type of Sushi: " + plate_obj.getContents().getName());
		
		
		info_display.add(plate_color);
		info_display.add(sushi_type);
		if(plate_obj.getContents().getName().contains("Roll")) {
			for(int i = 0; i < plate_obj.getContents().getIngredients().length; i++) {
				JLabel ingredient = 
						new JLabel("Roll has: " + plate_obj.getContents().getIngredients()[i].getAmount()   + "oz of " + plate_obj.getContents().getIngredients()[i].getName());
				info_display.add(ingredient);
			}
		}
		
		JLabel name_of_chef = new JLabel("Name of the Chef: " + plate_obj.getChef().getName());
		JLabel age_of_plate = new JLabel("Age of the Plate: " + belt_obj.getAgeOfPlateAtPosition(position));
		
		info_display.add(name_of_chef);
		info_display.add(age_of_plate);
		framer.add(info_display);
		framer.setSize(420, 420);
		framer.setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}