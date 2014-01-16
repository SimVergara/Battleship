import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class battleship extends JFrame {
	private JButton boxs1 = new JButton();
	private JButton boxs2 = new JButton();
	private JButton doneButton = new JButton("Done");
	private JButton resetButton = new JButton("Reset");


	private JPanel contentPane;
	private JTextField xaxis;
	private JTextField yaxis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					battleship frame = new battleship();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public battleship(){
		
		int grids = 10;
		int gridSize = 30;
		
		JFrame f = new JFrame("Battleships Game");
		f.setSize(1000,600);
		
		//thehandler events = new thehandler();
		
		// game table
		for(int i = 0; i < grids; i++){
			for (int j = 0; j< grids; j++){
				JButton boxs1 = new JButton();
				JButton boxs2 = new JButton();
				boxs1.setSize(gridSize ,gridSize );
				boxs2.setSize(gridSize ,gridSize );
				
				boxs1.setLocation(i*gridSize+60, j*gridSize+100);
				boxs2.setLocation(i*gridSize+600, j*gridSize+100);
				
				//boxs1.addActionListener(events);
				//boxs2.addActionListener(events);
				
				f.getContentPane().add(boxs1);
				f.getContentPane().add(boxs2);
				
			}
		}
		doneButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		//done button
		doneButton.setSize(70,30);
		doneButton.setLocation(60, gridSize+450);
		//doneButton.addActionListener(events);
		
		//rest button
		resetButton.setSize(70,30);
		resetButton.setLocation(gridSize+800, gridSize+450);
		//resetButton.addActionListener(events);
		
		JLabel user = new JLabel("User");
		user.setFont(new Font("Serif", Font.BOLD, 20));
		user.setBounds(190, 10, 100, 100);
		
		JLabel computer = new JLabel("Computer");
		computer.setFont(new Font("Serif", Font.BOLD, 20));
		computer.setBounds(715, 10, 100, 100);
		
		f.getContentPane().setLayout(null);
		f.getContentPane().add(doneButton);
		f.getContentPane().add(resetButton);
		f.getContentPane().add(user);
		f.getContentPane().add(computer);
		
		JButton hit = new JButton("Hit");
		hit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		hit.setBounds(460, 492, 100, 29);
		f.getContentPane().add(hit);
		
		JButton rotate = new JButton("Rotate");
		rotate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		rotate.setBounds(460, 460, 100, 29);
		f.getContentPane().add(rotate);
		
		xaxis = new JTextField();
		xaxis.setBounds(440, 362, 134, 28);
		f.getContentPane().add(xaxis);
		xaxis.setColumns(10);
		
		yaxis = new JTextField();
		yaxis.setBounds(440, 410, 134, 28);
		f.getContentPane().add(yaxis);
		yaxis.setColumns(10);
		f.setVisible(true);
		
	}
}
