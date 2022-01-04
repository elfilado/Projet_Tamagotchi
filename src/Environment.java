import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.Calendar;
import java.util.Date;
import java.applet.Applet;
/**
 * Class Environment.
 * Firas Fares, Dorian El Filali, Manan Bhardwaj, Hugo de la Reberdiere
 */

public class Environment extends Applet{
    private String locationMap="House/Salon.jpeg";
    private JLabel temps, lab;
    
    private final String[] salon={"Salon","Cuisine","Jardin","Chambre"};
    private final String[] cuisine={"Cuisine", "Salon"};
    private final String[] jardin={"Jardin", "Salon"};
    private final String[] chambre={"Chambre", "Salon", "Salle de bain"};
    private final String[] salle_de_bain={"Salle de bain", "Chambre", "Toilettes"};
    private final String[] Toilettes={"Toilettes", "Salle de bain"};
    
    private JButton action, interact;
    private JComboBox emplacements;
    public Environment(){
        setLayout(new GridLayout(7,1));

        Image img= new ImageIcon(locationMap).getImage();
        Image newImg= img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        lab=new JLabel(new ImageIcon(newImg));

        Date d=new Date();
        Calendar cal=Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        
        temps = new JLabel(" Moment de la journée : Nuit ");
        if(hour>7) {
            if(hour<11) {
                temps.setText(" Moment de la journée: Matin ");
            }else{
                if(hour < 17){
                    temps.setText(" Moment de la journée : Journée ");
                }else{
                    if(hour < 21){
                        temps.setText(" Moment de la journée : Soir ");
                    }
                }
            }
        }
        
        JLabel emplab= new JLabel("Emplacement: ");
        emplacements=new JComboBox(salon);
        JLabel actlab= new JLabel("Action: ");
        action = new JButton("Jouer");
        interact = new JButton("Interagir");
        
        add(lab);
        add(temps);
        add(emplab);
        add(emplacements);
        add(actlab);
        add(action);
        add(interact);
    }
    public Environment(String s){
        setLayout(new GridLayout(7,1));
        setNewEmplacementIcon(s);
        
        temps = new JLabel();
        updateTemps();
        updateList(s);
        
        
        JLabel emplab= new JLabel("Emplacement: ");
        JLabel actlab= new JLabel("Action: ");
        interact = new JButton("Interagir");
        
        add(lab);
        add(temps);
        add(emplab);
        add(emplacements);
        add(actlab);
        add(action);
        add(interact);
    }
    
    public void setNewEmplacementIcon(String s){
        this.locationMap="House/"+s+".jpeg";
        Image img= new ImageIcon(locationMap).getImage();
        Image newImg= img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        lab=new JLabel(new ImageIcon(newImg));
        switch(s){
            case "Chambre":
                this.action = new JButton("Dormir");
                break;
            case "Cuisine":
                this.action = new JButton("Nourrir");
                break;
            case "Jardin":
            case "Salon":
                this.action = new JButton("Jouer");
                break;
            case "Salle de bain":
                this.action = new JButton("Nettoyer");
                break;
            case "Toilettes":
                this.action = new JButton("Aller aux toilettes");
                break;
        }
    }
    
    public void updateTemps(){
        Date d=new Date();
        Calendar cal=Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        
        if(hour>7) {
            if(hour<11) {
                temps.setText("Moment de la journée: Matin");
            }else{
                if(hour < 17){
                    temps.setText("Moment de la journée : Journée.");
                }else{
                    if(hour < 21){
                        temps.setText("Moment de la journée : Soir");
                    }
                }
            }
        }else{
            temps.setText("Moment de la journée : nuit.");
        }
    }
    
    public void updateList(String s){
        switch(s){
            case "Salon":
                this.emplacements = new JComboBox(salon);
                break;
            case "Chambre":
                this.emplacements = new JComboBox(chambre);
                break;
            case "Cuisine":
                this.emplacements = new JComboBox(cuisine);
                break;
            case "Jardin":
                this.emplacements = new JComboBox(jardin);
                break;
            case "Salle de bain":
                this.emplacements = new JComboBox(salle_de_bain);
                break;
            case "Toilettes":
                this.emplacements = new JComboBox(Toilettes);
                break;
        }
    }
    
    public JComboBox getEmplacements(){
        return this.emplacements;
    }
    
    public JButton getAction(){
        return this.action;
    }
    
    public JButton getInteract(){
        return this.interact;
    }
}