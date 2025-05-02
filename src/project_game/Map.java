/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_game;

import java.util.List;

/**
 *
 * @author Ayxan
 */
public class Map {
    private List <Field> fields;
    
    public Map(List <Field> fields)
    {
        this.fields = fields;
    }
    
    public Field getField(int pos)
    {
        return (fields.get(pos));
    }
    public int getSize()
    {
        return fields.size();
    }
}
