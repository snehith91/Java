package Controller;
import View.*;
import Model.*;
import java.io.FileNotFoundException;



public class Hangman {

    public static void main(String[] args) throws FileNotFoundException {
    HangmanView view = new HangmanView();
    BeanClass bean = BeanClass.getInstance();
    bean.setIsWinner(false);
    
    BusinessLogic abcStock = new BusinessLogic();

    CharacterInWord buyStockOrder = new CharacterInWord(abcStock);
    DistinctCharacter sellStockOrder = new DistinctCharacter(abcStock);

    Broker broker = new Broker();
    broker.takeOrder(sellStockOrder);
    broker.takeOrder(buyStockOrder);
    

   
    
    GenerateWord randomWordGeneration = new GenerateWord();
    bean.setRandomWord(randomWordGeneration.randomWord());
    char []word = bean.getRandomWord().toCharArray();
    String name = bean.getRandomWord();
    int length = name.length();
    bean.setWordLength(length);
    bean.setRandomCharWord(word);
    
    bean.setNoOfGuessesLeft(6);
    
    GuessFactory GF=new GuessFactory();


    
while(bean.getNoOfGuessesLeft()>=0 && !bean.getIsWinner()){

Guess g6=GF.getGuess(bean.getNoOfGuessesLeft());
g6.guess();
 
if (bean.getNoOfGuessesLeft() == 0) {
System.out.println("You Loose");
bean.setIsWinner(true);
}
view.display(bean,broker);

}

    
    
    
    }
    
}
