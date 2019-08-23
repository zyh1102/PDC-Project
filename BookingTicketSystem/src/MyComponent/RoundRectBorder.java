/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;

/**
 *
 * @author Administrator
 */
public class RoundRectBorder implements Border{

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
         g.drawRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, 5, 5);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false; 
    }
    
}
