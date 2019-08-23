/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class BannerPanel extends JPanel{
    ImageIcon []imageIcons;
    int index = 0;
    public BannerPanel(ImageIcon []imageIcons){
        this.index = 0;
        this.imageIcons = imageIcons;
    }
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(imageIcons[index%imageIcons.length].getImage(),0,0,this);
        index ++;
    }
    
}
