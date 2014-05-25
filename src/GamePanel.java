


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.*;


public class GamePanel extends CardPanel {

	private RazorRabbit rabbit1, rabbit2, rabbit3;

	public Image currentImage;
	public Image gunRR, gunRC, gunCC, gunLC, gunLL, bomb, smoke;
	
	public static int rabbitKilled;
	public int smokeX, smokeY;
	public int x, y;
	public int clickedX, clickedY;
	public int level, num2 = 1;
	public int elapsedSeconds;
	
	public static boolean bool = false;
	
	public Graphics g;
	
	public Timer timer3;
	
	GamePanel(){

        rabbit1 = new RazorRabbit();
        rabbit2 = new RazorRabbit();
        rabbit3 = new RazorRabbit();
		smoke = ResourceLoader.getImage("smoke.png");
		bomb = ResourceLoader.getImage("Bomb.png");
		gunRR = ResourceLoader.getImage("gunRR.jpg");
		gunRC = ResourceLoader.getImage("gunRC.jpg");
		gunCC = ResourceLoader.getImage("gunCC.jpg");
		gunLC = ResourceLoader.getImage("gunLC.jpg");
		gunLL = ResourceLoader.getImage("gunLL.jpg");
		currentImage = gunCC;

		timer3 = new Timer (1000, new TimerListener());
		
		addMouseListener(new ClickTarget());
		addMouseMotionListener(new MouseMovement());
		
		StartGame();
		}

    private void GameOver() {
        timer3.stop();
        rabbit1.EndMove();
        rabbit2.EndMove();
        rabbit3.EndMove();

        if (JOptionPane.showConfirmDialog(null, "Points: " + rabbitKilled + "\nPlay Again") == JOptionPane.YES_OPTION) {
            StartGame();
        }
        else {
            System.exit(0);
        }
    }

    private void StartGame() {
        rabbitKilled = 0;
        level = 0;
        elapsedSeconds = 20;
        rabbit1.StartMove();
        timer3.start();
    }
	
	class TimerListener implements ActionListener {
			
			public void actionPerformed(ActionEvent e){
				if (elapsedSeconds <= 0 ) {
				
                    GameOver();
                    HIghscore hs = new HIghscore(rabbitKilled);
                }
				else{
				    elapsedSeconds--;
					
			    }
		    }
	}
		
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawString("TARGET HIT: " + rabbitKilled,  10, 660);
		g.drawString("LEVEL: " + level, 690, 660);
		g.drawString("TIMER " + elapsedSeconds , 10, 680);

		rabbit1.DrawRabbit(g);
        drawSmoke(g, rabbit1);
        if(level > 2) {
            rabbit2.DrawRabbit(g);
            drawSmoke(g, rabbit2);
        }
        if(level > 4) {
            rabbit3.DrawRabbit(g);
            drawSmoke(g, rabbit3);
        }

        drawGunImage(g);

        repaint();


	}
	
	private class MouseMovement implements MouseMotionListener{
		public void mouseMoved(MouseEvent e){
			x = e.getX();
			y = e.getY();
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

    private  void drawSmoke(Graphics g, RazorRabbit rabbit) {
        if(bool == true)
            g.drawImage(smoke, smokeX, smokeY, null);
        if (rabbit.GetPosY() > -450)
            bool = false;
    }

    private void drawGunImage(Graphics g) {
        if (x < 175)
            currentImage = gunRR;

        if (x <350 && x >175)
            currentImage = gunLC;

        if (x <525 && x >350)
            currentImage = gunCC;

        if (x <700 && x >525)
            currentImage = gunRC;

        if (x <875 && x >700)
            currentImage = gunLL;

        g.drawImage(currentImage, 260, 490, null);
    }

    private boolean isHit(RazorRabbit rabbit, int posX, int posY) {
        if (clickedX < rabbit.GetPosX() + 130 && clickedX > rabbit.GetPosX() - 5){
            if (clickedY < rabbit.GetPosY() + 130 && clickedY > rabbit.GetPosY() - 5){
                return true;
            }
        }
        return false;
    }

    private void killRabbit(RazorRabbit rabbit) {
        rabbitKilled++;
        bool = true;
        smokeX = clickedX - 150;
        smokeY = clickedY - 80;
        rabbit.ResetPosition();

        if (rabbitKilled % 5 == 0 && rabbitKilled > num2){
            level++;
            elapsedSeconds += 10;
            rabbit.IncreaseMoveDistance(1);
            num2 += 3;
            if(level == 2) {
                rabbit2.StartMove();
            }
            else if(level == 4) {
                rabbit3.StartMove();
            }
        }
    }

		
	private class ClickTarget implements MouseListener{
		public void mouseClicked(MouseEvent e){
			clickedX = e.getX();
			clickedY = e.getY();
			
			if(isHit(rabbit1, clickedX, clickedY)) {
                killRabbit(rabbit1);
            }
            else if(isHit(rabbit2, clickedX, clickedY)) {
                killRabbit(rabbit2);
            }
            else if(isHit(rabbit3, clickedX, clickedY)) {
                killRabbit(rabbit3);
            }
		}

	
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    }
		
			
}
	

	