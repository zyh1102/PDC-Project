/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;

import javax.swing.JButton;

/**
 *
 * @author Administrator
 */
public class Seat extends JButton {
    private int row;
    private int column;
    private boolean selected = false;
    private boolean sold = false;
    public Seat(){

    }
    public Seat(String name){
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
    public void setSelected(boolean selected){
        this.selected = selected;
    }
    public boolean getSelected(){
        return this.selected;
    }
    public boolean getSold(){
        return this.sold;
    }
    public void setSold(boolean sold){
        this.sold = sold;
    }
}
