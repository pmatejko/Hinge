package Hinge;

import java.util.*;

public class Cemetery extends ArrayList<Card> {                // Po skonczonej rundzie sa na nim przechowywane wszystkie brazowe
                                                               // karty, ktore byly wystawione na polowie gracza
    /*-----------------------------------------
                      Methods
     ------------------------------------------*/

    public Card getCard(boolean realPlayer) {                                     // Wybor karty ze cmentarza
        Card choice;                                                              // mozliwy przy specjalnej
        if(realPlayer)                                                            // umiejetnosci medyka
            choice = remove(playerGetCardIndex());
        else
            choice = remove(computerGetCardIndex());

        return choice;
    }

    private int playerGetCardIndex(){                                                   // Wybor karty gdy gracz
        Scanner input = new Scanner(System.in);                                         // nie jest komputerem
        int choice = -1;

        System.out.println("Choose one card from cemetery to add. (type number)");
        while (choice < 1 || choice > size()) {

            for (int i = 1; i <= size(); i++) {
                System.out.println(i + ". " + (get(i - 1)).toEnhancedString());
            }

            if (input.hasNextInt())
                choice = input.nextInt();
            else
                input.next();

            if(choice < 1 && choice > size())
                System.out.println("Wrong argument. Choose one card from cemetery to add (type number)");
        }

        return choice - 1;
    }

    private int computerGetCardIndex(){                                             // Wybor karty gdy gracz
        Random rand = new Random(System.currentTimeMillis());                       // jest komputerem
        return rand.nextInt(size());
    }
}
