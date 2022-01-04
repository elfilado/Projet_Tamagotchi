import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.text.*;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * Class JTextFieldLimit.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
class JTextfieldLimit extends PlainDocument{
    private int limit;
    JTextfieldLimit(int limit){
        super();
        this.limit=limit;
    }
    JTextfieldLimit(int limit, boolean upper){
        super();
        this.limit = limit;
    }
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
        if(str == null){
            return;
        }
        if((getLength()+str.length()) <= limit){
            super.insertString(offset, str, attr);
        }
    }
}

/**
 * Class MenuCreation.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class MenuCreation
{
    private JFrame pageC;
    private JComboBox especes;
    private JTextField name;
    private JLabel debut;
    private Buttons buttons;
    private String imgPath="TamaImages/Lapin/1.jpeg";
    private JLabel lab;
    private int imageIndex;
    private String[] especesTama={"Lapin","Oiseau","Chien","Chat","Hamster", "Robot"};
    /**
     * Constructeur d'objets de classe MenuCreation
     */
    public MenuCreation()
    {
        
        pageC=new JFrame("Creation du Tamagotchi!");
        
        especes=new JComboBox(especesTama);
        especes.addActionListener(changeEspeces);
        
        debut=new JLabel("Choisissez le nom de votre nouveau compagnon : ");
        
        name=new JTextField(15);
        name.setDocument(new JTextfieldLimit(12));
        
        imageIndex=1;
        
        Buttons buttons=new Buttons();
        
        buttons.getRevenir().addActionListener(moveToMenuAcceuil);
        buttons.getSuivant().addActionListener(moveToNextImage);
        buttons.getPrecedent().addActionListener(moveToPreviousImage);
        buttons.getConfirmer().addActionListener(confirmCreation);
        
        Image img= new ImageIcon(imgPath).getImage();
        Image newImg= img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        lab=new JLabel(new ImageIcon(newImg));
        
        pageC.getContentPane().add(debut,BorderLayout.NORTH);
        pageC.getContentPane().add(name, BorderLayout.WEST);
        pageC.getContentPane().add(especes, BorderLayout.EAST);
        pageC.getContentPane().add(lab, BorderLayout.CENTER);
        pageC.getContentPane().add(buttons, BorderLayout.SOUTH);
        
        pageC.pack();
        pageC.setVisible(true);
    }
    
    Action moveToMenuAcceuil = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pageC.setVisible(false);
                MenuAcceuil menuA=new MenuAcceuil();
                pageC.dispose();
            }
    };
    
    Action moveToPreviousImage = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pageC.remove(lab);
                if(imageIndex<=1){
                    imageIndex=4;
                }else{
                    imageIndex--;
                }
                imgPath="TamaImages/"+especes.getSelectedItem()+"/"+imageIndex+".jpeg";
                
                Image img= new ImageIcon(imgPath).getImage();
                Image newImg= img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                lab=new JLabel(new ImageIcon(newImg));
                
                pageC.getContentPane().add(lab, BorderLayout.CENTER);
                pageC.pack();
            }
        };
    
   Action moveToNextImage = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pageC.remove(lab);
                if(imageIndex>=4){
                    imageIndex=1;
                }else{
                    imageIndex++;
                }
                imgPath="TamaImages/"+especes.getSelectedItem()+"/"+imageIndex+".jpeg";
                
                Image img= new ImageIcon(imgPath).getImage();
                Image newImg= img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                lab=new JLabel(new ImageIcon(newImg));
                
                pageC.getContentPane().add(lab, BorderLayout.CENTER);
                pageC.pack();
            }
        };
   
   Action confirmCreation = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            if(name.getText().length()>0){
                pageC.setVisible(false);
                Tamagotchi tc=new Lapin(name.getText(),imgPath);
                switch(especes.getSelectedItem().toString()){
                    case "Lapin":
                        tc= new Lapin(name.getText(),imgPath);
                        break;
                    case "Perroquet":
                        tc = new Perroquet(name.getText(),imgPath);
                        break;
                    case "Chien" :
                        tc = new Chien(name.getText(),imgPath);
                        break;
                    case "Hamster":
                        tc = new Hamster(name.getText(),imgPath);
                        break;
                    case "Chat":
                        tc = new Chat(name.getText(),imgPath);
                        break;
                    case "Robot":
                        tc = new TamaRobot(name.getText(),imgPath);
                        break;
                }
                MenuJeu mc= new MenuJeu(tc);
                pageC.dispose();
            }else{
                String message = "Veuillez entrer un nom pour votre ami!";
                JOptionPane.showMessageDialog(null, message);
            }
        }
   };
   
   Action changeEspeces = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            pageC.remove(lab);
            imgPath="TamaImages/"+especes.getSelectedItem()+"/1.jpeg";
            
            Image img= new ImageIcon(imgPath).getImage();
            Image newImg= img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            lab=new JLabel(new ImageIcon(newImg));
            
            pageC.getContentPane().add(lab,BorderLayout.CENTER);
            pageC.pack();
        }
    };
    
   public String getImgPath(){
       return this.imgPath;
   }
}
