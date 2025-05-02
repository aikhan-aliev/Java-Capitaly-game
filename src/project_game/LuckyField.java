/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_game;

/**
 *
 * @author Ayxan
 */
public class LuckyField extends Field{
    private int money;
    
    public LuckyField(int pos, int money)
    {
        super(pos);
        this.money = money;
    }
    
    @Override
    public void stepOn(Player player)
    {
        player.addOrSub(money);
    }
}
