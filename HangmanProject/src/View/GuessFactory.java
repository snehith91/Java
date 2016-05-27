/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Controller.Guess;

/**
 *
 * @author sony
 */
public class GuessFactory {
     public Guess getGuess(int guessno) {

        if (guessno == 6) {
            return new GuessNo6();
        } else if (guessno == 5) {
            return new GuessNo5();
        } else if (guessno == 4) {
            return new GuessNo4();
        } else if (guessno == 3) {
            return new GuessNo3();
        } else if (guessno == 2) {
            return new GuessNo2();
        } else if (guessno == 1) {
            return new GuessNo1();
        } else if (guessno == 0) {
            return new GuessNo0();
        }
        return null;
    }
    
}
