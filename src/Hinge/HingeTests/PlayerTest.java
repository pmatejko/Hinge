package Hinge.HingeTests;

import Hinge.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void getLives() {
        Player p1 = new Player(false);
        Player p2 = new Player(false);

        p2.takeLive();

        assertEquals(2, p1.getLives());
        assertEquals(1, p2.getLives());
    }

    @Test
    void getPass() {
        Player p1 = new Player(false);
        Player p2 = new Player(false);

        p1.pass();

        assertTrue(p1.getPass());
        assertFalse(p2.getPass());
    }

    @Test
    void getLinePower() {
        Player p1 = new Player(false);

        p1.addBattleCard(new BattleCard(Unit.Geralt));
        p1.addBattleCard(new BattleCard(Unit.Yennefer));

        assertEquals(15, p1.getLinePower(0));
    }

    @Test
    void getTotalPower() {
        Player p1 = new Player(false);

        p1.addBattleCard(new BattleCard(Unit.Geralt));
        p1.addBattleCard(new BattleCard(Unit.Yennefer));

        assertEquals(22, p1.getTotalPower());
    }

    @Test
    void getDeckSize() {
        Player p1 = new Player(false);
        Player p2 = new Player(false);
        GameBoard b1 = new GameBoard(p1, p2);

        p2.addCardToBoard(b1);

        assertEquals(19.0, p1.getDeckSize(), 1.0);
        assertEquals(19.0, p2.getDeckSize(), 1.0);
    }

    @Test
    void getHandSize() {
        Player p1 = new Player(false);

        assertEquals(10, p1.getHandSize());
    }

    @Test
    void getRealPlayer() {
        Player p1 = new Player(false);

        assertFalse(p1.getRealPlayer());
    }

    @Test
    void addCardToBoard() {
        Player p1 = new Player(false);
        Player p2 = new Player(false);
        GameBoard b1 = new GameBoard(p1, p2);

        p1.addCardToBoard(b1);
        assertEquals(10.0, (double)p1.getHandSize(), 1.0);
    }

    @Test
    void addChosenCardToBoard() {
        Player p1 = new Player(false);
        Player p2 = new Player(false);
        GameBoard b1 = new GameBoard(p1, p2);

        p1.addChosenCardToBoard(b1, new BattleCard(Unit.Ciri));
        assertEquals(15, p1.getTotalPower());
    }

    @Test
    void addCardsToHand() {
        Player p1 = new Player(false);

        p1.addCardsToHand(5);
        assertEquals(15, p1.getHandSize());
    }

    @Test
    void addCardFromCemetery() {
        Player p1 = new Player(false);
        Player p2 = new Player(false);
        GameBoard b1 = new GameBoard(p1, p2);

        p1.addChosenCardToBoard(b1, new BattleCard(Unit.Keira));
        p1.clearHalf();
        p1.addCardFromCemetery(b1);
        assertEquals(5, p1.getTotalPower());
    }

    @Test
    void addBattleCard() {
        Player p1 = new Player(false);

        p1.addBattleCard(new BattleCard(Unit.Yennefer));
        assertEquals(7, p1.getTotalPower());
    }

    @Test
    void addUpgrade() {
        Player p1 = new Player(false);

        p1.addBattleCard(new BattleCard(Unit.Roche));
        p1.addUpgrade(0);
        p1.updatePower(0);

        assertEquals(20, p1.getTotalPower());
    }

    @Test
    void addDowngrade() {
        Player p1 = new Player(false);

        p1.addBattleCard(new BattleCard(Unit.Keira));
        p1.addDowngrade(new DowngradeCard(1));
        p1.updatePower(1);

        assertEquals(1, p1.getTotalPower());
    }

    @Test
    void updatePower() {
        Player p1 = new Player(false);

        p1.addUpgrade(0);
        p1.addBattleCard(new BattleCard(Unit.Vesemir));
        p1.updatePower(0);

        assertEquals(12, p1.getLinePower(0));
    }

    @Test
    void takeLive() {
        Player p1 = new Player(false);
        Player p2 = new Player(false);

        p1.takeLive();

        assertEquals(1, p1.getLives());
        assertEquals(2, p2.getLives());
    }

    @Test
    void pass() {
        Player p1 = new Player(false);
        Player p2 = new Player(false);

        p1.pass();
        p2.pass();
        p2.unPass();

        assertTrue(p1.getPass());
        assertFalse(p2.getPass());
    }

    @Test
    void unPass() {
        Player p2 = new Player(false);

        p2.pass();
        p2.unPass();

        assertFalse(p2.getPass());
    }

    @Test
    void clearHalf() {
        Player p1 = new Player(false);

        p1.addBattleCard(new BattleCard(Unit.Keira));
        p1.addBattleCard(new BattleCard(Unit.Ciri));

        p1.clearHalf();

        assertEquals(0, p1.getTotalPower());
    }

}