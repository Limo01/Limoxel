package foglioelettronico;
/**
 * @brief classe IntervalloCelle
 */
public class IntervalloCelle 
{
    protected Cella[] celle;
    protected boolean intervalloCelleOk;
    
    /**
     * @brief costruttore
     * @param formula => String, formula
     * @param posIniziale => int, posizione della stringa da cui iniziare le analisi
     * @param lungI => int, lunghezza minima della stringa
     * @param foglio => Foglio, contiene tutte le celle del foglio
     * @param indice => String, indice della cella
     */
    public IntervalloCelle(String formula, int posIniziale, int lungI, Foglio foglio, String indice)
    {
        int rI=0, rF=0, cI=0, cF=0;
        int lung= formula.length();
        intervalloCelleOk= true;
        
        if(lung==lungI)
        {
            rI= formula.charAt(posIniziale+1)-'0';
            rF= formula.charAt(posIniziale+4)-'0';
            cI= formula.charAt(posIniziale)-'A';
            cF= formula.charAt(posIniziale+3)-'A';
        }
        else if(lung==lungI+1)
        {
            rI= formula.charAt(posIniziale+1)-'0';
            rF= ((formula.charAt(posIniziale+4)-'0')*10)+(formula.charAt(posIniziale+5)-'0');
            cI= formula.charAt(posIniziale)-'A';
            cF= formula.charAt(posIniziale+3)-'A';
        }
        else if(lung==lungI+2)
        {
            rI= ((formula.charAt(posIniziale+1)-'0')*10)+(formula.charAt(posIniziale+2)-'0');
            rF= ((formula.charAt(posIniziale+5)-'0')*10)+(formula.charAt(posIniziale+6)-'0');
            cI= formula.charAt(posIniziale)-'A';
            cF= formula.charAt(posIniziale+4)-'A';
        }                    
        
        if(rI<=rF && cI<=cF && rI>0 && rF<=foglio.dimensione.getPrimo() && cF<foglio.dimensione.getSecondo() && !(cI<=indice.charAt(0)-'A' && cF>=indice.charAt(0)-'A' && rI<=stringToFloat(indice.substring(1)) && rF>=stringToFloat(indice.substring(1))))
        {
            lung= (rF-rI+1)*(cF-cI+1);

            celle= new Cella[lung];

            char colonna;
        
            int i=0;
            int indiceN=0;
            
            for(int r= rI; r<= rF; r++)
            {
                i=0;
                for(int c= cI; c<= cF; c++, indiceN++, i++)
                {       
                    colonna= (char)(formula.charAt(posIniziale)+i);
                    celle[indiceN]= foglio.getCella(""+colonna+r);
                }
            }
        }
        else intervalloCelleOk= false;   
    }
    
    /**
     * @brief metodo che converte una stringa in float
     * @param numero => String, stringa da convertire in numero
     * @return => float, numero convertito
     */
    public static float stringToFloat(String numero)
    {
        boolean negativo= false;
        if(numero.charAt(0)=='-'){ numero= numero.substring(1); negativo= true;}
        float n= numero.charAt(0)-'0';
        int lung= numero.length();
        boolean virgola= false;
        int divDecimali=10;
        
        for(int i=1; i<lung; i++)
        {
            if(numero.charAt(i)=='.')
            {
                virgola= true;
            }
            
            if(!(numero.charAt(i)=='.'))
            {
                n*=divDecimali;
                n+= numero.charAt(i)-'0';   
            }
            
            if(virgola && !(numero.charAt(i)=='.'))
            {
                n/=divDecimali;
                divDecimali*=10;
            }
        }
        return negativo? n*(-1): n;
    } 
}
