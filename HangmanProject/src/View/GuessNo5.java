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
public class GuessNo5 implements Guess {

    private int i;

    @Override
    public void guess() {
        System.out.println("|---------");
                System.out.println("|        |");
                System.out.println("|        _");
                System.out.println("|       |_|");
                for (i = 0; i < 6; i++) {
                    System.out.println("|");
                }
    }

}
