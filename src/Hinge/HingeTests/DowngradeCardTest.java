package Hinge.HingeTests;

import Hinge.DowngradeCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class DowngradeCardTest {
    @Test
    void getLine() {
        DowngradeCard card1 = new DowngradeCard(0);
        DowngradeCard card2 = new DowngradeCard(1);
        DowngradeCard card3 = new DowngradeCard(2);

        assertEquals(0, card1.getLine());
        assertEquals(1, card2.getLine());
        assertEquals(2, card3.getLine());
    }

    @Test
    void toEnhancedString() {
        DowngradeCard card1 = new DowngradeCard(0);
        DowngradeCard card2 = new DowngradeCard(1);
        DowngradeCard card3 = new DowngradeCard(2);

        assertEquals("Downgrade line 1", card1.toEnhancedString());
        assertEquals("Downgrade line 2", card2.toEnhancedString());
        assertEquals("Downgrade line 3", card3.toEnhancedString());
    }

}