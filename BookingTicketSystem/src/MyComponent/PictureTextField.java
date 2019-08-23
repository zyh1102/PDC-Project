/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;


import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class PictureTextField extends JTextField{
    private ImageIcon icon;
    public PictureTextField(String path){
        icon = new ImageIcon(path);
        Insets insets = new Insets(0,20,0,0);
        this.setMargin(insets);
    }
    
    @Override
    public void paintComponent(Graphics g){
        Insets insets = getInsets();
        super.paintComponent(g);
        int iconWidth = icon.getIconWidth();
        int iconHeight = icon.getIconHeight();
        int height = this.getHeight();
        icon.paintIcon(this, g, (insets.left - iconWidth) / 2, (height - iconHeight) / 2);
        
    }
}
