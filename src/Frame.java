
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


	public class Frame extends JFrame {
		
		
		public static JButton start;
		public static CardLayout cardLayout = new CardLayout();
		public static JPanel card, end;
		public static GamePanel game;
		
		
		public Frame (){
			
			setTitle("Rabbit Game");
			setSize(800, 800);
			setLocationRelativeTo(null);
			
			Image title = ResourceLoader.getImage("title.png");
			ImageIcon icon = new ImageIcon(title);
			
			card = new JPanel();
			card.setLayout(cardLayout);
				
			JPanel frontScreen = new CardPanel();
			
			start = new JButton(icon);
			
			start.setBackground(Color.white);
			start.addActionListener(new StartGame());
			
			frontScreen.add(start);
			card.add(frontScreen, "1");
			
			add(card);
			
		}
		
		public class StartGame implements ActionListener{	
			public void actionPerformed(ActionEvent e){
				game = new GamePanel();
				card.add(game, "2");
				cardLayout.show(card, "2");
			
				}
			}
		
		
		
		public static void main(String[] args) {
			
			Frame frame = new Frame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
		
		}


	}


	