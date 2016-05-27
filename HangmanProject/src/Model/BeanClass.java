
package Model;

public class BeanClass {
    private static BeanClass instance = new BeanClass();
    private int wordLength;
    private int noOfGuessesLeft;
    private final char guessLetter[] = new char[26];
    private char userCorrectInputString[] = new char[26];

    private BeanClass(){}
    public static BeanClass getInstance(){
      return instance;
   }
    public boolean getIsWinner() {
        return isWinner;
    }

    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }
    private boolean isWinner;
    
    /*public BeanClass(){
        this.isWinner = false;
    }*/

    public char[] getUserCorrectInputString() {
        return userCorrectInputString;
    }

    public void setUserCorrectInputString(char correctGuessFromUser, int position) {
        this.userCorrectInputString[position] = correctGuessFromUser;
    }

    public char[] getRandomCharWord() {
        return randomCharWord;
    }

    public void setRandomCharWord(char[] randomCharWord) {
        this.randomCharWord = randomCharWord;
    }
    private char[] randomCharWord;

    public String getRandomWord() {
        return randomWord;
    }

    public void setRandomWord(String randomWord) {
        this.randomWord = randomWord;
    }
    private String randomWord;
    
    
    
    public char[] getGuessLetter() {
        return guessLetter;
    }

    public void setGuessLetter(char guessLetterFromUser, int position) {
        this.guessLetter[position] = guessLetterFromUser;
    }
    

    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    public int getNoOfGuessesLeft() {
        return noOfGuessesLeft;
    }

    public void setNoOfGuessesLeft(int noOfGuessesLeft) {
        this.noOfGuessesLeft = noOfGuessesLeft;
    }
   
}
