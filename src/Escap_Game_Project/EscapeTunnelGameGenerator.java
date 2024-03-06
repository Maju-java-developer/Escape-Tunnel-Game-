package Escap_Game_Project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import processing.core.PVector;

public class EscapeTunnelGameGenerator extends JPanel{
    int width;
    int height;

    movingBackground movingBackground = null;
    Tunnels tunnels = null;
    Player player = null;
    
    GameOverDialog gameOverDialog = null;
    boolean isGameOver = true;
    
    ArrayList<Tunnels> tunnelsList = new ArrayList<Tunnels>();

    public EscapeTunnelGameGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void intiialize(){
        gameSetup();
        updateGame();
    }

    public void gameSetup(){
        gameOverDialog = new GameOverDialog(this);
        movingBackground = new movingBackground(1.0f, new Dimension(width, height));
        player = new Player(new Dimension(width, height));
        createTunnels();
    }

    public void createTunnels(){
        tunnels = new Tunnels(1.5f, new Dimension(width, height));
        tunnelsList.add(tunnels);
    }
    
    int TunnelsCreateTime = 135;
    
    public void updateNewTunnels(){
        TunnelsCreateTime --;
        
        if (TunnelsCreateTime < 0) {
            TunnelsCreateTime = 135;
            createTunnels();
        }
    }
    
    int delay = 10;
    Timer updateTimer = null;
    
    public void updateGame(){
        updateTimer = new Timer(delay, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                for (int i = 0; i < tunnelsList.size(); i++) {
                    tunnelsList.get(i).tunnelsUpdate();

                    // If My Player Ecsap Form Tunnels: 
                    if (tunnelsList.get(i).size.height <= player.size.width) {
                        Constants.score += Constants.scoreLimit;
                    }
                }
                
                player.update();
                movingBackground.update();
                updateNewTunnels();
                repaint();
                revalidate();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        Graphics2D graphics2D = (Graphics2D) g;

        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        graphics2D.addRenderingHints(hints);
        graphics2D.setRenderingHints(hints);
        
        // DrawImage For Background:
        graphics2D.drawImage(
                movingBackground.background.getImage(), 
                (int)movingBackground.position.x, 
                (int)movingBackground.position.y, 
                movingBackground.size.width, 
                movingBackground.size.height, 
                null
        );
        // DrawImage For Background:
        
        // Draw Rectangle For Tunnels:
        for (int i = 0; i < tunnelsList.size(); i++) {
            Rectangle2D tunnelsRec = new Rectangle.Float(
                    tunnelsList.get(i).position.x + 13, 
                    tunnelsList.get(i).position.y, 
                    tunnelsList.get(i).size.width - 30, 
                    tunnelsList.get(i).size.height
            );
            
            graphics2D.drawImage(
                    tunnelsList.get(i).tunnelIcon.getImage(), 
                    (int)tunnelsList.get(i).position.x, 
                    (int)tunnelsList.get(i).position.y, 
                    tunnelsList.get(i).size.width, 
                    tunnelsList.get(i).size.height,
                    null
            );
         
            if (tunnelsList.get(i).position.x < - 50) {
                Constants.score += Constants.scoreLimit;
                tunnelsList.remove(i);
            }
            
            graphics2D.setColor(Color.BLACK);
            graphics2D.setFont(new Font("Souge UI Light", 0, 18));
            graphics2D.drawString("Score: " +Constants.score, width - 200, 30);
                    
            // Draw Ellipse For MY Player:
            Ellipse2D playerEllipse = new Ellipse2D.Float(
                    player.position.x + 7, 
                    player.position.y, 
                    player.size.width - 5, 
                    player.size.height - 5
            );       
            
            graphics2D.drawImage(
                    player.playerIcon.getImage(), 
                    (int)player.position.x, 
                    (int)player.position.y, 
                    player.size.width, 
                    player.size.height,
                    null
            );
            // END MY PLAYER WORK:
            
            // Check If My Player Hit with Tunnels Then Happen Collision:
            if (playerEllipse.intersects(tunnelsRec) || player.position.y < 0 || player.position.y > height - 105) {
                if (isGameOver) {
                    gameOverDialog.setVisible(true);    
                    updateTimer.stop();
                    
                    Constants.killedTime += 1;
                    gameOverDialog.killTimeLbl.setText("" + Constants.killedTime);
                }
                
                isGameOver = false;
                GameOverAction();
            }
            
        }
        // END TUNNELS WORK:
        
        // Check If My UpdateTimer isStart Then Show This message OtherWise Don't Show It:
        if (!updateTimer.isRunning()) {
            graphics2D.setColor(Color.BLACK);
            graphics2D.setFont(new Font("Sogue UI Light", 0, 15));
            graphics2D.drawString("Click Space To Start Game:", player.position.x + 30, player.position.y + 50);
        }
        // END:
        
        // Display My Player Velectiy:
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("Souge UI Light", 20, 15));
        graphics2D.drawString("Velecity: " +player.velecity, 20, 20);
        graphics2D.drawString("Tunnels: " +tunnelsList.size(), 20, 60);
        // END:
    }
    
    public void GameOverAction(){
        gameOverDialog.closeBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        gameOverDialog.playAgainBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                player.position = new PVector(100, 300);
                gameOverDialog.dispose();
                isGameOver = true;
                tunnelsList.clear();
                createTunnels();
                
                TunnelsCreateTime = 135;
                repaint();
            }
        });
    }
}
