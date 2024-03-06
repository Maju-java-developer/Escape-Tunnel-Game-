package Escap_Game_Project;

import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import processing.core.PVector;

public class Tunnels {
    
    PVector position;
    Dimension screenSize;
    Dimension size;
    
    ImageIcon tunnelIcon = new ImageIcon("");
    
    float tunnelsVelX;
    
    public Tunnels(){}
    public Tunnels(float tunnelsVelX, Dimension screenSize) {
        this.tunnelsVelX = tunnelsVelX;
        position = new PVector(screenSize.width, 0);
        size = new Dimension(70, RandomRange.randomInt(200, screenSize.height - 160));
        
        tunnelIcon = Defaults.rescaleIcon(size.width, size.height,
                new File("").getAbsolutePath() +"\\src\\res\\tunnels1.png"
        );
    }
    
    public void Tunnelsupdate(){
        position.x -= tunnelsVelX;
    }
    
}
