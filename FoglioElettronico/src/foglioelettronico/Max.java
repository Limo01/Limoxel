package foglioelettronico;
/**
 * @brief classe Max 
 */
public class Max extends IntervalloCelle implements Valore
{
    /**
     * @brief costruttore
     * @param formula => String, formula
     * @param indice => String, indice della cella
     * @param foglio => Foglio, foglio contenente tutte le celle
     */
    public Max(String formula, String indice, Foglio foglio)
    {
        super(formula, 5, 11, foglio, indice);
    }
    
    /**
     * @brief metodo per generare il valore Max
     * @param testo => String, testo da analizzare
     * @param indice => String, indice della cella
     * @param foglio => Foglio, foglio contenente tutte le celle
     * @return => Valore, null oppure Max
     */
    public static Valore generaValore(String testo, String indice, Foglio foglio)
    {
        Max max= null;
        int lung= testo.length();
        
        if(lung>=11 && lung<=13)
        {
            if(testo.substring(0, 5).equals("=MAX(") && testo.charAt(5)>='A' && testo.charAt(5)<='Z' && testo.charAt(6)>='0' && testo.charAt(6)<='9')
            {
                if(lung== 11)
                {
                    if(testo.charAt(7)==',' && testo.charAt(8)>='A' && testo.charAt(8)<='Z' && testo.charAt(9)>='0' && testo.charAt(9)<='9' && testo.charAt(10)==')')
                    {
                        max = new Max(testo, indice, foglio);
                    }
                }
                else if(lung== 12)
                {
                    if(testo.charAt(7)==',' && testo.charAt(8)>='A' && testo.charAt(8)<='Z' && testo.charAt(9)>='0' && testo.charAt(9)<='9' && testo.charAt(10)>='0' && testo.charAt(10)<='9' && testo.charAt(11)==')')
                    {
                        max = new Max(testo, indice, foglio);
                    }
                }
                else if(lung== 13)
                {
                    if(testo.charAt(7)>='0' && testo.charAt(7)<='9' &&  testo.charAt(8)==',' && testo.charAt(9)>='A' && testo.charAt(9)<='Z' && testo.charAt(10)>='0' && testo.charAt(10)<='9' && testo.charAt(11)>='0' && testo.charAt(11)<='9' && testo.charAt(12)==')')
                    {
                        max = new Max(testo, indice, foglio);
                    }
                }
            }
        }
        if(max!=null && max.intervalloCelleOk)
        {
            return max;
        }
        return null;
    }
    
    @Override
    /**
     * @brief metodo per ottenere il valore di Max
     */
    public String getValore() 
    {
        int lung= super.celle.length;    
        NumeroUtility max= null;
        float newPossibleMax;
        int i=0;
        
        for(boolean inizializzato=false; i<lung && !inizializzato; i++)
        {
            if(!celle[i].isText && !celle[i].errore)
            {
                max= new NumeroUtility(stringToFloat(celle[i].getValore()));
                inizializzato= true;
            }
        }
        
        if(max!= null)
        {
            for(; i<lung; i++)
            {
                if(!celle[i].isText)
                {
                    newPossibleMax= stringToFloat(celle[i].getValore());

                    if(max.getValore()< newPossibleMax)
                    {
                        max= new NumeroUtility(newPossibleMax);
                    }
                }
            }
            return ""+max.getValore(); 
        }
        else return "";
    }
}