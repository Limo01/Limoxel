package foglioelettronico;
/**
 * @brief classe Cella
 */
public class Cella 
{
    private Valore contenuto;
    private String valore;
    private final String indice;
    public boolean errore;
    public boolean isText;
    public boolean isNumber;
    
    /**
     * @brief costruttore
     * @param indice => String, indice della cella
     * @param valore => String, formula da inserire nella cella
     */
    public Cella(String indice, String valore)
    {
        this.indice= indice;
        this.valore= valore;
        isText= false;
        isNumber= false;
        errore= false;
    }
    
    /**
     * @brief metodo che fornisce in output l'indice della cella
     * @return indice => String, indice della cella
     */
    public String getIndice()
    {
        return indice;
    }
    
    /**
     * @brief metodo che fonisce in output il valore calcolato dalla formula
     * @return valore del contenuto della cella
     */
    public String getValore()
    {
        return contenuto.getValore();
    }
    
    /**
     * @brief metodo che fonisce in output la formula
     * @return valore => String, formula
     */
    public String getFormula()
    {
        return valore;
    }
    
    /**
     * @brief metodo che imposta il contenuto della cella
     * @param testo => String, testo formula
     * @param foglio => Foglio, foglio di lavoro contenente tutte le celle
     * @return => boolean, true se il contenuto è stato settato, altrimenti false
     */
    public boolean setContenuto(String testo, Foglio foglio, String indice)
    {
        contenuto= Numero.generaValore(testo);
        valore= testo;
        isNumber= true; 
        isText= false;
        errore= false;
        
        if(contenuto==null)
        {
            contenuto= Min.generaValore(testo, indice, foglio);
            isText= false;
            isNumber= false;
        }
        if(contenuto==null)
        {
            contenuto= Testo.generaValore(testo);
            isText= true;
            isNumber= false;
        }
        if(contenuto==null)
        {
            contenuto= Max.generaValore(testo, indice, foglio);
            isText= false;
            isNumber= false;
        }
        if(contenuto==null)
        {
            contenuto= Somma.generaValore(testo, indice, foglio);
        }
        if(contenuto==null)
        {
            contenuto= Se.generaValore(testo, indice, foglio);
        }
        if(contenuto==null)
        {
            errore= true;
        }
            
        return !(contenuto==null);
    }      
}