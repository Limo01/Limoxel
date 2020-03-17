package foglioelettronico;
/**
 * @brief classe UtilityMouse
 */
public class UtilityMouse 
{
    /**
     * @brief funzione per calcolare le coordinate y della riga corrispondente della matrice
     * @param inizio => double, indica il punto da cui iniziano i quadrati della matrice sull'asse y
     * @param lato => double, lunghezza del lato di una cella
     * @param riga => variabile di tipo int che indica la riga della matrice
     * @return y => coordinata della riga nella finestra 
     */
    public static double rigaY(int riga, double lato, double inizio) 
    {
        double y;
        y = (inizio- (lato / 2)) - (lato * riga);
        return y;
    }
	
    /**
     * @brief Metodo con il quale, data la coordinata y, restituisce la posizione sulla matrice
     * @param inizio => double, indica il punto da cui iniziano i quadrati della matrice sull'asse y
     * @param y => double, coordinata nella schermata
     * @param lato => double, lunghezza del lato di una cella della matrice
     * @return riga => int, indica la riga della matrice in base alle coordinate
     */
    public static int yRiga(double y, double lato, double inizio)
    {
        int riga= (int) ((inizio-y)/lato);
        return riga;
    }
    
    /**
     * @brief funzione per calcolare le coordinate x della colonna corrispondente della matrice
     * @param inizio => double, indica il punto da cui iniziano i quadrati della matrice sull'asse x
     * @param lato => double, lunghezza del lato di una cella
     * @param colonna => variabile di tipo int che indica la colonna della matrice
     * @return x => coordinata della colonna nella finestra
     */
    public static double colonnaX(int colonna, double lato, double inizio) 
    {
        double x;
        x = inizio+(lato*colonna)+ lato/2;
        return x;
    }
	
    /**
     * @brief Metodo con il quale, data la coordinata x, restituisce la posizione sulla matrice
     * @param inizio => double, indica il punto da cui iniziano i quadrati della matrice sull'asse x
     * @param x => coordinata x del click del mouse
     * @param lato => lunghezza del lato della matrice
     * @return CodiceColore => valore intero compreso tra 0  3 che indica il numero del colore selezionato
     */
    public static int xColonna(double x, double lato, double inizio) 
    {
        int Colonna = (int) ((x-inizio)/lato);
        return Colonna;
    }
}
