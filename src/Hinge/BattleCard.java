package Hinge;

public class BattleCard implements Card {                   // Karty, ktore moga byc wystawione na plansze
                                                            // Kazda karta ma ustalona z gory linie,
                                                            // specjalna umiejetnosc, typ oraz sile
    /*-----------------------------------------
                     Variables
     ------------------------------------------*/

    private UnitType subType;
    private Skill skill;
    private Unit unit;
    private int line;
    private int defaultPower;
    private int actualPower;

    /*-----------------------------------------
                    Constructor
     ------------------------------------------*/

    public BattleCard(Unit unit) {
        this.unit = unit;
        this.subType = unit.getType();
        this.actualPower = this.defaultPower = unit.getPower();
        this.line = unit.getLine();
        this.skill = unit.getSkill();
    }

    /*-----------------------------------------
                      Setter
     ------------------------------------------*/

    public void setActualPower(int power){                                // Podatne na modyfikatory, zarowno pozytywne
        if(subType == UnitType.Bronze)                                    // jak i negatywne sa tylko karty brazowe
            actualPower = power;
    }

    /*-----------------------------------------
                      Getters
     ------------------------------------------*/

    public int getActualPower(){
        return actualPower;
    }

    public int getDefaultPower(){
        return defaultPower;
    }

    public int getLine(){
        return line;
    }

    public Unit getUnit(){
        return unit;
    }

    public Skill getSkill(){
        return skill;
    }

    public UnitType getSubType() {
        return subType;
    }

    public String toString() {                                              // Zwraca postac przeznaczona do wyswietlania
        String color;                                                       // w liniach planszy
        String defaultColor = "\033[0m";
        if(subType == UnitType.Gold)
            color = "\033[33m";                                             // Kolor zolty
        else
            color = "\033[30m";                                             // Kolor czarny

        if(skill == null)
            return (unit.toString() + color + "+" +
                    actualPower + defaultColor);
        else
            return (unit.toString() + "(" + (skill.toString()).substring(0,1) + ")"
                    + color + "+" + actualPower + defaultColor);
    }

    public String toEnhancedString(){                                               // Zwraca postac przeznaczona do
        return toString() + ", line: " + (line + 1);                                // wyswietlania podczas wybierania kart
    }

    public int length(){                                                            // Zwraca dlugosc jaka zajmuja same wyswietlane znaki
        if(skill == null)                                                           // Stringa (pomija znaki odpowiadajace za kolor)
            return ((unit.toString()).length() + 1 +
                    (((Integer)actualPower).toString()).length());
        else
            return ((unit.toString()).length() + 4 +
                    (((Integer)actualPower).toString()).length());
    }

    public boolean equals(Object o){
        if(o == null)
            return false;
        else if(this == o)
            return true;
        else if(!(o instanceof BattleCard))
            return false;
        else
            return unit == ((BattleCard)o).getUnit();
    }

}
