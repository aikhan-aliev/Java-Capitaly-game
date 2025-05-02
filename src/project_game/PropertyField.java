/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_game;

/**
 *
 * @author Ayxan
 */
public class PropertyField extends Field{
    private Player owner;
    private boolean isHouse;
    
    public PropertyField(int pos)
    {
        super(pos);
        this.owner = null;
        this.isHouse = false;
    }
    
    public int getLocation()
    {
        return pos;
    }
    
    public Player getOwner()
    {
        return owner;
    }
    public void setOwner(Player player)
    {
        this.owner = player;
    }
    public boolean getIsHouse()
    {
        return isHouse;
    }
    public void looseHouse()
    {
        this.isHouse = false;
    }
    public void deletePlayer()
    {
        this.owner = null;
        this.isHouse = false;
    }
    
    @Override
    public void stepOn(Player player)
    {
        if (owner == null) {
            if (player.spendMoney(1000))
            {
                owner = player;
                player.addProperty(this);
            }
        } else if (owner == player && !isHouse)
        {
            if (player.spendMoney(4000))
            {
                isHouse = true;
            }
        } else if (owner != null && owner != player)
        {
            int rent;
            if (isHouse)
            {
                rent = 2000;
            }
            else
            {
                rent = 500;
            }
            player.addOrSub(-rent);
            owner.addOrSub(rent);

            if (player.noMoney())
            {
                player.setZero();
            }
        }
    }
}
