/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;
import java.util.ArrayList;
import java.util.List;
import Model.*;
import View.*;
/**
 *
 * @author sony
 */
public class Broker {
    private List<Logic> orderList = new ArrayList<Logic>(); 
    private boolean res=false;
    

   public void takeOrder(Logic logic){
      orderList.add(logic);		
   }

   public boolean placeOrders(char character, BeanClass bean){
      for (Logic logic : orderList) {
         res = logic.execute(character, bean);
         
      }
      return res;
   }
    
}
