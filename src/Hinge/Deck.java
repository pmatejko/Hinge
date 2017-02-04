package Hinge;

import java.util.*;
import static Hinge.Unit.*;

public class Deck extends ArrayList<Card> {                          // Talia - tu sa przetrzymywane karty gracza jakie
                                                                     // po dobraniu moze uzyc w czasie gry
    /*-----------------------------------------
                    Constructor
     ------------------------------------------*/

    public Deck() {

        defaultDeck();

        Collections.shuffle(this);                                   // Potasowanie wybranej talii
    }

    /*-----------------------------------------
                      Methods
     ------------------------------------------*/

    public Card getCard() {                                 // getCard zwraca karte z gory talii (nie mozna wybierac konkretnego indeksu)
        return this.remove(0);
    }

    private void defaultDeck(){
        add(new UpgradeCard());
        add(new UpgradeCard());
        add(new UpgradeCard());
        add(new DowngradeCard(0));
        add(new DowngradeCard(1));
        add(new DowngradeCard(2));
        add(new BattleCard(Geralt));
        add(new BattleCard(Yennefer));
        add(new BattleCard(Roche));
        add(new BattleCard(Havecar));
        add(new BattleCard(Triss));
        add(new BattleCard(Vesemir));
        add(new BattleCard(Ciri));
        add(new BattleCard(Keira));
        add(new BattleCard(Stannis));
        add(new BattleCard(Dethmold));
        add(new BattleCard(Geralt));
        add(new BattleCard(Yennefer));
        add(new BattleCard(Roche));
        add(new BattleCard(Havecar));
        add(new BattleCard(Triss));
        add(new BattleCard(Vesemir));
        add(new BattleCard(Ciri));
        add(new BattleCard(Keira));
        add(new BattleCard(Stannis));
        add(new BattleCard(Dethmold));
        add(new BattleCard(Geralt));
        add(new BattleCard(Yennefer));
        add(new BattleCard(Roche));
        add(new BattleCard(Havecar));
    }
}
