package View;

import java.util.Scanner;
import Model.BeanClass;
import Controller.*;

public class HangmanView {

    private int i, j,consider=0;

    public void display(BeanClass bean, Broker broker) {
        if (!bean.getIsWinner()) {
                    
            
            System.out.print("\nYou have guessed: ");
            
            i = bean.getGuessLetter().length;
            for (j = 0; j < i; j++) {
                if(bean.getGuessLetter()[j] != '\0')
                System.out.print(bean.getGuessLetter()[j] + ",");
            }
            System.out.println("\n");
            
            System.out.println("Word: ");
            System.out.println("You have "+bean.getNoOfGuessesLeft()+" guesses remaining");
            System.out.print("Guess a letter: ");
            Scanner input = new Scanner(System.in);
            char inputCharacter = input.next().charAt(0);
            boolean result = broker.placeOrders(inputCharacter,bean);
            if(!result){
                bean.setNoOfGuessesLeft(bean.getNoOfGuessesLeft() - 1);
            }
            for(i=0;i<bean.getWordLength();i++){
            if(bean.getUserCorrectInputString()[i]!='\0'){
               consider++; 
            }}
            if(consider!=bean.getWordLength()){
                consider=0;
            }
            else{
                System.out.println("\nYou Win");
                bean.setIsWinner(true);
            }
           
            
            
            
            
            
            
        }
    }

}
