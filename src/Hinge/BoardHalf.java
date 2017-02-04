package Hinge;

import java.util.ArrayList;

public class BoardHalf {                                        // Polowa planszy nalezaca do jednego z graczy

    /*-----------------------------------------
                     Variables
     ------------------------------------------*/

    private int lives;
    private boolean pass;
    private Line[] lines;
    private boolean[] lineUpgrade;
    private boolean[] lineDowngrade;

    /*-----------------------------------------
                    Constructor
     ------------------------------------------*/

    public BoardHalf(){
        lives = 2;                                              // Kazdy gracz zaczyna z dwoma zyciami (best of 3)
        pass = false;
        lineUpgrade = new boolean[3];
        lineDowngrade = new boolean[3];
        lines = new Line[3];
        for (int i = 0; i < 3; i++) {
            lines[i] = new Line();
            lineUpgrade[i] = false;
            lineDowngrade[i] = false;
        }
    }

    /*-----------------------------------------
                      Getters
     ------------------------------------------*/

    public int getLives(){
        return lives;
    }

    public boolean getPass(){
        return pass;
    }

    public int getLinePower(int line){
        int power = 0;

        for (BattleCard card : lines[line])
            power += card.getActualPower();

        return power;
    }

    public int getTotalPower(){
        int power = 0;

        for(int line = 0; line <= 2; line++){
            power += getLinePower(line);
        }

        return power;
    }

    /*-----------------------------------------
                 Modifying methods
     ------------------------------------------*/

    public void addBattleCard(BattleCard card){

        int line = card.getLine();

        lines[line].add(card);
    }

    public void addUpgrade(int line){

        lineUpgrade[line] = true;
    }

    public void addDowngrade(DowngradeCard card){

        int line = card.getLine();

        lineDowngrade[line] = true;
    }

    public void updatePower(int line) {                                     // Odswiezanie sily - gdy jest dodawana karta
        for (BattleCard card : lines[line]) {                               // to karty z tej samej linii sa odswiezane na
            card.setActualPower(card.getDefaultPower());                    // wypadek zmiany aktualnej sily
            if (lineDowngrade[line])
                card.setActualPower(1);
            if (lineUpgrade[line])
                card.setActualPower(card.getActualPower() * 2);
        }
    }

    public void pass(){
        pass = true;
    }

    public void unPass(){
        pass = false;
    }

    public void takeLive(){
        lives--;
    }

    public ArrayList<Card> clear(){
        ArrayList<Card> clearedBronzeCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for(BattleCard card : lines[i]){
                if(card.getSubType() == UnitType.Bronze){                       // clear oprocz czyszczenia polowy szuka
                    card.setActualPower(card.getDefaultPower());                // brazowych kart i wrzuca je do cmentarza
                    clearedBronzeCards.add(card);
                }
            }
            lines[i].clear();
            lineUpgrade[i] = false;
            lineDowngrade[i] = false;
        }

        return clearedBronzeCards;
    }


    /*-----------------------------------------
                 Printing methods
     ------------------------------------------*/

    public void printHalf(int width){                                                   // Wyswietlanie polowy od pierwszej linii do ostatniej
        for(int i = 0; i <= 2; i++) {

            if(lineUpgrade[i])
                System.out.print("\n|" + "\033[32m" + "+" + "\033[29m" + "|");
            else
                System.out.print("\n| |");
            if(lineDowngrade[i])
                System.out.print("\033[31m" + "-" + "\033[29m" + "| ");
            else
                System.out.print(" | ");

            System.out.print("+" + getLinePower(i) + " | ");

            int temp = width - 11 - ((Integer)getLinePower(i)).toString().length();
            if(lines[i] != null) {
                for (BattleCard card : lines[i]) {
                    System.out.print(card.toString() + " ");
                    temp -= (card.length() + 1);
                }
            }
            GameBoard.printRemainingSpace(temp);
            System.out.print("|\n");

            GameBoard.printHorizontalLine(width, "-");
        }
    }


    public void printReverseHalf(int width){                                            // Wyswietlanie polowy od ostatniej linii do pierwszej
        for(int i = 2; i >= 0; i--) {
            GameBoard.printHorizontalLine(width, "-");
            String greenColor = "\033[32m";
            String redColor = "\033[31m";
            String defaultColor = "\033[0m";

            if(lineUpgrade[i])
                System.out.print("\n|" + greenColor + "+" + defaultColor + "|");
            else
                System.out.print("\n| |");

            if(lineDowngrade[i])
                System.out.print(redColor + "-" + defaultColor + "| ");
            else
                System.out.print(" | ");

            System.out.print("+" + getLinePower(i) + " | ");

            int temp = width - 11 - ((Integer)getLinePower(i)).toString().length();
            if(lines[i] != null) {
                for (BattleCard card : lines[i]) {
                    System.out.print(card.toString() + " ");
                    temp -= (card.length() + 1);
                }
            }
            GameBoard.printRemainingSpace(temp);
            System.out.print("|\n");
        }
    }

}
