package foglioelettronico;
/**
 * @brief classe Numero
 */
public class Numero implements Valore
{
    private String valore;
    
    /**
     * @brief costruttore
     * @param numero => String, numero in stringa 
     */
    public Numero(String numero)
    {
        valore= numero;
    }
    
    /**
     * @brief metodo per controllare se la stringa è un numero
     * @param testo => String, stringa da analizzare
     * @return => Valore, null o Numero
     */
    public static Valore generaValore(String testo) 
    {
        Numero out= null;
        if(isNumero(testo))
        {
            if(!testo.contains("."))
            {
                testo+=".0";
            }
            out= new Numero(testo);
        } 
        return out;
    }    
    
    @Override
    /**
     * @brief metodo che restituisce il valore Numero
     */
    public String getValore() 
    {
        return valore;
    }
    
    /**
     * @brief metodo per controllare se la stringa è un numero
     * @param testo => String, stringa da analizzare
     * @return => boolean, true se è un numero altrimenti false
     */
    private static boolean isNumero(String testo) 
    {
        boolean isNumero = false;
        boolean virgola = false;  
        
        if(testo.length()>0)
        {
            isNumero=true;
            if(testo.charAt(0)=='-')testo= testo.substring(1);
            if(testo.length()==0) isNumero=false;
            
            for (int i = 0; i < testo.length() && isNumero; i++) 
            {
                if (testo.charAt(i) == '.' && !virgola && i+4>testo.length()-1)//può avere massimo 3 cifre decimali
                {
                    virgola = true;
                } 
                else if (!(testo.charAt(i) - '0' >= 0 && testo.charAt(i) - '0' <= 9))
                {
                    isNumero = false;
                }
            }
        }
        return isNumero;
    }
}