package Hinge;

import java.util.*;

public class Game {

    /*-----------------------------------------
                     Variables
    ------------------------------------------*/

    private Player playerOne;
    private Player playerTwo;
    private GameBoard board;

    /*-----------------------------------------
                    Constructor
     ------------------------------------------*/

    public Game(boolean player1, boolean player2){                                  // player = true -> gra prawdziwa osoba
        printInstruction();                                                         // player = false -> gra komputer
        playerOne = new Player(player1);
        playerTwo = new Player(player2);
        board = new GameBoard(playerOne, playerTwo);
    }

    /*-----------------------------------------
                      Methods
     ------------------------------------------*/

    public void play() throws InterruptedException {                                // Glowna metoda pozwalajaca zaczac gre
        boolean playerOneTurn = true;
        int width = 120;

        board.showBoard(width);

        while(playerOne.getLives() > 0 && playerTwo.getLives() > 0){                // Gra konczy sie gdy ktorys z graczy
                                                                                    // straci oba zycia
            if(playerOneTurn && !playerOne.getPass()){
                playerOneTurn = playerAction(playerOne, playerOneTurn);
                board.showBoard(width);
                Thread.sleep(2000);
            }

            if(!playerOneTurn && !playerTwo.getPass()){
                playerOneTurn = playerAction(playerTwo, playerOneTurn);
                board.showBoard(width);
                Thread.sleep(2000);
            }

            if(playerOne.getPass() && playerTwo.getPass()){                         // Runda konczy sie gdy dwoch graczy spasuje
                if(playerOne.getTotalPower() == playerTwo.getTotalPower()){         // Gdy jest remis oboje traca po zyciu
                    board.takeLive(playerOne);
                    board.takeLive(playerTwo);
                }
                else if(playerOne.getTotalPower() > playerTwo.getTotalPower())      // Gdy jeden mial wieksza laczna sile od drugiego
                    board.takeLive(playerTwo);                                      // to drugi traci jedno zycie
                else
                    board.takeLive(playerOne);

                board.clearBoard();                                                 // Po rundzie plansza jest czyszczona
                board.unPass();                                                     // a gracze zostaja odpauzowani

                playerOne.addCardsToHand(2);                                        // Kazdy gracz dostaje rowniez do reki
                playerTwo.addCardsToHand(2);                                        // 2 karty ze swojej talii

                //board.showBoard(width);
            }
        }



        if(playerTwo.getLives() == 0)
            System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                                   "~ Congratulations! Player1 won! ~" +
                                 "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        else
            System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                                   "~        Player1 lost :(        ~" +
                                 "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }



    private boolean playerAction(Player player, boolean playerOneTurn){         // Jesli jest teraz tura gracza to
                                                                                // gracz moze wybrac co chce zrobic
        if(player.getHandSize() > 0) {                                          // o ile ma jeszcze karty w rece,
            if(player.getRealPlayer())                                          // jesli nie ma to przymusowo pasuje
                playerOneTurn = realPlayerAction(player, playerOneTurn);
            else
                playerOneTurn = computerPlayerAction(player, playerOneTurn);
        }
        else {
            player.pass();
            playerOneTurn = !playerOneTurn;
        }

        return playerOneTurn;
    }

    private boolean realPlayerAction(Player player, boolean playerOneTurn){     // Jesli graczem nie jest komputer
                                                                                // to gracz moze wybrac czy chce dodac
        Player otherPlayer;                                                     // karte z reki, czy spasowac
        if (player == playerOne)
            otherPlayer = playerTwo;
        else
            otherPlayer = playerOne;


        Scanner input = new Scanner(System.in);
        int action = 0;

        do {
            System.out.println("\nChoose action:\n" +
                                 "1. Add card to board\n" +
                                 "2. Pass");

            if (input.hasNextInt())
                action = input.nextInt();
            else {
                System.out.println("Wrong choice.");
                input.next();
            }
        }
        while (action != 1 && action != 2);


        if (action == 1)
            player.addCardToBoard(board);
        else
            player.pass();


        if (!otherPlayer.getPass())
            return !playerOneTurn;
        else
            return playerOneTurn;
    }


    private boolean computerPlayerAction(Player player, boolean playerOneTurn){
                                                                                // Komputer jesli ma 2 zycia dodaje karty z reki
        Player otherPlayer;                                                     // az zostana mu tylko 4
        if (player == playerOne)                                                // gdy ma 1 zycie to dodaje wszystkie mozliwe
            otherPlayer = playerTwo;
        else
            otherPlayer = playerOne;


        if((player.getHandSize() > 4 && player.getLives() == 2) || (player.getHandSize() > 0 && player.getLives() == 1))
            player.addCardToBoard(board);
        else
            player.pass();

        if(!otherPlayer.getPass())
            return !playerOneTurn;
        else
            return playerOneTurn;
    }

    public static void printInstruction(){
        Scanner input = new Scanner(System.in);
        System.out.println(
                "    Instrukcja:\n" +
                "    1. W kazdej grze gra dwoch graczy (gracz moze byc sterowany przez komputer)\n" +
                "    2. Kazdy ma swoja talie, ktora zawiera 30 kart\n" +
                "    3. Gracz zaczyna z 10 kartami w rece, ktore zostaly dobrane z talii\n" +
                "    4. Sa 3 rodzaje kart:\n" +
                "        - jednostki ktore mozna dodac na plansze - dziela sie na:\n" +
                "            - zwykle (brazowe) - sa podatne na modyfikatory, po rundzie trafiaja do cmentarza\n" +
                "            - specjalne (zlote) - nie dzialaja na nie zadne modyfikatory, po rundzie przepadaja\n" +
                "        - karty ulepszenia - podwajaja sile brazowych jednostek w wybranej linii (na swojej polowie)\n" +
                "        - karty pogorszenia - zmieniaja sile brazowych jednostek na 1 w ustalonej linii (na obu polowach)\n" +
                "    5. Jednostki moga rowniez miec umiejetnosci specjalne:\n" +
                "        - Medyk (M) - po wystawieniu go na plansze mozna dobrac 1 karte ze cmentarza\n" +
                "        - Szpieg (S) - wystawia sie go na polowe przeciwnika, ale dobiera sie przy okazji 2 karty\n" +
                "    6. Kazdy z graczy ma 2 zycia (best of 3) - po rundzie jest odejmowane zycie temu kto na planszy\n" +
                "       mial mniej lacznej sily, w przypadku remisu oboje traca po zyciu\n" +
                "    7. Runda konczy sie wtedy, gdy oboje z graczy spasuja. (Gracz automatycznie pasuje gdy skoncza\n" +
                "       mu sie karty w rece)\n" +
                "    8. Po rundzie kazdy z graczy dobiera automatycznie 2 karty z talii do reki\n\n" +
                "    Kliknij enter, aby kontynuowac");
        input.nextLine();
    }
}
