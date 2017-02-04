package Hinge;

public class UpgradeCard implements Card {                              // Karta podwaja sile jednostek w wybranej linii
                                                                        // o ile dana jednostka jest typu brazowego
    /*-----------------------------------------
                    Constructor
     ------------------------------------------*/

    public UpgradeCard(){}

    /*-----------------------------------------
                      Method
     ------------------------------------------*/

    public String toEnhancedString(){
        return "Upgrade";
    }

}
