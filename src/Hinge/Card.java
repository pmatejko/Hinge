package Hinge;

public interface Card {                                     // Interfejs karta - uzywany przede wszystkim do tworzenia
                                                            // specjalnych list z kartami - talia, reka, cmentarz, linia
    /*-----------------------------------------
                      Method
     ------------------------------------------*/

    public abstract String toEnhancedString();              // Metoda uzywana do zapisywania w Stringu nazwy,
                                                            // atrybutow i linii na ktorej ma zostac umieszczona karta
}

