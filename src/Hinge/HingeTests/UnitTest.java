package Hinge.HingeTests;

import Hinge.Skill;
import Hinge.Unit;
import Hinge.UnitType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    @Test
    void getType() {
        Unit unit1 = Unit.Dethmold;
        Unit unit2 = Unit.Ciri;
        Unit unit3 = Unit.Triss;

        Assertions.assertEquals(UnitType.Bronze, unit1.getType());
        assertEquals(UnitType.Gold, unit2.getType());
        assertEquals(UnitType.Gold, unit3.getType());
    }

    @Test
    void getPower() {
        Unit unit1 = Unit.Geralt;
        Unit unit2 = Unit.Keira;
        Unit unit3 = Unit.Dethmold;

        assertEquals(15, unit1.getPower());
        assertEquals(5, unit2.getPower());
        assertEquals(6, unit3.getPower());
    }

    @Test
    void getLine() {
        Unit unit1 = Unit.Geralt;
        Unit unit2 = Unit.Havecar;
        Unit unit3 = Unit.Keira;

        assertEquals(0, unit1.getLine());
        assertEquals(2, unit2.getLine());
        assertEquals(1, unit3.getLine());
    }

    @Test
    void getSkill() {
        Unit unit1 = Unit.Roche;
        Unit unit2 = Unit.Stannis;
        Unit unit3 = Unit.Yennefer;

        assertEquals(null, unit1.getSkill());
        Assertions.assertEquals(Skill.Spy, unit2.getSkill());
        assertEquals(Skill.Medic, unit3.getSkill());
    }

}