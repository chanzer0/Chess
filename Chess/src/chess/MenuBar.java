package chess;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	private ChessGUI gui;
	public MenuBar(ChessGUI gui) {
		this.gui = gui;
		populateMenuBar();
	}	

	private void populateMenuBar() {
		
		JMenu file = new JMenu("Games");
		
		JMenuItem restart = new JMenuItem("Restart");
		restart.addActionListener(gui);
		file.add(restart);
		
		JMenuItem classical = new JMenuItem("New Classical Game");
		classical.addActionListener(gui);
		file.add(classical);
		
		JMenuItem board960 = new JMenuItem("New 960 Game");
		board960.addActionListener(gui);
		file.add(board960);
		
		JMenu options = new JMenu("Options");
		JMenuItem cpu = new JMenuItem("CPU");
		JMenuItem user = new JMenuItem("User");
		cpu.addActionListener(gui);
		user.addActionListener(gui);
		
		options.add(cpu);
		options.add(user);
		 
		//submenu
		options.addSeparator();
		JMenu theme = new JMenu("Theme");
		options.add(theme);
		
		
		JMenuItem classic = new JMenuItem("Classic");
		classic.addActionListener(gui);
		theme.add(classic);
		
		JMenuItem cyclone = new JMenuItem("Cyclone");
		cyclone.addActionListener(gui);
		theme.add(cyclone);
		
		JMenuItem retro = new JMenuItem("Retro");
		retro.addActionListener(gui);
		theme.add(retro);
		
		JMenuItem cottonCandy = new JMenuItem("Cotton Candy");
		cottonCandy.addActionListener(gui);
		theme.add(cottonCandy);
		
		JMenuItem politics = new JMenuItem("Politics");
		politics.addActionListener(gui);
		theme.add(politics);
		
		JMenuItem seaside = new JMenuItem("SeaSide");
		seaside.addActionListener(gui);
		theme.add(seaside);
		
		JMenuItem celtic = new JMenuItem("Celtic");
		celtic.addActionListener(gui);
		theme.add(celtic);
		
		JMenuItem rose = new JMenuItem("Rose");
		rose.addActionListener(gui);
		theme.add(rose);
		
		this.add(options);

		this.add(file);
		
		
	}
}

