package foglioelettronico;
/**
 * @brief classe NumeroUtility. Utilizzato nelle classi Max e Min per il numero massimo/minimo iniziale
 */
public class NumeroUtility 
{
    private float n;
    
    /**
     * @brief costruttore
     * @param n => float, numero
     */
    public NumeroUtility(float n)
    {
        this.n= n;
    }
    
    /**
     * @brief metodo che fornisce il valore di n
     * @return n => float, numero
     */
    public float getValore()
    {
        return n;
    }
}
