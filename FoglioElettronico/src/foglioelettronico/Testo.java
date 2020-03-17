package foglioelettronico;

/**
 * @brief classe Testo
 */
public class Testo implements Valore
{
    private String valore;
    
    /**
     * @brief costruttore
     * @param testo => String, testo
     */
    public Testo(String testo)
    {
        valore= testo.substring(1, testo.length()-1);
    }
    
    @Override
    /**
     * @brief metodo che fornisce il valore Testo
     */
    public String getValore() 
    {
        return valore;
    }
    
    /**
     * @brief metodo per generare il valore testo
     * @param testo => String, testo
     * @return 
     */
    public static Valore generaValore(String testo)
    {
        Testo t= null;
        
        if(testo.length()>1)
        {
            if(testo.charAt(0)=='"' && testo.charAt(testo.length()-1)=='"')
            {
                t= new Testo(testo);
            }
        }
        return t;
    }
}
