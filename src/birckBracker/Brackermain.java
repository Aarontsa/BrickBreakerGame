package birckBracker;

import java.awt.Color;

import javax.swing.*;

public class Brackermain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setBounds(10,10,700,600);
//		frame.setSize(700,600);
//		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Birck Breaker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setBackground(Color.gray);
		
		Birckpanel panel = new Birckpanel();
		frame.add(panel);
	}

}
