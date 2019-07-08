package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.SushiGameModel;

public class SushiGameView extends JPanel implements ActionListener, BeltObserver {

	private PlayerChefView player;
	private List<RotationRequestListener> rotation_request_listeners;
	private JLabel controller_messages;
	ScoreboardWidget scoreboard;
	
	public SushiGameView(SushiGameModel game_model) {
		setLayout(new BorderLayout());
		
		scoreboard = new ScoreboardWidget(game_model);
		add(scoreboard, BorderLayout.WEST);
				
		player = new PlayerChefView(game_model.getBelt().getSize());
		add(player, BorderLayout.EAST);
		
		BeltView belt_view = new BeltView(game_model.getBelt());
		add(belt_view, BorderLayout.CENTER);
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setLayout(new BorderLayout());
		
		JButton rotate_button = new JButton("Rotate");
		rotate_button.setActionCommand("rotate");
		rotate_button.addActionListener(this);
		
		bottom_panel.add(rotate_button, BorderLayout.WEST);
		
		controller_messages = new JLabel("Controller Messages.");
		bottom_panel.add(controller_messages, BorderLayout.CENTER);
		
		add(bottom_panel, BorderLayout.SOUTH);
		
		rotation_request_listeners = new ArrayList<RotationRequestListener>();
		
		game_model.getBelt().registerBeltObserver(this);
	}
	
	public void registerPlayerChefListener(ChefViewListener cl) {
		player.registerChefListener(cl);
	}
	
	public void registerRotationRequestListener(RotationRequestListener rrl) {
		rotation_request_listeners.add(rrl);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if (action.getActionCommand().equals("rotate")) {
			for (RotationRequestListener rrl : rotation_request_listeners) {
				rrl.handleRotationRequest();
			}
		}
	}
	
	public void setControllerMessage(String message) {
		controller_messages.setText(message);
	}

	@Override
	public void handleBeltEvent(BeltEvent event) {
		if (event.getType() == BeltEvent.EventType.ROTATE) {
			controller_messages.setText("");
		}
	}
	
	public void refreshScoreboard() {
		scoreboard.refresh();
	}
}
