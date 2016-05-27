package Controller;

import Model.BeanClass;

public class BusinessLogic {

    int count2 = 0, count4 = 0, count5 = 0;

    public boolean isCharacterInWord(char character,BeanClass bean) {

        char word[] = bean.getRandomCharWord();
        count2 = 0;
        for (int k = 0; k < word.length; k++) {
            if (word[k] == character) {
                count2++;
                bean.setUserCorrectInputString(character, k);
            }
        }
        System.out.print("Word: ");
        for (int i = 0; i < bean.getRandomCharWord().length; i++) {
            if (bean.getUserCorrectInputString()[i] == '\0') {
                System.out.print("_ ");
            } else {
                System.out.print(bean.getUserCorrectInputString()[i]);
            }

        }
        System.out.println("\n");
        return count2 > 0;

    }

    public boolean distictCharacter(char distinctCharacter, BeanClass bean) {
        if (count4 == 0) {
            bean.setGuessLetter(distinctCharacter, count4);
            count4++;
        }
        for (int i = 0; i < count4; i++) {
            if (bean.getGuessLetter()[i] == distinctCharacter) {
                count5++;
            }
        }
        if (count5 == 0) {
            bean.setGuessLetter(distinctCharacter, count4);
            count4++;
        }
        count5 = 0;
        return true;
    }

}
