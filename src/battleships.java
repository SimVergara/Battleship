
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class battleships{
	
	private JFrame f = new JFrame("Battleships Game");
	private JButton boxs1[][];
	private JButton boxs2[][];
	private JButton doneButton;
	private JButton resetButton;
//	private JButton nextButton;
	private JButton shiftButton;
	private thehandler events;
	private int grids = 10;
	private int gridSize = 30;
	private Color origin = Color.WHITE;
	private Color place = Color.GREEN;
	private Color miss = Color.BLUE;
	private Color hit = Color.RED;
	
	
	
	public battleships(){
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(960,600);
		f.setLayout(new BorderLayout(3,3));
		events = new thehandler();
		
		// game table
		boxs1 = new JButton[grids][grids];
		boxs2 = new JButton[grids][grids];
		
		for(int i = 0; i < grids; i++){
			for (int j = 0; j< grids; j++){
				
				boxs1[i][j] = new JButton();
				boxs1[i][j].setSize(gridSize,gridSize);
				boxs1[i][j].setLocation(i*gridSize+60, j*gridSize+100);
				boxs1[i][j].addActionListener(events);
				boxs1[i][j].addMouseListener(events);
				boxs1[i][j].setBackground(origin);
				
				
				boxs2[i][j] = new JButton();
				boxs2[i][j].setSize(gridSize,gridSize);
				boxs2[i][j].setLocation(i*gridSize+600, j*gridSize+100);
				boxs2[i][j].addActionListener(events);
				boxs2[i][j].addMouseListener(events);
				boxs2[i][j].setBackground(origin);
				
				
				f.add(boxs1[i][j]);
				f.add(boxs2[i][j]);

			}
		}
		
		
		//shift button
		shiftButton = new JButton("Shift");
		shiftButton.setSize(70,30);
		shiftButton.setLocation(60, gridSize+450);
		shiftButton.addActionListener(events);
		
		//done button
		doneButton = new JButton("Done");
		doneButton.setSize(70,30);
		doneButton.setLocation(gridSize+150, gridSize+450);
		doneButton.addActionListener(events);
		
		//rest button
		resetButton = new JButton("Reset");
		resetButton.setSize(70,30);
		resetButton.setLocation(gridSize+800, gridSize+450);
		resetButton.addActionListener(events);
				
		JLabel user = new JLabel("User");
		user.setFont(new Font("Serif", Font.BOLD, 20));
		user.setBounds(190, 10, 100, 100);
		
		JLabel computer = new JLabel("Computer");
		computer.setFont(new Font("Serif", Font.BOLD, 20));
		computer.setBounds(715, 10, 100, 100);
		
		f.setLayout(null);
		f.add(doneButton);
		f.add(shiftButton);
		f.add(resetButton);
		f.add(user);
		f.add(computer);
		f.setVisible(true);
		
	}
	public class thehandler implements ActionListener, MouseListener{
		
		int done = 0;
		int shift = 0;
		int good = 0;
		int bad = 1;
		int horizontal = 0;
		int vertical = 1;
		String ships = "Aircraft";
		int n = 0;
		
		public void actionPerformed(ActionEvent e){
			
		
		
			//User panel
				switch (ships){
				
				case "Aircraft":
					for(int i = 0; i < grids; i++){
						for (int j = 0; j< grids; j++){
							if (e.getSource() == boxs1[i][j]){
								ships = "Battleship";
							}
						}
					}
					break;
						
					case "Battleship":
						for(int i = 0; i < grids; i++){
							for (int j = 0; j< grids; j++){
								if (e.getSource() == boxs1[i][j]){
									ships = "Cruiser";
								}
							}
						}
						break;
							
					case "Cruiser":
						for(int i = 0; i < grids; i++){
							for (int j = 0; j< grids; j++){
								if (e.getSource() == boxs1[i][j]){
									ships = "Destroyer";
								}
							}
						}
						break;
						
					case "Destroyer":
						for(int i = 0; i < grids; i++){
							for (int j = 0; j< grids; j++){
								if (e.getSource() == boxs1[i][j]){
									if(n != 1){
										ships = "Destroyer";
										n++;
									}
									else {
										ships = "Submarine";
										n = 0;
									}
								}
							}
						}
						break;
						
					case "Submarine":
						for(int i = 0; i < grids; i++){
							for (int j = 0; j< grids; j++){
								if (e.getSource() == boxs1[i][j]){
									if ( n!= 1 ){
										ships = "Submarine";
										n++;
									}
									else{
										ships = "Aircraft";
									}
									
								}
							}
						}
						break;
					}
					
			
		
		
			//Computer panel
			for(int i = 0; i < grids; i++){
				for (int j = 0; j< grids; j++){
					if (e.getSource() == boxs2[i][j]){
						boxs2[i][j].setBackground(miss);
						boxs2[i][j].setOpaque(true);
						boxs2[i][j].setBorderPainted(true);
						boxs2[i][j].setEnabled(false);
						f.add(boxs2[i][j]);
					}	
				}
			}
		
		
			//done button action
			if (e.getSource() == doneButton){
				if (done == 0){
					panelBreak(boxs1,true);
					done = 1;
					doneButton.setText("Stop");
				}
				else if (done == 1){
					panelBreak(boxs1,false);
					done = 0;
					doneButton.setText("Done");
				}
				
			}
		
		
			if (e.getSource() == shiftButton){
				if (shift == horizontal){
					shift = vertical;
				}
				else {
					shift = horizontal;
				}
			}
			
		
			//reset button action
			else if (e.getSource() == resetButton){
				for(int i = 0; i < grids; i++){
					for (int j = 0; j< grids; j++){
						boxs2[i][j].setBackground(origin);
						boxs2[i][j].setOpaque(true);
						boxs2[i][j].setBorderPainted(true);
						boxs2[i][j].setEnabled(true);
						f.add(boxs2[i][j]);
					}	
				}
			}
		}

		// to close user panel
		private void panelBreak(JButton[][] button, boolean a){
			if (a == true){
				for(int i = 0; i < 10; i++){
					for (int j = 0; j < 10; j++){
						button[i][j].setEnabled(false);
						f.add(button[i][j]);
					}	
				}
			}
			else if (a == false){
				for(int i = 0; i < 10; i++){
					for (int j = 0; j < 10; j++){
						button[i][j].setEnabled(true);
						f.add(button[i][j]);
					}
				}
			}
		}
		


		public void mouseClicked(MouseEvent e) { }
		
		public void mousePressed(MouseEvent e) { }
		
		public void mouseReleased(MouseEvent e) { }

		
		public void mouseEntered(MouseEvent e) {
			if(done == 0){
				switch (ships){
				case "Aircraft":
					aircraftPlace(e,boxs1,good);
				case "Battleship":
					battleshipPlace(e,boxs1,good);
				case "Cruiser":
					cruiserPlace(e,boxs1,good);
				case "Destroyer":
					destroyerPlace(e,boxs1,good);
				case "Submarine":
					submarinePlace(e,boxs1,good);
				}
			}
		}
		

		public void mouseExited(MouseEvent e) {
			if (done == 0){
				switch (ships){
				case "Aircraft":
					aircraftPlace(e,boxs1,bad);
				case "Battleship":
					battleshipPlace(e,boxs1,bad);
				case "Cruiser":
					cruiserPlace(e,boxs1,bad);
				case "Destroyer":
					destroyerPlace(e,boxs1,bad);
				case "Submarine":
					submarinePlace(e,boxs1,bad);
				}
			}
		}
		
		
		// place aircraft Carrier
		public void aircraftPlace (MouseEvent e, JButton button[][], int decide ) throws ArrayIndexOutOfBoundsException{
			
			if(decide == 0){
				for(int i = 0; i < grids; i++){
					for (int j = 0; j< grids; j++){
						if (e.getSource() == button[i][j]){
							try{
								switch (shift){
								case 0:
									button[i][j].setBackground(place);
									button[i+1][j].setBackground(place);
									button[i+2][j].setBackground(place);
									button[i+3][j].setBackground(place);
									button[i+4][j].setBackground(place);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								case 1:
									button[i][j].setBackground(place);
									button[i][j+1].setBackground(place);
									button[i][j+2].setBackground(place);
									button[i][j+3].setBackground(place);
									button[i][j+4].setBackground(place);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								}
							}
							catch (ArrayIndexOutOfBoundsException aio){
								panelBreak(button,true);
							}
						}
					}
				}
			}
			else if(decide == 1){
				for(int i = 0; i < grids; i++){
					for (int j = 0; j < grids; j++){
						if (e.getSource() == button[i][j]){
							try{
								switch(shift){
								case 0:
									button[i][j].setBackground(origin);
									button[i+1][j].setBackground(origin);
									button[i+2][j].setBackground(origin);
									button[i+3][j].setBackground(origin);
									button[i+4][j].setBackground(origin);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								case 1:
									button[i][j].setBackground(origin);
									button[i][j+1].setBackground(origin);
									button[i][j+2].setBackground(origin);
									button[i][j+3].setBackground(origin);
									button[i][j+4].setBackground(origin);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								}
							}
							catch( ArrayIndexOutOfBoundsException aio){
								panelBreak(button,false);
							}
							
						}
					}	
				}
			}
		}
		
		//place battleship
		public void battleshipPlace (MouseEvent e, JButton button[][], int decide ) throws ArrayIndexOutOfBoundsException{
		
			
			if(decide == 0){
				for(int i = 0; i < grids; i++){
					for (int j = 0; j< grids; j++){
						if (e.getSource() == button[i][j]){
							try{
								switch (shift){
								case 0:
									button[i][j].setBackground(place);
									button[i+1][j].setBackground(place);
									button[i+2][j].setBackground(place);
									button[i+3][j].setBackground(place);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								case 1:
									button[i][j].setBackground(place);
									button[i][j+1].setBackground(place);
									button[i][j+2].setBackground(place);
									button[i][j+3].setBackground(place);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								}
							}
							catch (ArrayIndexOutOfBoundsException aio){
								panelBreak(button,true);
							}
						}
					}
				}
			}
			else if(decide == 1){
				for(int i = 0; i < grids; i++){
					for (int j = 0; j < grids; j++){
						if (e.getSource() == button[i][j]){
							try{
								switch(shift){
								case 0:
									button[i][j].setBackground(origin);
									button[i+1][j].setBackground(origin);
									button[i+2][j].setBackground(origin);
									button[i+3][j].setBackground(origin);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								case 1:
									button[i][j].setBackground(origin);
									button[i][j+1].setBackground(origin);
									button[i][j+2].setBackground(origin);
									button[i][j+3].setBackground(origin);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								}
							}
							catch( ArrayIndexOutOfBoundsException aio){
								panelBreak(button,false);
							}
							
						}
					}	
				}
			}
		}
	
	//place cruiser
	public void cruiserPlace (MouseEvent e, JButton button[][], int decide ) throws ArrayIndexOutOfBoundsException{
			
			if(decide == 0){
				for(int i = 0; i < grids; i++){
					for (int j = 0; j< grids; j++){
						if (e.getSource() == button[i][j]){
							try{
								switch (shift){
								case 0:
									button[i][j].setBackground(place);
									button[i+1][j].setBackground(place);
									button[i+2][j].setBackground(place);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								case 1:
									button[i][j].setBackground(place);
									button[i][j+1].setBackground(place);
									button[i][j+2].setBackground(place);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								}
							}
							catch (ArrayIndexOutOfBoundsException aio){
								panelBreak(button,true);
							}
						}
					}
				}
			}
			else if(decide == 1){
				for(int i = 0; i < grids; i++){
					for (int j = 0; j < grids; j++){
						if (e.getSource() == button[i][j]){
							try{
								switch(shift){
								case 0:
									button[i][j].setBackground(origin);
									button[i+1][j].setBackground(origin);
									button[i+2][j].setBackground(origin);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								case 1:
									button[i][j].setBackground(origin);
									button[i][j+1].setBackground(origin);
									button[i][j+2].setBackground(origin);
									button[i][j].setOpaque(true);
									button[i][j].setBorderPainted(true);
									f.add(button[i][j]);
									break;
								}
							}
							catch( ArrayIndexOutOfBoundsException aio){
								panelBreak(button,false);
							}
							
						}
					}	
				}
			}
		}
	
	
	//place Destroyer * 2
	public void destroyerPlace (MouseEvent e, JButton button[][], int decide ) throws ArrayIndexOutOfBoundsException{
		
		if(decide == 0){
			for(int i = 0; i < grids; i++){
				for (int j = 0; j< grids; j++){
					if (e.getSource() == button[i][j]){
						try{
							switch (shift){
							case 0:
								button[i][j].setBackground(place);
								button[i+1][j].setBackground(place);
								button[i][j].setOpaque(true);
								button[i][j].setBorderPainted(true);
								f.add(button[i][j]);
								break;
							case 1:
								button[i][j].setBackground(place);
								button[i][j+1].setBackground(place);
								button[i][j].setOpaque(true);
								button[i][j].setBorderPainted(true);
								f.add(button[i][j]);
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException aio){
							panelBreak(button,true);
						}
					}
				}
			}
		}
		else if(decide == 1){
			for(int i = 0; i < grids; i++){
				for (int j = 0; j < grids; j++){
					if (e.getSource() == button[i][j]){
						try{
							switch(shift){
							case 0:
								button[i][j].setBackground(origin);
								button[i+1][j].setBackground(origin);
								button[i][j].setOpaque(true);
								button[i][j].setBorderPainted(true);
								f.add(button[i][j]);
								break;
							case 1:
								button[i][j].setBackground(origin);
								button[i][j+1].setBackground(origin);
								button[i][j].setOpaque(true);
								button[i][j].setBorderPainted(true);
								f.add(button[i][j]);
								break;
							}
						}
						catch( ArrayIndexOutOfBoundsException aio){
							panelBreak(button,false);
						}
						
					}
				}	
			}
		}
	}
	
	//place Submarine * 2
	public void submarinePlace (MouseEvent e, JButton button[][], int decide ) throws ArrayIndexOutOfBoundsException{
		
		if(decide == 0){
			for(int i = 0; i < grids; i++){
				for (int j = 0; j< grids; j++){
					if (e.getSource() == button[i][j]){
						button[i][j].setBackground(place);
						button[i][j].setOpaque(true);
						button[i][j].setBorderPainted(true);
						f.add(button[i][j]);
					}
				}
			}
		}
		else if(decide == 1){
			for(int i = 0; i < grids; i++){
				for (int j = 0; j < grids; j++){
					if (e.getSource() == button[i][j]){
						button[i][j].setBackground(origin);
						button[i][j].setOpaque(true);
						button[i][j].setBorderPainted(true);
						f.add(button[i][j]);
						}
					}
				}	
			}
		}
	}

	public static void main(String[] args) {
		new battleships();
	}
		
}


