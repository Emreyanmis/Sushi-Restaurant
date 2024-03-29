package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
	private JLabel[] belt_labels;

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(belt.getSize(), 1));
		belt_labels = new JLabel[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {
			JLabel plabel = new JLabel("");
			plabel.setMinimumSize(new Dimension(300, 20));
			plabel.setPreferredSize(new Dimension(300, 20));
			plabel.setOpaque(true);
			plabel.setBackground(Color.GRAY);
			add(plabel);
			belt_labels[i] = plabel;
		}
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
	
		for (int i=0; i<belt.getSize(); i++) {
			Plate plate_obj = belt.getPlateAtPosition(i);
			JLabel plabel = belt_labels[i];
			
			for(int j = 0; j < plabel.getMouseListeners().length; j++) {
				MouseListener temp = plabel.getMouseListeners()[j];
				plabel.removeMouseListener(temp);
			}
			
			if(plate_obj != null) {
				plabel.addMouseListener(new PlateMouseListener(plate_obj, belt, i));
			}
			
			if (plate_obj == null) {
				plabel.setText("");
				plabel.setBackground(Color.GRAY);
			} else {
				plabel.setText(plate_obj.getContents().getName().toString() + " (Click here for more information about plate!)");
			
				switch (plate_obj.getColor()) {
				case RED:
					plabel.setBackground(Color.RED);
					break;
				case GREEN:
					plabel.setBackground(Color.GREEN);
					break;
				case BLUE:
					plabel.setBackground(Color.BLUE);
					break;
				case GOLD:
					plabel.setBackground(Color.YELLOW);
					break;
				
				}
		}	
	 }
  }	
}	
