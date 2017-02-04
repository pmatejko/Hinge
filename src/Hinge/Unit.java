package Hinge;

import static Hinge.UnitType.*;
import static Hinge.Skill.*;


public enum Unit {                                          // Wszystkie jednostki mozliwe do
    Geralt(Gold, 15, 0, null),                              // wystawiania na linie planszy
    Yennefer(Gold, 7, 1, Medic),                            // razem z atrybutami - typ, sila, linia, umiejetnosc specjalna
    Roche(Bronze, 10, 0, null),
    Havecar(Bronze, 5, 2, Medic),
    Triss(Gold, 7, 0, null),
    Vesemir(Bronze, 6, 0, null),
    Ciri(Gold, 15, 0, null),
    Keira(Bronze, 5, 1, null),
    Stannis(Bronze, 5, 0, Spy),
    Dethmold(Bronze, 6, 1, null);


    private UnitType type;
    private int power;
    private int line;
    private Skill skill;

    Unit(UnitType type, int power, int line, Skill skill){
        this.type = type;
        this.power = power;
        this.line = line;
        this.skill = skill;
    }

    public UnitType getType(){
        return type;
    }

    public int getPower(){
        return power;
    }

    public int getLine(){
        return line;
    }

    public Skill getSkill(){
        return skill;
    }
}
