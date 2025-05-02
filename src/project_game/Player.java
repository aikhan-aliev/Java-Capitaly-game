/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_game;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Ayxan
 */
public abstract class Player {
    protected String name;
    protected int balance;
    protected List <PropertyField> ownedFields;
    protected int pos;
    
    
    public Player(String name)
    {
        this.name = name;
        this.balance = 10000;
        this.pos = 0;
        this.ownedFields = new ArrayList<>();
    }
    
    
    public String getName()
    {
        return name;
    }

    public int getBalance()
    {
        return balance;
    }
    public List<PropertyField> getOwnedFields()
    {
        return ownedFields;
    }
    public int getPos()
    {
        return pos;
    }
    public void movePosition(int step, int mapLength)
    {
        pos = (pos + step) % mapLength;
    }
    public boolean noMoney()
    {
        return balance < 0;
    }
    public void setZero()
    {
        if (noMoney())
        for (PropertyField property : ownedFields) {
            property.deletePlayer();
        }
        ownedFields.clear();
    }
    public void addProperty(PropertyField field)   
    {
        ownedFields.add(field);
    }
    public void addOrSub(int price)
    {
        balance += price;
    }
    
    public boolean mayBuy(int price)
    {
        return balance >= price;
    }
    
    public abstract boolean spendMoney(int price);
}
