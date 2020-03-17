package foglioelettronico;
/**
 * @brief classe Somma
 */
public class Somma extends IntervalloCelle implements Valore
{
    /**
     * @brief costruttore
     * @param formula => String, formula
     * @param indice => String, indice della cella
     * @param foglio => Foglio, foglio contenente tutte le celle
     */   
    public Somma(String formula, String indice, Foglio foglio)
    {
        super(formula, 7, 13, foglio, indice);
    }
    
    /**
     * @brief  metodo per generare il valore somma
     * @param testo => String, testo da analizzare
     * @param indice => String, indice della cella
     * @param foglio => Foglio, foglio contenente tutte le celle
     * @return => Valore, null oppure Somma
     */    
    public static Valore generaValore(String testo, String indice, Foglio foglio)
    {
        Somma somma= null;
        int lung= testo.length();
        
        if(lung>=13 && lung<=15)
        {
            if(testo.substring(0, 7).equals("=SOMMA(") && testo.charAt(7)>='A' && testo.charAt(7)<='Z' && testo.charAt(8)>='0' && testo.charAt(8)<='9')
            {
                if(lung== 13)
                {
                    if(testo.charAt(9)==',' && testo.charAt(10)>='A' && testo.charAt(10)<='Z' && testo.charAt(11)>='0' && testo.charAt(11)<='9' && testo.charAt(12)==')')
                    {
                        somma = new Somma(testo, indice, foglio);
                    }
                }
                else if(lung== 14)
                {
                    if(testo.charAt(9)==',' && testo.charAt(10)>='A' && testo.charAt(10)<='Z' && testo.charAt(11)>='0' && testo.charAt(11)<='9' && testo.charAt(12)>='0' && testo.charAt(12)<='9' && testo.charAt(13)==')')
                    {
                        somma = new Somma(testo, indice, foglio);
                    }
                }
                else if(lung== 15)
                {
                    if(testo.charAt(9)>='0' && testo.charAt(9)<='9' &&  testo.charAt(10)==',' && testo.charAt(11)>='A' && testo.charAt(11)<='Z' && testo.charAt(12)>='0' && testo.charAt(12)<='9' && testo.charAt(13)>='0' && testo.charAt(13)<='9' && testo.charAt(14)==')')
                    {
                        somma = new Somma(testo, indice, foglio);
                    }
                }
            }
        }
        if(somma!=null && somma.intervalloCelleOk)
        {
            return somma;
        }
        return null;
    } 
    
    @Override
    /**
     * @brief metodo per ottenere il valore di Somma
     */    
    public String getValore() 
    {
        int lung= super.celle.length;
        float somma=0;
        
        for(int i=0; i<lung; i++)
        {
            if(!celle[i].isText && !celle[i].errore)
            {
                somma+= stringToFloat(celle[i].getValore()); 
            }
        } 
        return ""+somma;
    }  
}