/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class ButtonInTable extends JButton {
    private JFrame frame;
    private String type;
    private int row;
    private int column;
    private String username;
    public ButtonInTable(){

    }
    public ButtonInTable(String name){
        super(name);
    }

    public int getRow(){
        return row;
    }
    public void setRow(int row){
        this.row = row;
    }
    public int getColumn(){
        return column;
    }
    public void setColumn(int column){
        this.column = column;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setFrame(JFrame frame){
        this.frame = frame;
    }
    public JFrame getFrame(){
        return this.frame;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
        
}
