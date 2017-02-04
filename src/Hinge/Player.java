package Hinge;

import java.util.*;

public class Player {

    /*-----------------------------------------
                     Variables
     ------------------------------------------*/

    private boolean realPlayer;                                         // Gracz moze byc sterowany zarowno przez
    private Deck deck;                                                  // osobe jak i przez komputer
    private Hand hand;
    private Cemetery cemetery;
    private BoardHalf half;                                             // Kazdy gracz posiada swoja polowe na ktora
                                                                        // wystawia karty
    /*-----------------------------------------
                    Constructor
     ------------------------------------------*/

    public Player(boolean realPlayer){
        this.realPlayer = realPlayer;
        deck = new Deck();
        hand = new Hand();
        cemetery = new Cemetery();
        half = new BoardHalf();
        this.addCardsToHand(10);                                        // Na poczatek gracz dostaje do reki 10 kart
    }                                                                   // ze swojej talii

    /*-----------------------------------------
                      Getters
     ------------------------------------------*/

    public int getLives(){
        return half.getLives();
    }

    public boolean getPass(){
        return half.getPass();
    }

    public int getLinePower(int line){
        return half.getLinePower(line);
    }

    public int getTotalPower(){
        return half.getTotalPower();
    }

    public int getDeckSize(){
        return deck.size();
    }

    public int getHandSize(){
        return hand.size();
    }

    public boolean getRealPlayer(){
        return realPlayer;
    }


    /*-----------------------------------------
                      Methods
     ------------------------------------------*/

    public void addCardToBoard(GameBoard board){
        int cardAmount = hand.size();
        int choice = 0;
        Card chosenCard;

        if(realPlayer) {                                                // Gracz wybiera jaka karte chce uzyc
            Scanner input = new Scanner(System.in);

            System.out.println("Which card do you want to add? (type number)");
            while (choice < 1 || choice > cardAmount) {

                for (int i = 1; i <= cardAmount; i++) {
                    System.out.println(i + ". " + (hand.get(i - 1)).toEnhancedString());
                }

                if (input.hasNextInt())
                    choice = input.nextInt();
                else
                    input.next();

                if(choice < 1 && choice > cardAmount)
                    System.out.println("Wrong argument. Which card do you want to add? (type number)");
            }

            chosenCard = hand.remove(choice - 1);
        }
        else {                                                         // Komputer losuje karte ze swojej reki
            Random rand = new Random(System.currentTimeMillis());
            choice = rand.nextInt(cardAmount);

            chosenCard = hand.remove(choice);
        }

        addChosenCardToBoard(board, chosenCard);
    }

    public void addChosenCardToBoard(GameBoard board, Card card){       // Jesli karta ma umiejetnosc specjalna
        if(card instanceof BattleCard){                                 // to jest ona uzywana przy dodawaniu
            Skill skill = ((BattleCard)card).getSkill();                // karty na plansze

            if(skill == Skill.Spy)
                addCardsToHand(2);
            else if(skill == Skill.Medic)
                addCardFromCemetery(board);
        }

        board.addCard(card, this);
    }

    public void addCardsToHand(int amount){                              // Dodaje karty do reki z talii
        for(int i = 0; i < amount; i++) {
            if(!deck.isEmpty())
                hand.add(deck.getCard());
        }
    }

    public void addCardFromCemetery(GameBoard board){                    // Dodaje karty na plansze ze cmentarza
        if(!cemetery.isEmpty())                                          // (uzywane przy karcie Medyka)
            addChosenCardToBoard(board, cemetery.getCard(realPlayer));
    }

    public void addBattleCard(BattleCard card){
        half.addBattleCard(card);
    }

    public void addUpgrade(int line){
        half.addUpgrade(line);
    }

    public void addDowngrade(DowngradeCard card){
        half.addDowngrade(card);
    }

    public void updatePower(int line){
        half.updatePower(line);
    }

    public void takeLive(){
        half.takeLive();
    }

    public void pass(){
        half.pass();
    }

    public void unPass(){
        half.unPass();
    }

    public void clearHalf(){
        cemetery.addAll(half.clear());
    }

    public void printHalf(int width, boolean reversed){
        if(reversed)
            half.printReverseHalf(width);
        else
            half.printHalf(width);
    }
}
