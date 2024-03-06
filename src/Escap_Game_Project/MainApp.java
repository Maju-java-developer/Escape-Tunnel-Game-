package Escap_Game_Project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class MainApp extends JFrame{

    public MainApp(){
        intiComponends();
    }
    
    public void intiComponends(){
        setTitle("Escap_Game_Project");
        setSize(1280, 720);
        setLayout(new GridLayout(1, 1));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        playerGenerator = new EscapeTunnelGameGenerator(getWidth(), getHeight());
        playerGenerator.setBackground(Color.BLACK);
        playerGenerator.intiialize();
        
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    playerGenerator.player.moveUp();
                    playerGenerator.updateTimer.start();
                }
                
                repaint();
                revalidate();
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        add(playerGenerator);
    }
    
    EscapeTunnelGameGenerator playerGenerator = null;
    
    public static void main(String[] args) {
        new MainApp().setVisible(true);
    }
}
