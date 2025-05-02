/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_game;

/**
 *
 * @author Ayxan
 */
public abstract class Field {
    protected int pos;
    
    public Field(int pos)
    {
        this.pos = pos;
    }
    
    public int getPos()
    {
        return pos;
    }
    
    public abstract void stepOn(Player player);
}
