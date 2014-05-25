import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EndPanel extends JPanel {
	JLabel label, score;
	JButton playAgain;
	Image endGame;
	int x = 200;
	int y = 200;
	
	EndPanel(){
		
		JPanel container = new JPanel();
		container.setLayout(new FlowLayout());
		container.setSize(200, 200);
		container.setBackground(Color.white);
		
		setBackground(Color.white);
		setLayout(new BorderLayout());
		
		endGame = ResourceLoader.getImage("GameOver.png");
		score = new JLabel("Score: " + GamePanel.rabbitKilled );
		score.setFont(new Font("Score: " + GamePanel.rabbitKilled, Font.BOLD, 36));
		
		score.setBackground(Color.white);
		label = new JLabel(new ImageIcon(endGame));
		
		playAgain = new JButton("Play Again?");
		playAgain.setFont(new Font("Play Again?", Font.BOLD, 36));
		
		playAgain.setBackground(Color.white);
		
		
		playAgain.addActionListener(new StartGame());
		container.add(score);
		container.add(playAgain);
		add(label);
		add(container);
		
	}
	

	public class StartGame implements ActionListener{	
		public void actionPerformed(ActionEvent e){
			Frame.game = new GamePanel();
			Frame.card.add(Frame.game, "2");
			Frame.cardLayout.show(Frame.card, "2");
			
			
			}
		}
}
