package foglioelettronico;
/**
 * @brief classe Se
 */
public class Se implements Valore
{
    private Cella valoreVero;
    private Cella valoreFalso;
    private Cella primoOperando;
    private Cella secondoOperando;
    private String operatore;
    static String[] operatori={"<=", ">=", "<>", "<", ">", "="};
    
    /**
     * @brief costruttore
     * @param formula => String, formula
     * @param foglio => Foglio, foglio contenente tutte le celle 
     */
    public Se(String formula, Foglio foglio)
    {
        byte virgola1= (byte)formula.indexOf(",");
        byte virgola2= (byte)formula.indexOf(",", virgola1+1);
        
        valoreVero= foglio.getCella(formula.substring(virgola1+1, virgola2));
        valoreFalso= foglio.getCella(formula.substring(virgola2+1, formula.length()-1));
        
        String operazione= formula.substring(4, virgola1);
        
        byte posIoperatore=-1;
        for(int i=0; posIoperatore==-1; i++)
        {
            posIoperatore= (byte) operazione.indexOf(operatori[i]);
            if(posIoperatore>0)operatore= operatori[i];
        }
        
        primoOperando= foglio.getCella(operazione.substring(0, posIoperatore));
        secondoOperando= foglio.getCella(operazione.substring(primoOperando.getIndice().length()+operatore.length()));
    }
    
    /**
     * @brief metodo per generare il valore Se
     * @param testo => String, testo da analizzare
     * @param indice => String, indice della cella
     * @param foglio => Foglio, foglio contenente tutte le celle
     * @return => Valore, null oppure Se
     */
    public static Valore generaValore(String testo, String indice, Foglio foglio)
    {
        Se v= null;
        int lung= testo.length();
        
        if(lung>15 && lung<21)
        {
            if(testo.substring(0,4).equals("=SE("))
            {
                if(isOperation(testo.substring(4, testo.indexOf(",")), foglio, indice))
                {
                    if(isIndice(testo.substring(testo.indexOf(",")+1, testo.indexOf(",", testo.indexOf(",")+1)), foglio, indice) && isIndice(testo.substring(testo.indexOf(",", testo.indexOf(",")+1)+1, testo.length()-1), foglio, indice) && testo.charAt(testo.length()-1)==')')
                    {
                        v= new Se(testo, foglio);
                    }
                }
            }
        }
        
        return v;       
    }
    
    @Override
    /**
     * @brief metodo per ottenere il valore di Se
     */
    public String getValore() 
    {
        boolean esito= false; 
        
        if(!primoOperando.errore && !secondoOperando.errore)
        {
            if(!primoOperando.isText && !secondoOperando.isText)
            {
                switch(operatore)
                {
                    case ">":
                        esito= IntervalloCelle.stringToFloat(primoOperando.getValore()) > IntervalloCelle.stringToFloat(secondoOperando.getValore());
                        break;
                    case "<":
                        esito= IntervalloCelle.stringToFloat(primoOperando.getValore()) < IntervalloCelle.stringToFloat(secondoOperando.getValore());
                        break;
                    case "=":
                        esito= IntervalloCelle.stringToFloat(primoOperando.getValore()) == IntervalloCelle.stringToFloat(secondoOperando.getValore());
                        break;
                    case ">=":
                        esito= IntervalloCelle.stringToFloat(primoOperando.getValore()) >= IntervalloCelle.stringToFloat(secondoOperando.getValore());
                        break;
                    case "<=":
                        esito= IntervalloCelle.stringToFloat(primoOperando.getValore()) <= IntervalloCelle.stringToFloat(secondoOperando.getValore());
                        break;
                    case "<>": 
                        esito= IntervalloCelle.stringToFloat(primoOperando.getValore()) != IntervalloCelle.stringToFloat(secondoOperando.getValore());
                        break;
                }
            }
            else
            {
                esito=primoOperando.getValore().equals(secondoOperando.getValore());
            }
            if(esito)
            {
                if(!valoreVero.errore)return valoreVero.getValore();
            }
            else if(!valoreFalso.errore)return valoreFalso.getValore();
        }
        return "";
    } 
    
    /**
     * @brief metodo per individuare la stringa è un operazione
     * @param s => String, stringa da analizzare
     * @return => boolean, true se è un'operazione altrimenti false
     */
    private static boolean isOperation(String s, Foglio foglio, String indice)
    {
        boolean esito= false;
        byte posIoperatore=-1;
        String operatore="";
        
        for(int i=0; posIoperatore==-1 && i<operatori.length; i++)
        {
            posIoperatore= (byte) s.indexOf(operatori[i]);
            if(posIoperatore>0)operatore= operatori[i];
        }
        if(posIoperatore>1 && operatore.length()<3)
        {
            if(isIndice(s.substring(0, posIoperatore), foglio, indice) && isIndice(s.substring(posIoperatore+operatore.length()), foglio, indice))
            {
                esito= true;
            }
        }
        
        return esito;
    }
    
    /**
     * @brief metodo per individuare la stringa è un indice di una cella
     * @param s => String, stringa da analizzare
     * @return => boolean, true se è un indice altrimenti false
     */
    private static boolean isIndice(String s, Foglio foglio, String indice)
    {
        boolean esito= false;
        byte lung= (byte) s.length();
        String cifre="0123456789";
        
        if(!s.equals(indice))
        {
            if(lung==2)
            {
                if(Grafica.alfabeto.substring(0, foglio.dimensione.getSecondo()).contains(s.charAt(0)+"") && cifre.substring(1).contains(s.charAt(1)+""))
                {
                    esito= true;
                } 
            }
            else if(lung==3)
            {
                if(Grafica.alfabeto.substring(0, foglio.dimensione.getSecondo()).contains(s.charAt(0)+"") && IntervalloCelle.stringToFloat(s.substring(1))<=foglio.dimensione.getPrimo())
                {
                    esito= true;
                }
            }
        }
        return esito;
    }  
}