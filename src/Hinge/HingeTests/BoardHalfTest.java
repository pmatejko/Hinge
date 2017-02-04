package Hinge.HingeTests;

import Hinge.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardHalfTest {
    @Test
    void getAndTakeLives() {
        BoardHalf b1 = new BoardHalf();
        BoardHalf b2 = new BoardHalf();
        b2.takeLive();

        assertEquals(2, b1.getLives());
        assertEquals(1, b2.getLives());
    }

    @Test
    void getAndSetPass() {
        BoardHalf b1 = new BoardHalf();
        BoardHalf b2 = new BoardHalf();
        b2.pass();

        assertFalse(b1.getPass());
        assertTrue(b2.getPass());
    }

    @Test
    void getLinePower() {
        BoardHalf b1 = new BoardHalf();
        BoardHalf b2 = new BoardHalf();
        b2.addBattleCard(new BattleCard(Unit.Geralt));

        assertEquals(0, b1.getLinePower(0));
        assertEquals(15, b2.getLinePower(0));
    }

    @Test
    void getTotalPower() {
        BoardHalf b1 = new BoardHalf();
        BoardHalf b2 = new BoardHalf();
        b2.addBattleCard(new BattleCard(Unit.Geralt));
        b2.addBattleCard(new BattleCard(Unit.Keira));

        assertEquals(0, b1.getTotalPower());
        assertEquals(20, b2.getTotalPower());
    }

    @Test
    void addBattleCard() {
        BoardHalf b1 = new BoardHalf();
        BoardHalf b2 = new BoardHalf();
        b1.addBattleCard(new BattleCard(Unit.Geralt));
        b2.addBattleCard(new BattleCard(Unit.Keira));

        assertEquals(15, b1.getLinePower(0));
        assertEquals(5, b2.getLinePower(1));
    }

    @Test
    void addUpgrade() {
        BoardHalf b2 = new BoardHalf();
        b2.addBattleCard(new BattleCard(Unit.Keira));
        b2.addUpgrade(1);
        b2.updatePower(1);

        assertEquals(10, b2.getLinePower(1));
    }

    @Test
    void addDowngrade() {
        BoardHalf b2 = new BoardHalf();
        b2.addBattleCard(new BattleCard(Unit.Keira));
        b2.addDowngrade(new DowngradeCard(1));
        b2.updatePower(1);

        assertEquals(1, b2.getLinePower(1));
    }

    @Test
    void updatePower() {
        BoardHalf b2 = new BoardHalf();
        b2.addBattleCard(new BattleCard(Unit.Roche));
        b2.addDowngrade(new DowngradeCard(0));
        b2.updatePower(0);

        assertEquals(1, b2.getLinePower(0));
    }

    @Test
    void unPass() {
        BoardHalf b1 = new BoardHalf();
        b1.pass();
        b1.unPass();

        assertFalse(b1.getPass());
    }

    @Test
    void clear() {
        BoardHalf b1 = new BoardHalf();
        b1.addBattleCard(new BattleCard(Unit.Ciri));
        b1.addBattleCard(new BattleCard(Unit.Roche));

        ArrayList<Card> temp = new ArrayList<>();
        temp.add(new BattleCard(Unit.Roche));

        assertArrayEquals(temp.toArray(), (b1.clear()).toArray());
    }

}