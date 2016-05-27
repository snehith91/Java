/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author sony
 */
public class GenerateWord {

    public String randomWord() throws FileNotFoundException {
        ArrayList<String> map = new ArrayList<String>();

        String s = choose(new File("C:/Users/sony/Desktop/Words.txt"));

        if (!map.contains(s)) {
            map.add(s);
        }
        String word = map.get(0);
        System.out.println(word);
        return (word);
    }

    public static String choose(File f) throws FileNotFoundException {
        String result = null;
        Random rand = new Random();
        int n = 0;
        for (Scanner sc = new Scanner(f); sc.hasNext();) {
            ++n;
            String line = sc.nextLine();
            if (rand.nextInt(n) == 0) {
                result = line;
            }
        }

        return result;
    }

}
