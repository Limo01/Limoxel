package foglioelettronico;
import static zuclib.GraficaSemplice.*;
import static zuclib.Varie.ritardo;

/**
 * @brief classe Grafica
 */
public class Grafica 
{
    private CellaGrafica[][] celle;
    
    public static final String alfabeto= "ABCDEFGHIJKLMNOPQRSTUWXYZ";
    public static double latoColonna;
    public static double latoRiga;
    public static int righe;
    public static int colonne;
    
    /**
     * @brief costruttore
     * @param dimensioneFoglio => Coppia, dimensione foglio righe e colonne 
     */
    public Grafica(Coppia dimensioneFoglio)
    {
        setFinestra(1000, 550, "LIMOXEL");
        celle= new CellaGrafica[dimensioneFoglio.getPrimo()][dimensioneFoglio.getSecondo()];
        colonne=celle[0].length+1;
        righe=celle.length+1; 
        latoColonna= 1.0/colonne;
        latoRiga= 1.0/righe;
        disegnaFoglio();
    }
    
    /**
     * @brief metodo per scrivere il testo su una cella
     * @param coordinate => Coppia, coordinate riga e colonna
     * @param testo => String, testo da scrivere sulla cella
     */
    public void setTesto(Coppia coordinate, String testo)
    {
        celle[coordinate.getPrimo()][coordinate.getSecondo()].ScriviTesto(testo);
    }
    
    /**
     * @brief metodo per disegnare la selezione della cella corrente
     * @param coordinate => Coppia, coordinate riga e colonna
     */
    public void disegnaSelezione(Coppia coordinate)
    {
        celle[coordinate.getPrimo()][coordinate.getSecondo()].disegnaSelezione(latoRiga, latoColonna);
    }
    
    /**
     * @brief metodo per cancellare la selezione della cella corrente
     * @param coordinate => Coppia, coordinate riga e colonna 
     */
    public void cancellaSelezione(Coppia coordinate)
    {
        celle[coordinate.getPrimo()][coordinate.getSecondo()].cancellaSelezione(latoRiga, latoColonna);
    }
    
    /**
     * @brief metodo per ottenere le coordinate della cella in base alle coordinate della finestra
     * @param mouseX => double, coordinata x del punto in cui è stato premuto il mouse nella fisnestra
     * @param mouseY => double, coordinata y del punto in cui è stato premuto il mouse nella fisnestra
     * @return => Coppia, coordinate riga e colonna del foglio
     */
    public Coppia getCoordinateFoglio(double mouseX, double mouseY)
    {
        return new Coppia(UtilityMouse.yRiga(mouseY, latoRiga, 1.0-latoRiga), UtilityMouse.xColonna(mouseX, latoColonna, latoColonna));
    }
    
    /**
     * @brief metodo per disegnare il foglio
     */
    private void disegnaFoglio()
    {
        inizializzaCelleGrafiche();
        
        for(int i=1; i<=righe; i++)
        {
            linea(0, latoRiga*i, 1, latoRiga*i);  
        }
        for(int i=1; i<=colonne; i++)
        {
            linea(latoColonna*i, 0, latoColonna*i, 1);
        }
        
        for(int i=0; i<colonne-1; i++)
        {
            testo(latoColonna+(latoColonna/2)+(latoColonna*i), 1-(latoRiga/2), ""+alfabeto.charAt(i));
        }
        
        for(int i=1; i<=righe; i++)
        {
            testo(latoColonna/2, 1.0-(latoRiga+(latoRiga/2)+(latoRiga*(i-1))), ""+i);
        }
        disegnaLogo();
    }
    
    /**
     * @brief metodo per disegnare la scritta in alto a sinistra
     */
    private void disegnaLogo()
    {
        rettangoloPieno(latoColonna/2, 1.0-latoRiga/2, latoColonna, latoRiga, VERDE);
        testo(latoColonna/2, 1.0-latoRiga/2, "LIMOXEL");
    }
    
    /**
     * @brief metodo per inizializzare le celle grafiche della matrice
     */
    private void inizializzaCelleGrafiche()
    {
        int righe= celle.length;
        int colonne= celle[0].length;
        
        double inizioCoordinateColonna= 1.0/(colonne+1);
        double latoRiga= (1.0/(righe+1));       
        
        for(int r=0; r< righe; r++)
        {
            for(int c=0; c< colonne; c++)
            {
                celle[r][c]= new CellaGrafica(inizioCoordinateColonna*(c+1), 1.0-latoRiga*(r+1), inizioCoordinateColonna*(c+2), 1.0-latoRiga*(r+2));
            }
        }
    }
    
    ///metodi statici
    /**
     * @brief metodo per disegnare la selezione della grandezza foglio
     */
    private static void disegnaSelezione()
    {
        setFinestra(500, 350, "LIMOXEL");
        quadratoPieno(0.5, 0.5, 1, GRIGIO_CHIARO);
        UtilityGrafica.disegnaBottone(0.5, 0.8, 0.55, 0.1, GRIGIO, "SELEZIONA GRANDEZZA FOGLIO", VERDE);
        UtilityGrafica.disegnaCellaBordo(0.25, 0.5, 0.10, 0.10, 0.02, BIANCO, NERO);
        UtilityGrafica.disegnaCellaBordo(0.75, 0.5, 0.10, 0.10, 0.02, BIANCO, NERO);
        testo(0.25, 0.7, "RIGHE");        
        testo(0.75, 0.7, "COLONNE");
        UtilityGrafica.disegnaBottone(0.25, 0.60, 0.05, 0.05, NERO, "+", BIANCO);//righe+
        UtilityGrafica.disegnaBottone(0.25, 0.40, 0.05, 0.05, NERO, "-", BIANCO);//righe-
        UtilityGrafica.disegnaBottone(0.75, 0.60, 0.05, 0.05, NERO, "+", BIANCO);//colonne+
        UtilityGrafica.disegnaBottone(0.75, 0.40, 0.05, 0.05, NERO, "-", BIANCO);//colonne-
        UtilityGrafica.disegnaBottone(0.5, 0.25, 0.2, 0.1, VERDE, "CONFERMA", NERO);//tasto conferma
    }
    
    /**
     * @brief metodo per leggere l'input delle dimensioni del foglio
     * @return => Coppia, grandezza riga e colonne del foglio
     */
    public static Coppia selezioneGrandezzaFoglio()
    {
        boolean confermato= false;
        double x, y;
        byte righe=10;
        byte colonne= 5;   
        disegnaSelezione();
        testo(0.25, 0.50, ""+righe);
        testo(0.75, 0.50, ""+colonne);
        
        while(!confermato)
        {
            while(!mousePremuto()){}
            while(mousePremuto())
            {
                x= mouseX();
                y= mouseY();
                    
                if(x>=0.225 && x<=0.275 && y>=0.575 && y<=0.625 && righe<25)
                {  
                    testo(0.25, 0.5, ""+righe, 0, BIANCO);
                    righe++;
                    testo(0.25, 0.5, ""+righe);
                }
                else if(x>=0.225 && x<=0.275 && y>=0.375 && y<=0.425 && righe>1)
                {
                    testo(0.25, 0.5, ""+righe, 0, BIANCO);
                    righe--;
                    testo(0.25, 0.50, ""+righe);
                }
                else if(x>=0.725 && x<=0.775 && y>=0.575 && y<=0.625 && colonne<25)
                {
                    testo(0.75, 0.5, ""+colonne, 0, BIANCO);
                    colonne++;
                    testo(0.75, 0.5, ""+colonne);
                }
                else if(x>=0.725 && x<=0.775 && y>=0.375 && y<=0.425 && colonne>1)
                {
                    testo(0.75, 0.5, ""+colonne, 0, BIANCO);
                    colonne--;
                    testo(0.75, 0.5, ""+colonne);
                }
                else if(x>=0.4 && x<=0.6 && y>=0.2 && y<=0.3)
                {
                    confermato=true;
                    quadratoPieno(0.5, 0.5, 1, BIANCO);
                }
                ritardo(100);
            }
        }
        return new Coppia(righe, colonne); 
    }
}
