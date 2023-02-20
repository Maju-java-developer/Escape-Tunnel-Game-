package Escap_Game_Project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp extends JFrame{

    public MainApp(){
        intiComponends();
    }
    
    public void intiComponends(){
        setTitle("Pratice:");
        setSize(1280, 720);
        setLayout(new GridLayout(1, 1));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        playerGenerator = new Escap__Generator(getWidth(), getHeight());
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
    
    Escap__Generator playerGenerator = null;
    
    public static void main(String[] args) {
        new MainApp().setVisible(true);
    }
}
