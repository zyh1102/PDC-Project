/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;

/**
 *
 * @author Administrator
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
public class RoundRactButton extends JButton  {
//    private Shape shape = null;
    // 离开时颜色
    public RoundRactButton(String s) {
        super(s);
        //addMouseListener(this);
        setContentAreaFilled(false);// 是否显示外围矩形区域 选否
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        //填充圆角矩形区域 也可以为其它的图形
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,
                20, 20);

        //必须放在最后 否则画不出来
        super.paintComponent(g);
    }
    public void paintBorder(Graphics g) {
        //画边界区域
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1,
                20, 20);

    }
}
