import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Image;
/**
 * Class MenuAcceuil.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class MenuAcceuil
{
    private JFrame page;
    private JButton chargement, creation, quit;
    private JLabel bienvenu;
    private String imgpath="Pepe.png";
    /**
     * Constructeur de Menu d'Acceuil
     */
    public MenuAcceuil()
    {
        page=new JFrame("Bienvenu dans Tamagotchi!");
        page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        chargement=new JButton("Charger une session.");
        chargement.addActionListener(moveToTamaLoading);
        
        creation=new JButton("créer une nouvelle session.");
        creation.addActionListener(moveToTamaCreation);
        
        quit = new JButton("Quitter");
        quit.addActionListener(close);
        
        bienvenu=new JLabel("Bienvenu cher utilisateur!");
        
        page.getContentPane().add(bienvenu, BorderLayout.NORTH);
        page.getContentPane().add(chargement, BorderLayout.EAST);
        page.getContentPane().add(creation, BorderLayout.WEST);
        page.getContentPane().add(new JLabel(new ImageIcon(imgpath)), BorderLayout.CENTER);
        page.getContentPane().add(quit, BorderLayout.SOUTH);
        
        page.pack();
        page.setVisible(true);
    }
    
    Action moveToTamaCreation = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                page.setVisible(false);
                MenuCreation menuC=new MenuCreation();
                page.dispose();
            }
        };
        
        
    Action moveToTamaLoading = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            page.setVisible(false);
            MenuChargement menuCh=new MenuChargement();
            page.dispose();
        }
    };
    
    Action close = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            page.dispose();
        }
    };
}
