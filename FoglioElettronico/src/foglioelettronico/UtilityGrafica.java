package foglioelettronico;
import java.awt.Color;
import static zuclib.GraficaSemplice.*;
/**
 * @brief classe UtilityGrafica
 */
public class UtilityGrafica 
{
    /**
     * @brief metodo per disegnare una cella
     * @param x => double, coordinata x nella finestra
     * @param y => double, coordinata y nella finestra
     * @param base => double, lunghezza della base
     * @param altezza => double, lunghezza dell'altezza
     * @param bordo => double, larghezza del bordo
     * @param coloreCella => Color, colore della cella
     * @param coloreBordo => Color, colore del bordo
     */
    public static void disegnaCellaBordo(double x, double y, double base, double altezza, double bordo, Color coloreCella, Color coloreBordo)
    {
        rettangoloPieno(x, y, base+bordo, altezza+bordo, coloreBordo);
        rettangoloPieno(x, y, base, altezza, coloreCella);
    }
    
    /**
     * @brief metodo per disegnare un bottone con una scritta centrato in x,y
     * @param x => double, coordinata x
     * @param y => double, coordinata y
     * @param b => double, misura della base
     * @param h => double, misura dell'altezza
     * @param c => Color, colore del bottone
     * @param testo => String, testo da scrivere
     */
    public static void disegnaBottone(double x, double y, double b, double h, Color c, String testo, Color coloreTesto) {
        rettangoloPieno(x, y, b, h, c);
        setColore(coloreTesto);
        testo(x, y, testo);
        setColore();
    } 
    
}
