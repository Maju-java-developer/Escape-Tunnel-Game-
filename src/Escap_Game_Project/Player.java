package Escap_Game_Project;

import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import processing.core.PVector;

public class Player {

    PVector position;
    Dimension screenSize;
    Dimension size;
    
    float garavity = 0.5f;
    float velecity;
    float lift = -10;
    
    ImageIcon playerIcon = new ImageIcon("");
    
    public Player(){}
    public Player(Dimension screenSize){
        this.screenSize = screenSize;
        
        position = new PVector(100, 300);
        size = new Dimension(60, 60);
        
        playerIcon = Defaults.recaleIcon(size.width, size.height, 
                new File("").getAbsolutePath() + "\\src\\res\\angryBirds.png"
        );
    }
    
    public void update(){
        velecity += garavity;
        position.y += velecity;
        System.out.println("VeleCity: " + velecity);
    }
    
    public void moveUp(){
        velecity += lift;
        velecity *= 0.5f;
        System.out.println("VeleCity: " + velecity);
    }
    
}
