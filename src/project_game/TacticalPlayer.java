/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_game;

/**
 *
 * @author Ayxan
 */
public class TacticalPlayer extends Player {
    
    private int tacticalCount = 0;
            
            
    public TacticalPlayer(String name)
    {
        super(name);
    }
    
    @Override
    public boolean spendMoney(int price) {
    if (mayBuy(price))
    {
        tacticalCount += 1;
        if (tacticalCount % 2 != 0)
        {
            balance -= price;
            return true;
        }
        
    }
    return false;
}
}
