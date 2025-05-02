/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_game;

/**
 *
 * @author Ayxan
 */
public class ServiceField extends Field{
    private int price;
    
    public ServiceField(int pos, int price)
    {
        super(pos);
        this.price = price;
    }
    
    @Override
    public void stepOn(Player player) {
    player.addOrSub(-price);

    if (player.noMoney())
    {
        player.setZero();
    }
}

}
