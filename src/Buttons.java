import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.applet.Applet;
/**
 * class Buttons.
 */
public class Buttons extends Applet
{
    private JButton confirmer,revenir, suivant, precedent;
    /**
     * Constructeur d'objets de classe Buttons
     */
    public Buttons()
    {
        setLayout(new GridLayout(1,4));
        
        confirmer=new JButton("Confirmer la creation.");
        revenir=new JButton("Revenir a l'acceuil");
        precedent=new JButton("Image precedente");
        suivant=new JButton("Image suivante");
        
        
        add(revenir);
        add(precedent);
        add(suivant);
        add(confirmer);
        
    }
    
    public JButton getConfirmer(){
        return this.confirmer;
    }
    
    public JButton getRevenir(){
        return this.revenir;
    }
    
    public JButton getSuivant(){
        return this.suivant;
    }
    
    public JButton getPrecedent(){
        return this.precedent;
    }
}
