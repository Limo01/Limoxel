package foglioelettronico;
import java.awt.Color;
import static zuclib.GraficaSemplice.*;
/**
 * @brief classe CellaGrafica
 */

public class CellaGrafica 
{
    private final double verticeSxX;
    private final double verticeSxY;
    private final double verticeDxX;
    private final double verticeDxY;
    
    /**
     * @brief costruttore
     * @param verticeSxX => double, coordinata x del vertice in alto-sinistra della cella
     * @param verticeSxY => double, coordinata y del vertice in alto-sinistra della cella
     * @param verticeDxX => double, coordinata x del vertice in basso-destra della cella
     * @param verticeDxY => double, coordinata y del vertice in basso-destra della cella 
     */
    public CellaGrafica(double verticeSxX, double verticeSxY, double verticeDxX, double verticeDxY)
    {
        this.verticeSxX= verticeSxX;
        this.verticeSxY= verticeSxY;
        this.verticeDxX= verticeDxX;
        this.verticeDxY= verticeDxY;
    }
    
    /**
     * @brief metodo per scrivere il testo sulla cella 
     * @param testo => String, testo da scrivere nella cella grafica 
     */
    public void ScriviTesto(String testo)
    {
        rettangoloPieno((verticeSxX+verticeDxX)/2, (verticeSxY+verticeDxY)/2, Grafica.latoColonna, Grafica.latoRiga, BIANCO);
        rettangolo((verticeSxX+verticeDxX)/2, (verticeSxY+verticeDxY)/2, Grafica.latoColonna, Grafica.latoRiga);
        testo((verticeSxX+verticeDxX)/2, (verticeSxY+verticeDxY)/2, testo);
    }
    
    /**
     * @brief metodo per disegnare la selezione della cella dove scrivere
     * @param latoRiga => double, lunghezza dell'altezza della cella 
     * @param latoColonna => double, lunghezza della base della cella  
     */
    public void disegnaSelezione(double latoRiga, double latoColonna)
    {
        setColore();
        setGrossezza(0.004);
        rettangolo((verticeSxX+verticeDxX)/2, (verticeSxY+verticeDxY)/2, latoColonna, latoRiga);
        setGrossezza();
    }
    
    /**
     * @brief metodo per cancellare la selezione della cella in cui scrivere
     * @param latoRiga => double, lunghezza dell'altezza della cella
     * @param latoColonna => double, lunghezza della base della cella 
     */
    public void cancellaSelezione(double latoRiga, double latoColonna)
    {
        setGrossezza(0.004);
        setColore(BIANCO);
        rettangolo((verticeSxX+verticeDxX)/2, (verticeSxY+verticeDxY)/2, latoColonna, latoRiga);
        setColore();
        setGrossezza();
        rettangolo((verticeSxX+verticeDxX)/2, (verticeSxY+verticeDxY)/2, latoColonna, latoRiga);
    }
}
