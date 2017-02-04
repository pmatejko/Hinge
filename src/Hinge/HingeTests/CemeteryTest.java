package Hinge.HingeTests;

import Hinge.BattleCard;
import Hinge.Cemetery;
import Hinge.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CemeteryTest {
    @Test
    void getCard() {
        Cemetery cem1 = new Cemetery();
        Cemetery cem2 = new Cemetery();

        cem1.add(new BattleCard(Unit.Roche));
        cem2.add(new BattleCard(Unit.Keira));

        assertEquals(new BattleCard(Unit.Roche), cem1.getCard(false));
        assertEquals(new BattleCard(Unit.Keira), cem2.getCard(false));
    }

}