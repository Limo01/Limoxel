package foglioelettronico;
/**
 * @brief classe Min
 */
public class Min extends IntervalloCelle implements Valore
{
    /**
     * @brief costruttore
     * @param formula => String, formula
     * @param indice => String, indice della cella
     * @param foglio => Foglio, foglio contenente tutte le celle
     */
    public Min(String formula, String indice, Foglio foglio) 
    {
        super(formula, 5, 11, foglio, indice);
    }
    
    /**
     * @brief  metodo per generare il valore Min
     * @param testo => String, testo da analizzare
     * @param foglio => Foglio, foglio contenente tutte le celle
     * @return => Valore, null oppure Min 
     */
    public static Valore generaValore(String testo, String indice, Foglio foglio)
    {
        Min min= null;
        int lung= testo.length();
        
        if(lung>=11 && lung<=13)
        {
            if(testo.substring(0, 5).equals("=MIN(") && testo.charAt(5)>='A' && testo.charAt(5)<='Z' && testo.charAt(6)>='0' && testo.charAt(6)<='9')
            {
                if(lung== 11)
                {
                    if(testo.charAt(7)==',' && testo.charAt(8)>='A' && testo.charAt(8)<='Z' && testo.charAt(9)>='0' && testo.charAt(9)<='9' && testo.charAt(10)==')')
                    {
                        min = new Min(testo,indice, foglio);
                    }
                }
                else if(lung== 12)
                {
                    if(testo.charAt(7)==',' && testo.charAt(8)>='A' && testo.charAt(8)<='Z' && testo.charAt(9)>='0' && testo.charAt(9)<='9' && testo.charAt(10)>='0' && testo.charAt(10)<='9' && testo.charAt(11)==')')
                    {
                        min = new Min(testo, indice, foglio);
                    }
                }
                else if(lung== 13)
                {
                    if(testo.charAt(7)>='0' && testo.charAt(7)<='9' &&  testo.charAt(8)==',' && testo.charAt(9)>='A' && testo.charAt(9)<='Z' && testo.charAt(10)>='0' && testo.charAt(10)<='9' && testo.charAt(11)>='0' && testo.charAt(11)<='9' && testo.charAt(12)==')')
                    {
                        min = new Min(testo,indice, foglio);
                    }
                }
            }
        }
        if(min!=null && min.intervalloCelleOk)
        {
            return min;
        }
        return null;
    }

    @Override
    /**
     * @brief metodo per ottenere il valore di Min
     */
    public String getValore() 
    {
        int lung= super.celle.length;    
        NumeroUtility min= null;
        float newPossibleMax;
        int i=0;
        
        for(boolean inizializzato=false; i<lung && !inizializzato; i++)
        {
            if(!celle[i].isText)
            {
                min= new NumeroUtility(stringToFloat(celle[i].getValore()));
                inizializzato= true;
            }
        }
        
        if(min!= null)
        {
            for(; i<lung; i++)
            {
                if(!celle[i].isText && !celle[i].errore)
                {
                    newPossibleMax= stringToFloat(celle[i].getValore());

                    if(min.getValore()> newPossibleMax)
                    {
                        min= new NumeroUtility(newPossibleMax);
                    }
                }
            }
            return ""+min.getValore(); 
        }
        else return "";
    }   
}