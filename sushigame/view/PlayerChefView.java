package sushigame.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sushi.AvocadoPortion;
import sushi.CrabPortion;
import sushi.EelPortion;
import sushi.IngredientPortion;
import sushi.Nigiri;
import sushi.Plate;
import sushi.RicePortion;
import sushi.Roll;
import sushi.Sashimi;
import sushi.SeaweedPortion;
import sushi.ShrimpPortion;
import sushi.Sushi;
import sushi.TunaPortion;
import sushi.YellowtailPortion;

public class PlayerChefView extends JPanel implements ActionListener {
	private List<ChefViewListener> listeners;
	private Plate.Color sashimi_color;
	private Plate.Color roll_color;
	private Nigiri.NigiriType nigiri_type;
	private Plate.Color nigiri_color;
	private Sashimi.SashimiType sahimi_type;
	private IngredientPortion[] roll_portion = new IngredientPortion[8];
	
	
	private double roll_avocado = 0;
	private double roll_crab = 0;
	private double roll_eel = 0;
	private double roll_rice = 0;
	private double roll_yellowtail = 0;
	private double roll_seaweed = 0;
	private double roll_shrimp = 0;
	private double roll_tuna = 0;
	private double gold_sashimi_price;
	private double gold_nigiri_price;
	private double gold_rollPrice;
	
	private int belt_size;
	private int roll_position;
	private int sashimi_position;
	private int nigiri_position;
	
	
	
	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton sashimi_button = new JButton("Create New Sashimi");
		sashimi_button.setActionCommand("red_crab_sashimi_at_3");
		sashimi_button.addActionListener(this);
		add(sashimi_button);

		JButton nigiri_button = new JButton("Create New Nigiri");
		nigiri_button.setActionCommand("blue_eel_nigiri_at_8");
		nigiri_button.addActionListener(this);
		add(nigiri_button);

		JButton roll_button = new JButton("Create New Roll");
		roll_button.setActionCommand("gold_kmp_roll_at_5");
		roll_button.addActionListener(this);
		add(roll_button);

	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}
	
	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "red_crab_sashimi_at_3") {
			makeSashimi();
		}
		else if(e.getActionCommand() == "gold_kmp_roll_at_5") {
			makeRoll();
		}
		else if(e.getActionCommand() == "blue_eel_nigiri_at_8") {
			makeNigiri();
		}
	}
	
	public void makeSashimi() {
		JFrame framer = new JFrame("Create your Sashimi");
		JPanel panel = new JPanel(new GridLayout(0, 1));
		
		JLabel label_gold = new JLabel("What is the price of the plate: ");
		SpinnerModel model_gold = new SpinnerNumberModel(5.0, 5.0, 10.0, .01);
		JSpinner price_gold_plate = new JSpinner(model_gold);
		price_gold_plate.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				gold_sashimi_price = (double)(spinner.getValue());
			}
		});
		
		String[] sushi_list = {"Choose sushi type", "eel", "crab", "yellowtail", "shrimp", "tuna"};
		JComboBox<String> choose_type = new JComboBox<String>(sushi_list);
		choose_type.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> source = (JComboBox<String>) e.getSource();
				String str = (String) source.getSelectedItem();
				switch(str) {
				case("tuna"):
					sahimi_type = Sashimi.SashimiType.TUNA;
					break;
				case("crab"):
					sahimi_type = Sashimi.SashimiType.CRAB;
					break;
				case("eel"):
					sahimi_type = Sashimi.SashimiType.EEL;
					break;
				case("yellowtail"):
					sahimi_type = Sashimi.SashimiType.YELLOWTAIL;
					break;
				case("shrimp"):
					sahimi_type = Sashimi.SashimiType.SHRIMP;
					break;
				
				}
			}	
		});
		
		String[] plateColors = {"Pick a plate type", "red", "green", "blue", "gold"};
		JComboBox<String> selectPlate = new JComboBox<String>(plateColors);
		selectPlate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> source = (JComboBox<String>) e.getSource();
				String str = (String) source.getSelectedItem();
				switch(str) {
				case("red"):
					sashimi_color = Plate.Color.RED;
					label_gold.setVisible(false);
					price_gold_plate.setVisible(false);
					break;
				case("green"):
					sashimi_color = Plate.Color.GREEN;
					label_gold.setVisible(false);
					price_gold_plate.setVisible(false);
					break;
				case("blue"):
					sashimi_color = Plate.Color.BLUE;
					label_gold.setVisible(false);
					price_gold_plate.setVisible(false);
					break;
				case("gold"): 
					sashimi_color = Plate.Color.GOLD;
					label_gold.setVisible(true);
					price_gold_plate.setVisible(true);
					break;
				}
			}
		});
		
		JLabel labels = new JLabel("Enter the position of plate: ");
		SpinnerModel model = new SpinnerNumberModel(0, 0, belt_size, 1);
		JSpinner choose_position = new JSpinner(model);
		choose_position.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				sashimi_position = (int)(spinner.getValue());
			}
		});
		
		JButton makePlate = new JButton("Click to make Plate!");
		makePlate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sashimi_color == Plate.Color.GOLD) {
					placeSashimi(sashimi_color, sashimi_position, sahimi_type, gold_sashimi_price);
				} else {
					placeSashimi(sashimi_color, sashimi_position, sahimi_type);
				}
				
				sashimi_color = null;
				sashimi_position = 0;
				sahimi_type = null;
				gold_sashimi_price = 5.0;
				
				framer.dispose();
			}
		});
		
		panel.add(choose_type);
		panel.add(labels);
		panel.add(choose_position);
		panel.add(selectPlate);
		label_gold.setVisible(false);
		panel.add(label_gold);
		price_gold_plate.setVisible(false);
		panel.add(price_gold_plate);
		panel.add(makePlate);
		framer.add(panel);
		framer.setSize(420, 320);
		framer.setVisible(true);
		
	}
	
	public void placeSashimi(Plate.Color color, int pos, Sashimi.SashimiType type) {
		if(color == Plate.Color.RED) {
			makeRedPlateRequest(new Sashimi(type), pos);
		}
		if(color == Plate.Color.BLUE) {
			makeBluePlateRequest(new Sashimi(type), pos);
		}
		if(color == Plate.Color.GREEN) {
			makeGreenPlateRequest(new Sashimi(type), pos);
		}
	}
	
	public void placeSashimi(Plate.Color color, int pos, Sashimi.SashimiType type, double price) {
		makeGoldPlateRequest(new Sashimi(type), pos, price);
	}
	
	public void makeNigiri() {
		JFrame frame = new JFrame("Create your Nigiri");
		JPanel panel = new JPanel(new GridLayout(0, 1));
		
		JLabel label_gold = new JLabel("Enter Plate's Price: ");
		SpinnerModel goldModel = new SpinnerNumberModel(5.0, 5.0, 10.0, .01);
		JSpinner goldPlatePrice = new JSpinner(goldModel);
		goldPlatePrice.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				gold_nigiri_price = (double)(spinner.getValue());
			}
		});
		
		String[] sushiTypes = {"Choose sushi type", "eel", "crab", "yellowtail", "shrimp", "tuna"};
		JComboBox<String> selectType = new JComboBox<String>(sushiTypes);
		selectType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> source = (JComboBox<String>) e.getSource();
				String temp = (String) source.getSelectedItem();
				switch(temp) {
				case("eel"):
					nigiri_type = Nigiri.NigiriType.EEL;
					break;
				case("crab"):
					nigiri_type = Nigiri.NigiriType.CRAB;
					break;
				case("yellowtail"):
					nigiri_type = Nigiri.NigiriType.YELLOWTAIL;
					break;
				case("shrimp"):
					nigiri_type = Nigiri.NigiriType.SHRIMP;
					break;
				case("tuna"):
					nigiri_type = Nigiri.NigiriType.TUNA;
					break;
				}
			}	
		});
		
		String[] plateColors = {"Choose plate type", "red", "green", "blue", "gold"};
		JComboBox<String> selectPlate = new JComboBox<String>(plateColors);
		selectPlate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> source = (JComboBox<String>) e.getSource();
				String temp = (String) source.getSelectedItem();
				switch(temp) {
				case("red"):
					nigiri_color = Plate.Color.RED;
					label_gold.setVisible(false);
					goldPlatePrice.setVisible(false);
					break;
				case("green"):
					nigiri_color = Plate.Color.GREEN;
					label_gold.setVisible(false);
					goldPlatePrice.setVisible(false);
					break;
				case("blue"):
					nigiri_color = Plate.Color.BLUE;
					label_gold.setVisible(false);
					goldPlatePrice.setVisible(false);
					break;
				case("gold"): 
					nigiri_color = Plate.Color.GOLD;
					label_gold.setVisible(true);
					goldPlatePrice.setVisible(true);
					break;
				}
			}
		});
		
		JLabel label = new JLabel("Enter the position of plate: ");
		SpinnerModel model = new SpinnerNumberModel(0, 0, belt_size, 1);
		JSpinner selectPosition = new JSpinner(model);
		selectPosition.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				nigiri_position = (int)(spinner.getValue());
			}
		});
		
		JButton makePlate = new JButton("Click to make Plate!");
		makePlate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nigiri_color == Plate.Color.GOLD) {
					makeNigiri(nigiri_color, nigiri_position, nigiri_type, gold_nigiri_price);
				} else {
					makeNigiri(nigiri_color, nigiri_position, nigiri_type);
				}
				
				nigiri_color = null;
				nigiri_position = 0;
				nigiri_type = null;
				gold_nigiri_price = 5.0;
				
				frame.dispose();
			}
		});
		
		panel.add(selectType);
		panel.add(label);
		panel.add(selectPosition);
		panel.add(selectPlate);
		label_gold.setVisible(false);
		panel.add(label_gold);
		goldPlatePrice.setVisible(false);
		panel.add(goldPlatePrice);
		panel.add(makePlate);
		frame.add(panel);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}
	
	public void makeNigiri(Plate.Color color, int pos, Nigiri.NigiriType type) {
		if(color == Plate.Color.RED) {
			makeRedPlateRequest(new Nigiri(type), pos);
		}
		if(color == Plate.Color.BLUE) {
			makeBluePlateRequest(new Nigiri(type), pos);
		}
		if(color == Plate.Color.GREEN) {
			makeGreenPlateRequest(new Nigiri(type), pos);
		}
	}	
	
	public void makeNigiri(Plate.Color color, int pos, Nigiri.NigiriType type, double price) {
		makeGoldPlateRequest(new Nigiri(type), pos, price);
	}
	
	public void makeRoll() {
		
		String roll_name = "Player Roll";
		
		JFrame framer = new JFrame("Create your Roll");
		JPanel panel = new JPanel(new GridLayout(0, 1));
		
		JLabel goldLabel = new JLabel("Enter the position of plate: ");
		SpinnerModel goldModel = new SpinnerNumberModel(5.0, 5.0, 10.0, .01);
		JSpinner goldPlatePrice = new JSpinner(goldModel);
		goldPlatePrice.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				gold_rollPrice = (double)(spinner.getValue());
			}
		});
		
		JLabel labelAvocado = new JLabel("Enter avocado amount: ");
		SpinnerModel modelAv = new SpinnerNumberModel(0, 0, 1.5, .1);
		JSpinner avocadoAmount = new JSpinner(modelAv);
		avocadoAmount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				roll_avocado = (double)(spinner.getValue());
			}
		});
		
		JLabel labelSeaweed = new JLabel("Enter seaweed amount");
		SpinnerModel modelSea = new SpinnerNumberModel(0, 0, 1.5, .1);
		JSpinner seaweedAmount = new JSpinner(modelSea);
		seaweedAmount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				roll_seaweed = (double)(spinner.getValue());
			}
		});
		
		JLabel labelRice = new JLabel("Enter rice amount");
		SpinnerModel modelRice = new SpinnerNumberModel(0, 0, 1.5, .1);
		JSpinner riceAmount = new JSpinner(modelRice);
		riceAmount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				roll_rice = (double)(spinner.getValue());
			}
		});
		
		JLabel labelEel = new JLabel("Enter eel amount");
		SpinnerModel modelEel = new SpinnerNumberModel(0, 0, 1.5, .1);
		JSpinner eelAmount = new JSpinner(modelEel);
		eelAmount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				roll_eel = (double)(spinner.getValue());
			}
		});
		
		JLabel labelCrab = new JLabel("Enter crab amount");
		SpinnerModel modelCrab = new SpinnerNumberModel(0, 0, 1.5, .1);
		JSpinner crabAmount = new JSpinner(modelCrab);
		crabAmount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				roll_crab = (double)(spinner.getValue());
			}
		});
		
		JLabel labelYellowtail = new JLabel("Enter yellowtail amount");
		SpinnerModel modelSal = new SpinnerNumberModel(0, 0, 1.5, .1);
		JSpinner yellowtailAmount = new JSpinner(modelSal);
		yellowtailAmount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				roll_yellowtail = (double)(spinner.getValue());
			}
		});
		
		JLabel labelShrimp = new JLabel("Enter shrimp amount");
		SpinnerModel modelShrimp = new SpinnerNumberModel(0, 0, 1.5, .1);
		JSpinner shrimpAmount = new JSpinner(modelShrimp);
		shrimpAmount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				roll_shrimp = (double)(spinner.getValue());
			}
		});
		
		JLabel labelTuna = new JLabel("Enter tuna amount");
		SpinnerModel modelTuna = new SpinnerNumberModel(0, 0, 1.5, .1);
		JSpinner tunaAmount = new JSpinner(modelTuna);
		tunaAmount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				roll_tuna = (double)(spinner.getValue());
			}
		});
		
		String[] plateColors = {"Choose plate type", "red", "green", "blue", "gold"};
		JComboBox<String> selectPlate = new JComboBox<String>(plateColors);
		selectPlate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> source = (JComboBox<String>) e.getSource();
				String temp = (String) source.getSelectedItem();
				switch(temp) {
				case("red"):
					roll_color = Plate.Color.RED;
					goldLabel.setVisible(false);
					goldPlatePrice.setVisible(false);
					break;
				case("green"):
					roll_color = Plate.Color.GREEN;
					goldLabel.setVisible(false);
					goldPlatePrice.setVisible(false);
					break;
				case("blue"):
					roll_color = Plate.Color.BLUE;
					goldLabel.setVisible(false);
					goldPlatePrice.setVisible(false);
					break;
				case("gold"): 
					roll_color = Plate.Color.GOLD;
					goldLabel.setVisible(true);
					goldPlatePrice.setVisible(true);
					break;
				}
			}
		});
		
		JLabel label = new JLabel("Enter the position of plate: ");
		SpinnerModel model = new SpinnerNumberModel(0, 0, belt_size, 1);
		JSpinner selectPosition = new JSpinner(model);
		selectPosition.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				roll_position = (int)(spinner.getValue());
			}
		});
		
		JButton makePlate = new JButton("Click to make Plate!");
		makePlate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(roll_avocado > 0) {
					roll_portion[0] = new AvocadoPortion(roll_avocado);
				}
				
				if(roll_crab > 0) {
					roll_portion[1] = new CrabPortion(roll_crab);
				}
				
				if(roll_eel > 0) {
					roll_portion[2] = new EelPortion(roll_eel);
				}
		
				if(roll_rice > 0) {
					roll_portion[3] = new RicePortion(roll_rice);
				}
	
				if(roll_yellowtail > 0) {
					roll_portion[4] = new YellowtailPortion(roll_yellowtail);
				}
	
				if(roll_shrimp > 0) {
					roll_portion[5] = new ShrimpPortion(roll_shrimp);
				}
		
				if(roll_seaweed > 0) {
					roll_portion[6] = new SeaweedPortion(roll_seaweed);
				}

				if(roll_tuna > 0) {
					roll_portion[7] = new TunaPortion(roll_tuna);
				}
		
				
				List<IngredientPortion> ingsList = new ArrayList<IngredientPortion>();
				for(int i = 0; i < roll_portion.length; i++) {
					if(roll_portion[i] != null && roll_portion[i].getAmount() > 0) {
						ingsList.add(roll_portion[i]);
					}
				}
				IngredientPortion[] newIngs = ingsList.toArray(new IngredientPortion[ingsList.size()]);
				
				if(roll_color == Plate.Color.GOLD) {
					placeRoll(newIngs, roll_name, roll_color, roll_position, gold_rollPrice);
				} else {
					placeRoll(newIngs, roll_name, roll_color, roll_position);
				}
				
				roll_avocado = 0;
				roll_crab = 0;
				roll_eel = 0;
				roll_rice = 0;
				roll_yellowtail = 0;
				roll_seaweed = 0;
				roll_shrimp = 0;
				roll_tuna = 0;
				gold_rollPrice = 5.0;
				roll_position = 0;
				roll_color = null;
				Arrays.fill(roll_portion, null);
				
				framer.dispose();
			}
		});
		
		panel.add(labelAvocado);
		panel.add(avocadoAmount);
		panel.add(labelSeaweed);
		panel.add(seaweedAmount);
		panel.add(labelRice);
		panel.add(riceAmount);
		panel.add(labelEel);
		panel.add(eelAmount);
		panel.add(labelCrab);
		panel.add(crabAmount);
		panel.add(labelYellowtail);
		panel.add(yellowtailAmount);
		panel.add(labelShrimp);
		panel.add(shrimpAmount);
		panel.add(labelTuna);
		panel.add(tunaAmount);
		panel.add(label);
		panel.add(selectPosition);
		panel.add(selectPlate);
		goldLabel.setVisible(false);
		panel.add(goldLabel);
		goldPlatePrice.setVisible(false);
		panel.add(goldPlatePrice);
		panel.add(makePlate);
		framer.add(panel);
		framer.setSize(450, 550);
		framer.setVisible(true);
			
	}
	public void placeRoll(IngredientPortion[] ings, String name, Plate.Color color, int pos) {
		if(color == Plate.Color.BLUE) {
			makeBluePlateRequest(new Roll(name, ings), pos);
		}
		if(color == Plate.Color.RED) {
			makeRedPlateRequest(new Roll(name, ings), pos);
		}
		if(color == Plate.Color.GREEN) {
			makeGreenPlateRequest(new Roll(name, ings), pos);
		}	
	}
	
	public void placeRoll(IngredientPortion[] ings, String name, Plate.Color color, int pos, double price) {	
		makeGoldPlateRequest(new Roll(name, ings), pos, price);	
	}
}
