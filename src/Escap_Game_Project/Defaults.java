/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escap_Game_Project;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author Majid Hussain
 */

public class Defaults {

    public static ImageIcon recaleIcon(int posX, int posY, String path){
        ImageIcon icon = new ImageIcon(
                new ImageIcon(path).
                        getImage().
                        getScaledInstance(
                                posX, 
                                posY,
                                Image.SCALE_SMOOTH)
        );
        return icon;
    }

    public static String getpath(String name, String extension){
        return new File("").getAbsolutePath() + "\\src\\res\\"+ name +"." + extension;
    }
}
