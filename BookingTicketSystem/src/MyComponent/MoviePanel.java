/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class MoviePanel extends JPanel{
    Image image = null;
    String icon;
    public MoviePanel(String icon){
        this.icon = icon;
    }
    public void paint(Graphics g){
            ImageIcon ico = new ImageIcon("./src/System/Images/movie.jpg");
            image = ico.getImage();
            g.drawImage(image,0,0,50,40,null);
    }
}
