/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;
import Model.*;
import View.*;
/**
 *
 * @author sony
 */
public class DistinctCharacter implements Logic{
    private BusinessLogic abcStock;

    public DistinctCharacter(BusinessLogic abcStock) {
        this.abcStock = abcStock;
    }
    
    @Override
    public boolean execute(char inputCharacter, BeanClass bean){
        return abcStock.distictCharacter(inputCharacter, bean);
    }
}
