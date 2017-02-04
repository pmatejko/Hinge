package Hinge;

public class DowngradeCard implements Card {                    // Karta zmienia sile jednostek na ustalonej przez
                                                                // karte linii na 1 o ile dana karta jest brazowego typu
    /*-----------------------------------------
                     Variables
     ------------------------------------------*/

    private int line;

    /*-----------------------------------------
                    Constructor
     ------------------------------------------*/

    public DowngradeCard(int line){
        this.line = line;
    }

    /*-----------------------------------------
                      Getters
     ------------------------------------------*/

    public int getLine(){
        return line;
    }

    /*-----------------------------------------
                      Method
     ------------------------------------------*/

    public String toEnhancedString(){
        return ("Downgrade line " + (line + 1));
    }

}
