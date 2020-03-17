package foglioelettronico;
/**
 * @brief classe Coppia
 */
public class Coppia 
{
    private int primo;
    private int secondo;
    
    /**
     * @brief costruttore
     * @param primo => int, primo numero
     * @param secondo => int, secondo numero 
     */
    public Coppia(int primo, int secondo)
    {
        this.primo= primo;
        this.secondo= secondo;
    }
    
    /**
     * @brief metodo che fornisce il primo numero della coppia
     * @return primo => int, primo numero
     */
    public int getPrimo()
    {
       return primo;
    }
    /**
     * @brief metodo che fornisce il primo numero della coppia
     * @return secondo => int, secondo numero
     */
    public int getSecondo()
    {
       return secondo;
    }
}