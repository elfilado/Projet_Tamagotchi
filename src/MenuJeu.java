import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import java.io.IOException;
/**
 *
 */
public class MenuJeu
{
    private JFrame pageJ;
    private Tamagotchi t;
    private SaveDirectoryTama directoryTama;
    private DAOTama dao;
    private InfoGenerales inf;
    private Environment env;
    private Etats et;
    private int current_hour;
    /**
     * Constructeur d'objets de classe MenuJeu
     */
    public MenuJeu(Tamagotchi ta)
    {
        t=ta;
        try{
            directoryTama = new SaveDirectoryTama(new TamaFactory(), new File(new File(System.getProperty("user.home")), "TamagotchiSaveFiles"));
            dao = directoryTama;
        }catch(Exception ex){
            ex.printStackTrace();
        }  
        env= new Environment();
        env.getEmplacements().addActionListener(moveToANewLocation);
        env.getAction().addActionListener(agir);
        env.getInteract().addActionListener(interact);
        
        Calendar cal=Calendar.getInstance();
        current_hour = cal.get(Calendar.HOUR_OF_DAY);
        
        
        et= new Etats(ta);
        inf= new InfoGenerales(ta);
        inf.getQuit().addActionListener(moveToMenuAcceuil);
        inf.getSave().addActionListener(save);
        pageJ= new JFrame("Partie en cours!");
        pageJ.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        pageJ.getContentPane().add(env,BorderLayout.EAST);
        pageJ.getContentPane().add(et, BorderLayout.CENTER);
        pageJ.getContentPane().add(inf, BorderLayout.WEST);
        
        pageJ.pack();
        pageJ.setVisible(true);
    }
    
    public MenuJeu(Tamagotchi ta, File f)
    {
        t=ta;
        try{
            directoryTama = new SaveDirectoryTama(new TamaFactory(), new File(new File(System.getProperty("user.home")), "TamagotchiSaveFiles"));
            dao = directoryTama;
            f.delete();
        }catch(Exception ex){
            ex.printStackTrace();
        }  
        env= new Environment();
        env.getEmplacements().addActionListener(moveToANewLocation);
        env.getAction().addActionListener(agir);
        env.getInteract().addActionListener(interact);
        
        Calendar cal=Calendar.getInstance();
        current_hour = cal.get(Calendar.HOUR_OF_DAY);
        
        
        et= new Etats(ta);
        inf= new InfoGenerales(ta);
        inf.getQuit().addActionListener(moveToMenuAcceuil);
        inf.getSave().addActionListener(save);
        pageJ= new JFrame("Partie en cours!");
        pageJ.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        pageJ.getContentPane().add(env,BorderLayout.EAST);
        pageJ.getContentPane().add(et, BorderLayout.CENTER);
        pageJ.getContentPane().add(inf, BorderLayout.WEST);
        
        pageJ.pack();
        pageJ.setVisible(true);
    }
    
    public void checkTime(){
        Calendar cal=Calendar.getInstance();
        int hr = cal.get(Calendar.HOUR_OF_DAY);
        if(hr!=current_hour){
            if(t.getFaim()>=5){
                t.setFaim(t.getFaim()-5);
            }else{
                t.setFaim(0);
                t.setVie(t.getVie()-5);
            }
            if(t.getSommeil()>5){
                t.setSommeil(t.getSommeil()-5);
            }else{
                t.setSommeil(0);
                t.setVie(t.getVie()-5);
            }
            if(t.getHygene()>=2){
                t.setHygene(t.getHygene()-2);
            }else{
                t.setHygene(0);
                t.setVie(t.getVie()-2);
            }
            if(t.getToilettes()<=10){
                t.setToilettes(t.getToilettes()+10);
            }else{
                t.setToilettes(100);
            }
            if(t.getBonheur()>=2){
                t.setBonheur(t.getBonheur()-2);
            }else{
                t.setBonheur(0);
                t.setVie(t.getVie()-2);
            }
        }
        current_hour=hr;
    }
    
    public void game_over(){
        if(t.getVie()==0){
            String message = "La vitalité de votre ami est trop basse, il a donc décidé de partir en vacances et de ne jamais revenir...";
            JOptionPane.showMessageDialog(null, message);
            try{
                dao.delete(t);
            }catch(IOException ioex){
                ioex.printStackTrace();
            }
            pageJ.dispose();
            MenuAcceuil menuA=new MenuAcceuil();
        }
    }
    
    Action moveToMenuAcceuil = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            pageJ.setVisible(false);
            MenuAcceuil menuA=new MenuAcceuil();
            pageJ.dispose();
        }
    };
    
    Action moveToANewLocation = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            game_over();
            if(t.getSommeil()>=5){
                checkTime();
                String newPlace = env.getEmplacements().getSelectedItem().toString();
                
                pageJ.remove(env);
                pageJ.remove(et);
                
                t.deplacer();
                
                Etats et2= new Etats(t);
                Environment env2 = new Environment(newPlace);
                
                env=env2;
                et=et2;
                
                env.getEmplacements().addActionListener(moveToANewLocation);
                env.getAction().addActionListener(agir);
                env.getInteract().addActionListener(interact);
                
                pageJ.getContentPane().add(env,BorderLayout.EAST);
                pageJ.getContentPane().add(et, BorderLayout.CENTER);
                pageJ.revalidate(); 
        
                game_over();
            }else{
                JOptionPane.showMessageDialog(null, "Votre ami ne peut pas se deplacer, il est trop fatigué. Il se dirige vers sa chambre l'air abbatu.");
                pageJ.remove(env);
                pageJ.remove(et);
                Environment env2 = new Environment("Chambre");
                t.dormir();
                Etats et2= new Etats(t);
                et=et2;
                t.setDormir(1);
                pageJ.dispose();
                try{
                    dao.save(t);
                }catch(IOException ioex){
                    ioex.printStackTrace();
                }
                MenuAcceuil me = new MenuAcceuil();
            }
        }
    };
    
    Action agir = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            game_over();
            switch(env.getAction().getText()){
                case "Dormir":
                    if(t.getSommeil()<25){
                        t.dormir();
                        t.setDormir(1);
                        JOptionPane.showMessageDialog(null, "Votre ami dort, veuillez retourner plus tard.");
                        try{
                            dao.save(t);
                        }catch(IOException ioex){
                            ioex.printStackTrace();
                        }
                        pageJ.dispose();
                        MenuAcceuil me = new MenuAcceuil();
                    }else{
                        JOptionPane.showMessageDialog(null, "Votre ami a encore d'energie, il ne souhaite pas dormir.");
                    }
                    break;
                case "Nourrir":
                    if(t.getSommeil()>5){
                        t.nourrir();
                    }else{
                        JOptionPane.showMessageDialog(null, "Votre ami ne peut pas manger, il est trop fatigué. Il se dirige vers sa chambre l'air abattu.");
                        t.setDormir(1);
                        pageJ.dispose();
                        try{
                            dao.save(t);
                        }catch(IOException ioex){
                            ioex.printStackTrace();
                        }
                        MenuAcceuil me = new MenuAcceuil();
                    }
                    break;
                case "Jouer":
                    if(t.getSommeil()>5){
                        t.jouer();
                    }else{
                        JOptionPane.showMessageDialog(null, "Votre ami ne peut pas jouer, il est trop fatigué. Il se dirige vers sa chambre l'air abattu.");
                        t.setDormir(1);
                        pageJ.dispose();
                        try{
                            dao.save(t);
                        }catch(IOException ioex){
                            ioex.printStackTrace();
                        }
                        MenuAcceuil me = new MenuAcceuil();
                    }
                    break;
                case "Nettoyer":
                    t.laver();
                    break;
                case "Aller aux toilettes":
                    t.aller_aux_toilettes();
                    break;
            }
            game_over();
            checkTime();
            pageJ.remove(et);
            pageJ.remove(inf);
            InfoGenerales i = new InfoGenerales(t);
            inf = i;
            Etats et2= new Etats(t);
            et=et2;
            inf.getQuit().addActionListener(moveToMenuAcceuil);
            inf.getSave().addActionListener(save);
            pageJ.getContentPane().add(et, BorderLayout.CENTER);
            pageJ.getContentPane().add(inf, BorderLayout.WEST);
            pageJ.revalidate();         
        }
    };
    
    Action interact = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            game_over();
            checkTime();
            JOptionPane.showMessageDialog(null, t.interact());
            try{
                dao.delete(t);
                dao.save(t);
            }catch(IOException ioex){
                ioex.printStackTrace();
            }
        }
    };
    
    Action save = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            try{
                dao.save(t);
            }catch(IOException ioex){
                ioex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Tamagotchi sauvegardé.");
        }
    };
}
