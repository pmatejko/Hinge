package Hinge.HingeTests;

import Hinge.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    @Test
    void addCard() {
        Player p1 = new Player(false);
        Player p2 = new Player(false);
        GameBoard b1 = new GameBoard(p1, p2);
        Player p3 = new Player(false);
        Player p4 = new Player(false);
        GameBoard b2 = new GameBoard(p3, p4);

        b1.addCard(new BattleCard(Unit.Geralt), p1);
        b2.addCard(new BattleCard(Unit.Roche), p3);
        b2.addCard(new DowngradeCard(0), p3);

        assertEquals(15, p1.getLinePower(0));
        assertEquals(1, p3.getLinePower(0));
    }

    @Test
    void addBattleCard() {
        Player p1 = new Player(false);
        Player p2 = new Player(false);
        GameBoard b1 = new GameBoard(p1, p2);

        b1.addBattleCard(new BattleCard(Unit.Triss), p1);

        assertEquals(7, p1.getTotalPower());
    }

    @Test
    void addUpgrade() {
        Player p3 = new Player(false);
        Player p4 = new Player(false);
        GameBoard b2 = new GameBoard(p3, p4);

        b2.addCard(new BattleCard(Unit.Yennefer), p3);
        b2.addCard(new BattleCard(Unit.Triss), p3);
        b2.addUpgrade(p3);

        assertEquals(14, p3.getTotalPower());
    }

    @Test
    void addDowngrade() {
        Player p3 = new Player(false);
        Player p4 = new Player(false);
        GameBoard b2 = new GameBoard(p3, p4);

        b2.addCard(new BattleCard(Unit.Keira), p3);
        b2.addCard(new BattleCard(Unit.Dethmold), p3);
        b2.addDowngrade(new DowngradeCard(1));

        assertEquals(2, p3.getTotalPower());
    }

    @Test
    void takeLive() {
        Player p3 = new Player(false);
        Player p4 = new Player(false);
        GameBoard b2 = new GameBoard(p3, p4);

        b2.takeLive(p3);
        b2.takeLive(p4);
        b2.takeLive(p4);

        assertEquals(1, p3.getLives());
        assertEquals(0, p4.getLives());
    }

    @Test
    void unPass() {
        Player p3 = new Player(false);
        Player p4 = new Player(false);
        GameBoard b2 = new GameBoard(p3, p4);

        p3.pass();
        p4.pass();
        b2.unPass();

        assertFalse(p3.getPass());
        assertFalse(p4.getPass());
    }

    @Test
    void clearBoard() {
        Player p3 = new Player(false);
        Player p4 = new Player(false);
        GameBoard b2 = new GameBoard(p3, p4);

        b2.addCard(new BattleCard(Unit.Geralt), p3);
        b2.addCard(new BattleCard(Unit.Keira), p4);
        b2.clearBoard();

        assertEquals(0, p3.getTotalPower());
        assertEquals(0, p4.getTotalPower());
    }

}