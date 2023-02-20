package Escap_Game_Project;

import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import processing.core.PVector;

public class movingBackground {

    PVector position = new PVector();
    Dimension size;
    Dimension screenSize;
    
    float velX;
    ImageIcon background = new ImageIcon("");

    public movingBackground() {}

    public movingBackground(float velX ,Dimension screenSize) {
        this.velX = velX;
        this.screenSize = screenSize;
        size = new Dimension(1280 * 2, screenSize.height);
        
        position = new PVector(0, 0);
        
        background = Defaults.recaleIcon(screenSize.width, screenSize.height, 
                new File("").getAbsolutePath()+ "\\src\\res\\Background.png"
        );
    }
    
    public void update(){
        position.x -= velX;

        if (position.x < -1300) {
            position.x = (0);
        }
    }
}
