package foglioelettronico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static zuclib.GraficaSemplice.*;
import static zuclib.Varie.*;

/**
 * @brief classe Foglio
 */
public class Foglio 
{
    private Cella[][] celle;
    private Grafica grafica;
    private Coppia cellaCorrente;
    private InputCelle input;
    public Coppia dimensione;
    
    /**
     * @brief costruttore
     * @param dimensione => Coppia, dimensioni riga e colonna del foglio
     */
    public Foglio(Coppia dimensione)
    {
        this.dimensione= dimensione;
        grafica= new Grafica(dimensione);
        celle= new Cella[dimensione.getPrimo()][dimensione.getSecondo()];
        cellaCorrente= new Coppia(0, 0);
        grafica.disegnaSelezione(cellaCorrente);
        input= new InputCelle();
        inizializzaCelle();
        gestoreEventi(); 
    }
    
    /**
     * @brief metodo che fornisce in output una cella del foglio in base all'indice fornito come parametro
     * @param indice => String, indice della cella
     * @return => Cella, cella in base all'indice fornito
     */
    public Cella getCella(String indice)
    { 
        if(indice.length()==2)
        {
            return celle[indice.charAt(1)-'1'][Grafica.alfabeto.indexOf(indice.charAt(0))];
        }
        return celle[(indice.charAt(1)-'0')*10+(indice.charAt(2)-'0')-1][Grafica.alfabeto.indexOf(indice.charAt(0))];
    }
    
    /**
     * @brief metodo che gestisce gli eventi del foglio
     */
    public void gestoreEventi()
    {
        double x, y;
        input.setTesto(celle[cellaCorrente.getPrimo()][cellaCorrente.getSecondo()].getFormula());
        
        while(true)
        {
            while(!mousePremuto()){}
            
            x= mouseX();
            y= mouseY();
            
            if(x>Grafica.latoColonna && y<(1.0-Grafica.latoRiga))
            {
                grafica.cancellaSelezione(cellaCorrente);
                cellaCorrente= grafica.getCoordinateFoglio(x, y);
                input.setTesto(celle[cellaCorrente.getPrimo()][cellaCorrente.getSecondo()].getFormula());
                grafica.disegnaSelezione(cellaCorrente);
            }
            ritardo(200);
        }
    }
    
    /**
     * @brief metodo che viene eseguito dopo che viene premuto il bottone sul visore. Legge la formula inserita e scrive sulla cella il valore calcolato
     */
    public void premutoBottoneConfermaTesto()
    {
        String testo= input.leggiTesto();              
                
        if(!testo.equals(celle[cellaCorrente.getPrimo()][cellaCorrente.getSecondo()].getFormula()))//se il testo in input non è uguale a quello precedente
        {
            if(celle[cellaCorrente.getPrimo()][cellaCorrente.getSecondo()].setContenuto(testo, this, celle[cellaCorrente.getPrimo()][cellaCorrente.getSecondo()].getIndice()))
            {
                testo= celle[cellaCorrente.getPrimo()][cellaCorrente.getSecondo()].getValore();
            }
            else testo="ERRORE";  
                    
            grafica.setTesto(cellaCorrente, testo);
            aggiornaFoglio();
            grafica.disegnaSelezione(cellaCorrente);
        }
    }
    
    /**
     * @brief aggiorna i valori delle celle del foglio
     */
    private void aggiornaFoglio()
    {
        int righe= celle.length;
        int colonne= celle[0].length;
        
        for(int r=0; r<righe; r++)
        {
            for(int c=0; c<colonne; c++)
            {
                if(!celle[r][c].isText && !celle[r][c].isNumber && !celle[r][c].errore)
                {
                    grafica.setTesto(new Coppia(r,c), celle[r][c].getValore());
                } 
            }
        }
    }
    
    /**
     * @brief metodo per inizializzare le celle della matrice
     */
    private void inizializzaCelle()
    {
        int righe= celle.length;
        int colonne= celle[0].length;
        String indice= "";
        
        for(int r=0; r<righe; r++)
        {
            for(int c=0; c<colonne; c++)
            {
                indice+= Grafica.alfabeto.charAt(c);
                indice+= r+1;
                
                celle[r][c]= new Cella(indice, "\"\"");
                celle[r][c].setContenuto("\"\"", this, indice);
                indice= "";
            }
        }
    }
    
    //classe input celle dichiarata dentro la classe foglio per far funzionare il bottone del visore
    /**
     * @brief classe InputCelle, visore per l'input nel folgio
     */
    class InputCelle 
    {
        private JFrame finestra;
        private Container c;
        private JPanel p;
        private JTextField testoCella;
        private JButton bottone;
        private ActionListener bottoneInput;
        
        /**
         * @brief costruttore
         */
        public InputCelle()
        {         
            finestra = new JFrame("Inserimento cella");  // crea una nuova finestra invisibile                
            finestra.setSize(300, 100);        // misure in pixel per impostare le dimensioni
            finestra.setLocation(1000, 100); // e la posizione  con (0,0) angolo sup. sin.
            finestra.setResizable(false);      // per ridimensionare con mouse
            c = finestra.getContentPane();
            p = new JPanel();
            p.setBackground(Color.green);// sfondo colorato
            testoCella = new JTextField(20);
            p.add(testoCella);  // aggiunge al pannello un campo di testo con ampiezza specificata 
            creaBottoneAzione();
            c.add(p);  // aggiunge il pannello 
            finestra.setVisible(true);// mostra la finestra (dimensioni 500x500)
            finestra.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);//quando si preme sulla X la finestra non viene chiusa.
        }
        
        /**
         * @brief metodo che crea il bottone con il suo ActionListener
         */
        private void creaBottoneAzione()
        {
            bottone= new JButton("CONFERMA");
            LetturaTesto azione= new LetturaTesto();
            bottone.addActionListener(azione);
            p.add(bottone);
        }
        
        /**
         * @brief metodo che legge il testo dalla JTextField
         * @return => String, testo della JTextField
         */
        public String leggiTesto()
        {
            return testoCella.getText();
        }
        
        /**
         * @brief metodo che setta il testo della JTextField
         * @param testo => String, testo da impostare nella JTextField
         */
        public void setTesto(String testo)
        {
            testoCella.setText(testo);
        }
        
        /**
         * @brief classe LetturaTesto
         */
        private class LetturaTesto implements ActionListener
        {
            @Override
            /**
             * @brief metodo da attivare quando viene premuto il bottone
             */
            public void actionPerformed(ActionEvent ae) 
            {
                try {
                    premutoBottoneConfermaTesto();
                } catch (Exception ex) {
                    Logger.getLogger(InputCelle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        }
    }
}