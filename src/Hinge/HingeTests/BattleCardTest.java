package Hinge.HingeTests;

import Hinge.BattleCard;
import Hinge.Skill;
import Hinge.Unit;
import Hinge.UnitType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleCardTest {

    @Test
    void setAndGetActualPower() {
        BattleCard card1 = new BattleCard(Unit.Geralt);
        BattleCard card2 = new BattleCard(Unit.Triss);
        BattleCard card3 = new BattleCard(Unit.Roche);
        BattleCard card4 = new BattleCard(Unit.Havecar);
        card1.setActualPower(100);
        card2.setActualPower(2);
        card3.setActualPower(100);
        card4.setActualPower(3);

        assertEquals(15, card1.getActualPower());
        assertEquals(7, card2.getActualPower());
        assertEquals(100, card3.getActualPower());
        assertEquals(3, card4.getActualPower());
    }

    @Test
    void getDefaultPower() {
        BattleCard card1 = new BattleCard(Unit.Geralt);
        BattleCard card2 = new BattleCard(Unit.Triss);
        BattleCard card3 = new BattleCard(Unit.Roche);
        BattleCard card4 = new BattleCard(Unit.Havecar);

        assertEquals(15, card1.getDefaultPower());
        assertEquals(7, card2.getDefaultPower());
        assertEquals(10, card3.getDefaultPower());
        assertEquals(5, card4.getDefaultPower());
    }

    @Test
    void getLine() {
        BattleCard card1 = new BattleCard(Unit.Geralt);
        BattleCard card2 = new BattleCard(Unit.Yennefer);
        BattleCard card3 = new BattleCard(Unit.Roche);
        BattleCard card4 = new BattleCard(Unit.Havecar);

        assertEquals(0, card1.getLine());
        assertEquals(1, card2.getLine());
        assertEquals(0, card3.getLine());
        assertEquals(2, card4.getLine());
    }

    @Test
    void getUnit(){
        BattleCard card1 = new BattleCard(Unit.Geralt);
        BattleCard card2 = new BattleCard(Unit.Yennefer);
        BattleCard card3 = new BattleCard(Unit.Roche);
        BattleCard card4 = new BattleCard(Unit.Havecar);

        assertEquals(Unit.Geralt, card1.getUnit());
        assertEquals(Unit.Yennefer, card2.getUnit());
        assertEquals(Unit.Roche, card3.getUnit());
        assertEquals(Unit.Havecar, card4.getUnit());
    }

    @Test
    void getSkill() {
        BattleCard card1 = new BattleCard(Unit.Geralt);
        BattleCard card2 = new BattleCard(Unit.Stannis);
        BattleCard card3 = new BattleCard(Unit.Roche);
        BattleCard card4 = new BattleCard(Unit.Havecar);

        assertEquals(null, card1.getSkill());
        Assertions.assertEquals(Skill.Spy, card2.getSkill());
        assertEquals(null, card3.getSkill());
        assertEquals(Skill.Medic, card4.getSkill());
    }

    @Test
    void getSubType() {
        BattleCard card1 = new BattleCard(Unit.Geralt);
        BattleCard card2 = new BattleCard(Unit.Stannis);

        Assertions.assertEquals(UnitType.Gold, card1.getSubType());
        assertEquals(UnitType.Bronze, card2.getSubType());
    }

    @Test
    void toStringTest() {
        BattleCard card1 = new BattleCard(Unit.Geralt);
        BattleCard card2 = new BattleCard(Unit.Stannis);

        assertEquals("Geralt\033[33m+15\033[29m", card1.toString());
        assertEquals("Stannis(S)\033[30m+5\033[29m", card2.toString());
    }

    @Test
    void toEnhancedString() {
        BattleCard card1 = new BattleCard(Unit.Geralt);
        BattleCard card2 = new BattleCard(Unit.Stannis);

        assertEquals("Geralt\033[33m+15\033[29m, line: 1", card1.toEnhancedString());
        assertEquals("Stannis(S)\033[30m+5\033[29m, line: 1", card2.toEnhancedString());
    }

    @Test
    void length() {
        BattleCard card1 = new BattleCard(Unit.Geralt);
        BattleCard card2 = new BattleCard(Unit.Stannis);

        assertEquals(9, card1.length());
        assertEquals(12, card2.length());
    }

    @Test
    void equals() {
        BattleCard card1 = new BattleCard(Unit.Geralt);
        BattleCard card2 = card1;
        BattleCard card3 = new BattleCard(Unit.Geralt);
        BattleCard card4 = new BattleCard(Unit.Keira);
        BattleCard card5 = null;
        String notCard = "Geralt";

        assertTrue(card1.equals(card2));
        assertTrue(card3.equals(card1));
        assertFalse(card4.equals(card1));
        assertFalse(card2.equals(card5));
        assertFalse(card1.equals(notCard));
    }

}