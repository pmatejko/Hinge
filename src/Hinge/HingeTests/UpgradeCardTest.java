package Hinge.HingeTests;

import Hinge.UpgradeCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UpgradeCardTest {
    @Test
    void toEnhancedString() {
        UpgradeCard card1 = new UpgradeCard();
        UpgradeCard card2 = new UpgradeCard();

        assertEquals("Upgrade", card1.toEnhancedString());
        assertEquals("Upgrade", card2.toEnhancedString());
    }

}