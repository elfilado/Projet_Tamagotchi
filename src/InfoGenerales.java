import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.applet.Applet;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JLabel;
/**
 * classe InfoGenerales
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class InfoGenerales extends Applet
{
    private JButton quit, save;
    private JLabel lab;
    /**
     * Constructeur d'objets de classe InfoGenerales
     */
    public InfoGenerales(Tamagotchi ta)
    {
        setLayout(new GridLayout(5,1));
        
        JLabel n = new JLabel("Nom :"+ta.getNom());
        Image img= new ImageIcon(ta.getAvatar()).getImage();
        Image newImg= img.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
        lab=new JLabel(new ImageIcon(newImg));
        
        String etats="Etat: ";
        if(ta.getFaim()<25){
            etats+="affamé,";
        }else if(ta.getFaim()>75){
            etats+="Repus,";
        }
        
        if(ta.getSommeil()<25){
            etats+="fatigué,";
        }else if(ta.getSommeil()>85){
            etats+="Pleine forme,";
        }
        
        if(ta.getHygene()<33){
            etats+="sale,";
        }else if(ta.getHygene()>66){
            etats+="propre,";
        }
        
        if(ta.getBonheur()<33){
            etats+="triste.";
        }else if (ta.getBonheur()>66){
            etats+="joyeux.";
        }
        
        JLabel et=new JLabel(etats);
        
        quit=new JButton("Quitter la session.");
        save=new JButton("Sauvegarder.");
        
        add(n);
        add(et);
        add(lab);
        add(save);
        add(quit);
    }
    
    public JButton getQuit(){
        return this.quit;
    }
    
    public JButton getSave(){
       return this.save;
    }
    
    public JLabel getLab(){
       return this.lab;
    }
}
