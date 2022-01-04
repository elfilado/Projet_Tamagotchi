import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.UUID;
/**
 * classe MenuChargement.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class MenuChargement
{
    private JFrame pageCh;
    private JButton confirmer, revenir;
    private JComboBox tamas;
    private SaveDirectoryTama directoryTama;
    private DAOTama dao;
    private String[] nomTamas;
    private File[] tamasFichiers;
    /**
     * Constructeur d'objets de classe MenuChargement
     */
    public MenuChargement()
    {
        try{
            directoryTama = new SaveDirectoryTama(new TamaFactory(), new File(new File(System.getProperty("user.home")), "TamagotchiSaveFiles"));
            dao = directoryTama;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        pageCh = new JFrame("Chargement d'un Tamagotchi!");
        
        confirmer = new JButton("Charger la partie");
        confirmer.addActionListener(confirmChargement);
        
        revenir = new JButton("Revenir au menu d'acceuil");
        revenir.addActionListener(moveToMenuAcceuil);
        
        JLabel lab= new JLabel("Choissisez votre ami!");
        
        
        tamasFichiers = directoryTama.getDirectory().listFiles();
        nomTamas = new String[tamasFichiers.length];
        if(nomTamas.length>0){
            for(int i=0;i<tamasFichiers.length;i++){
                nomTamas[i]=directoryTama.fileInfo(tamasFichiers[i]);
            }
        }
        
        tamas = new JComboBox(nomTamas);
        
        pageCh.getContentPane().add(lab, BorderLayout.NORTH);
        pageCh.getContentPane().add(revenir, BorderLayout.EAST);
        pageCh.getContentPane().add(confirmer, BorderLayout.WEST);
        pageCh.getContentPane().add(tamas, BorderLayout.CENTER);
        
        pageCh.pack();
        pageCh.setVisible(true);
    }
    
    Action moveToMenuAcceuil = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pageCh.setVisible(false);
                MenuAcceuil menuA=new MenuAcceuil();
                pageCh.dispose();
            }
    };
    
    Action confirmChargement = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            for(int i=0;i<nomTamas.length;i++){
                if(tamas.getSelectedItem().toString().equals(nomTamas[i])){
                    try{    
                        Tamagotchi tm = dao.find(UUID.fromString(tamasFichiers[i].getName().substring(2)));
                        if(tm != null){
                            pageCh.setVisible(false);
                            MenuJeu menuJ=new MenuJeu(tm, tamasFichiers[i]);
                            pageCh.dispose();
                        }else{
                            JOptionPane.showMessageDialog(null, "Votre ami dort encore, revenez plus tard.");
                        }
                        
                    }catch(IOException ioex){
                        ioex.printStackTrace();
                    }
                }
            }
        }
    };
}
