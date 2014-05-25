import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RazorRabbit {
    private Image imageOfRabbit;
    private int rabbitX, rabbitY, rabbitMove;
    private Timer timer;
    private Random rand = new Random();
    private boolean rabbitHit;

    public RazorRabbit() {
        imageOfRabbit = ResourceLoader.getImage("rabbit.png");
        timer = new Timer(5, new MoveRabbit());
    }

    public void StartMove() {
        rabbitHit = false;
        SetPosY(-160);
        SetMoveDistance(1);
        timer.start();
    }

    public void EndMove() {
        timer.stop();
        SetPosY(-160);
    }

    public void DrawRabbit(Graphics g) {
        g.drawImage(GetImage(), GetPosX(), GetPosY(), null);
    }

    public Image GetImage() {
        return imageOfRabbit;
    }

    public void SetPosX(int x) {
        rabbitX = x;
    }

    public int GetPosX() {
        return rabbitX;
    }

    public void SetPosY(int y) {
        rabbitY = y;
    }

    public int GetPosY() {
        return rabbitY;
    }

    public void ResetPosition() {
        SetPosX(rand.nextInt(8)*50);
        SetPosY(-500);
        rabbitHit = false;
    }

    public void RabbitHit() {
        rabbitHit = true;
    }

    public boolean IsHit() {
        return rabbitHit;
    }

    public void RabbitHitReset() {
        rabbitHit = false;
    }

    public void SetMoveDistance(int distance) {
        rabbitMove = distance;
    }

    public void IncreaseMoveDistance(int distance) {
        rabbitMove += distance;
    }

    public int GetMoveDistance() {
        return rabbitMove;
    }

    private class MoveRabbit implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            SetPosY(GetPosY() + GetMoveDistance());
            if (GetPosY() > 600) {
                ResetPosition();
            }
        }
    }
}