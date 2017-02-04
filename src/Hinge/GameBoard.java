package Hinge;

import java.util.*;

public class GameBoard {                                    // Glowna plansza przetrzymujaca obu graczy, ktorzy aktualnie graja

    /*-----------------------------------------
                     Variables
     ------------------------------------------*/

    private Player playerOne;
    private Player playerTwo;

    /*-----------------------------------------
                    Constructor
     ------------------------------------------*/

    public GameBoard(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    /*-----------------------------------------
                 Modifying methods
     ------------------------------------------*/

    public void addCard(Card card, Player player){                  // Dodawanie karty rozni sie w zaleznosci
        if(card instanceof BattleCard)                              // od jej rodzaju
            addBattleCard((BattleCard) card, player);
        else if (card instanceof UpgradeCard)
            addUpgrade(player);
        else if (card instanceof DowngradeCard)
            addDowngrade((DowngradeCard) card);
    }

    public void addBattleCard(BattleCard card, Player player){

        Player otherPlayer;
        if (player == playerOne)
            otherPlayer = playerTwo;
        else
            otherPlayer = playerOne;


        Skill skill = card.getSkill();                              // Przy specjalnej umiejetnosci szpiega
                                                                    // karte dodaje sie na polowe przeciwnika
        if (skill == Skill.Spy){                                    // ale w zamian dobiera sie 2 karty z talii
            otherPlayer.addBattleCard(card);
        }
        else {
            player.addBattleCard(card);
        }

            updatePower(card.getLine());
    }

    public void addUpgrade(Player player){                          // Gracz wybiera linie na ktora chce
                                                                    // postawic ulepszenie, a komputer losuje
        int line = -1;

        if(player.getRealPlayer()) {
            Scanner input = new Scanner(System.in);

            while (line != 1 && line != 2 && line != 3) {
                System.out.println("Choose line which you want to upgrade (1/2/3)");
                try {
                    if (input.hasNextInt())
                        line = input.nextInt();
                    else
                        input.next();
                } catch (InputMismatchException e) {
                    System.out.println("Wrong argument.");
                }
            }

            player.addUpgrade(--line);
        }
        else {
            Random rand = new Random(System.currentTimeMillis());
            line = rand.nextInt(3);

            player.addUpgrade(line);
        }

        updatePower(line);
    }

    public void addDowngrade(DowngradeCard card){                   // Karta z negatywnym modyfikatorem ma od razu
        playerOne.addDowngrade(card);                               // przypisana linie, na ktora trzeba ja dodac.
        playerTwo.addDowngrade(card);                               // Jej efekt jest nakladany zarowno na przeciwnika
                                                                    // jak i na gracza
        updatePower(card.getLine());
    }

    private void updatePower(int line){                             // Po dodaniu karty jest odswiezana sila wszystkich
        playerOne.updatePower(line);                                // kart w linii na wypadek, gdyby byl w tej linii
        playerTwo.updatePower(line);                                // juz wczesniej modyfikator
    }

    public void takeLive(Player player){
        player.takeLive();
    }

    public void unPass(){                                           // Po skonczonej rundzie gracze sa odpauzowani
        playerOne.unPass();
        playerTwo.unPass();
    }

    public void clearBoard(){                                       // Metoda czyszczaca cala plansze
        playerOne.clearHalf();
        playerTwo.clearHalf();
    }

    /*-----------------------------------------
                  Printing methods
     ------------------------------------------*/

    public static void printHorizontalLine(int width, String lineType){
        for(int i = 0; i < width; i++){
            System.out.print(lineType);
        }
    }

    public static void printRemainingSpace(int width){
        for(int i = 0; i < width; i++){
            System.out.print(" ");
        }
    }


    public void showBoard(int width){

        System.out.println("\n\n\nPlayer1 lives:  " + playerOne.getLives()      + "        Player2 lives:  "  + playerTwo.getLives());
        System.out.println(      "Player1 passed: " + playerOne.getPass()       +     "    Player2 passed: "  + playerTwo.getPass());
        System.out.println(      "Player1 power:  " + playerOne.getTotalPower() +  "       Player2 power:  "  + playerTwo.getTotalPower());

        playerTwo.printHalf(width, true);

        printHorizontalLine(width, "=");

        playerOne.printHalf(width, false);
    }
}
