import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.UUID;
import java.util.Date;
/**
 * class SaveDirectoryTama.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class SaveDirectoryTama extends DAOTama {
    private File dir; 
    public SaveDirectoryTama(TamaFactory factory, File directory) throws IOException {
        super(factory);
        directory.mkdirs();
        this.dir = directory.getAbsoluteFile();
    }

    public File getDirectory() {
        return dir;
    }

    public UUID getUUIDFromFile(File f) {
        if(f == null) {
            return null;
        }
        String name = f.getName();
        if(name.startsWith("TM")) {
            try {
                UUID uuid = UUID.fromString(name.substring(2));
                return uuid;
            } catch(IllegalArgumentException  iaex) {
                return null;
            }
        } else {
            return null;
        }
    }

    private File getFileFromTama(Tamagotchi tama) {
        return getFileFromUUID(tama.getUUID());
    }

    private File getFileFromUUID(UUID uuid) {
        String name = "TM"+uuid.toString();
        return new File(dir,name);
    }

    protected void trueSave(Tamagotchi tama) throws IOException {
        File f = getFileFromTama(tama);
        try (FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeInt(tama.getDormir());
            oos.writeInt(tama.getNom().length());
            oos.writeChars(tama.getNom());
            oos.writeInt(tama.getVie());
            oos.writeLong(tama.getDateDeNaissance().getTime());
            oos.writeLong(tama.getDateDerniereSession().getTime());
            oos.writeInt(tama.getFaim());
            oos.writeInt(tama.getEspece());
            oos.writeInt(tama.getHygene());
            oos.writeInt(tama.getToilettes());
            oos.writeInt(tama.getBonheur());
            oos.writeInt(tama.getAvatar().length());
            oos.writeChars(tama.getAvatar());
            oos.close();
        }
    }

    protected Tamagotchi internalLoad(UUID uuid) throws IOException {
        File f = getFileFromUUID(uuid);
        if(f.exists() && f.isFile()) {
            try  (FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);) {
                Tamagotchi tm=new Lapin("","");
                String name = f.getName();
                tm.setUUID(UUID.fromString(name.substring(2)));
                int dormir = ois.readInt();
                int lengthNom = ois.readInt();
                String nom="";
                for(int i=0;i<lengthNom;i++){
                    nom+=ois.readChar();
                }
                
                int vit=ois.readInt();
                Date date_de_naissance = new Date(ois.readLong());
                Date derniere_session = new Date(ois.readLong());
                int duration =(int) ((((new Date().getTime()-derniere_session.getTime())/1000)/60)/60);
                if((duration>8)||(dormir==0)){
                    int fa=ois.readInt();
                    int e=ois.readInt();
                    int hyg=ois.readInt();
                    int toi=ois.readInt();
                    int bon=ois.readInt();
                    
                    int lengthAvatar = ois.readInt();
                    String avatar="";
                    for(int i=0;i<lengthAvatar;i++){
                        avatar+=ois.readChar();
                    }
                    //mettre les valeurs recupere dans tm ici.
                    switch(e){
                        case 0:
                            tm = new TamaRobot(nom,avatar);
                            break;
                        case 1:
                            tm = new Chat(nom,avatar);
                            break;
                        case 2:
                            tm = new Chien(nom,avatar);
                            break;
                        case 3:
                            tm = new Perroquet(nom,avatar);
                            break;
                        case 4:
                            tm = new Lapin(nom,avatar);
                            break;
                        case 5:
                            tm = new Hamster(nom,avatar);
                            break;
                    }
                    if(duration*5>fa){
                        tm.setVie(vit-((duration*5)-hyg));
                        tm.setHygene(0);
                    }else{
                        tm.setFaim(fa-(duration*5));
                    }
                    
                    if(duration*5>hyg){
                        tm.setVie(vit-((duration*5)-hyg));
                        tm.setHygene(0);
                    }
                    if(e!=0){
                        if(toi+(duration*5)<100){
                            tm.setToilettes(toi+(duration*5));
                        }else{
                            tm.setVie(vit-(100-(toi+(duration*5))));
                            tm.setToilettes(100);
                        }
                    }else{
                        tm.setToilettes(0);
                    }
                    if(duration*2>bon){
                        tm.setVie(vit-((duration*5)-bon));
                        tm.setBonheur(bon);
                    }
                    tm.setDormir(dormir);
                    tm.setDateDeNaissance(date_de_naissance);
                    tm.setDateDerniereSession(derniere_session);
                    ois.close();
                    return tm;
                }else{
                    return null;
                }
            }
        }
        return null;
    }

    public void internalDelete(Tamagotchi tama) throws IOException {
        File f = getFileFromTama(tama);
        f.delete();
    }

    public void internalDelete(UUID uuid) throws IOException {
        File f = getFileFromUUID(uuid);
        f.delete();
    }
    
    protected Tamagotchi internalLoad(File f) throws IOException {
        if(f.exists() && f.isFile()) {
            try  (FileInputStream fis = new FileInputStream(f);
            ObjectInputStream os = new ObjectInputStream(fis);) {
                Tamagotchi tm=new Lapin("","");
                String name = f.getName();
                tm.setUUID(UUID.fromString(name.substring(2)));
                int dormir = os.readInt();
                int lengthNom = os.readInt();
                String nom="";
                for(int i=0;i<lengthNom;i++){
                    nom+=os.readChar();
                }
                
                int vit=os.readInt();
                Date date_de_naissance = new Date(os.readLong());
                Date derniere_session = new Date(os.readLong());
                int duration =(int) ((((new Date().getTime()-derniere_session.getTime())/1000)/60)/60);
                if((duration>8)||(dormir==0)){
                    int fa=os.readInt();
                    int e=os.readInt();
                    int hyg=os.readInt();
                    int toi=os.readInt();
                    int bon=os.readInt();
                    
                    int lengthAvatar = os.readInt();
                    String avatar="";
                    for(int i=0;i<lengthAvatar;i++){
                        avatar+=os.readChar();
                    }
                    //mettre les valeurs recupere dans tm ici.
                    switch(e){
                        case 0:
                            tm = new TamaRobot(nom,avatar);
                            break;
                        case 1:
                            tm = new Chat(nom,avatar);
                            break;
                        case 2:
                            tm = new Chien(nom,avatar);
                            break;
                        case 3:
                            tm = new Perroquet(nom,avatar);
                            break;
                        case 4:
                            tm = new Lapin(nom,avatar);
                            break;
                        case 5:
                            tm = new Hamster(nom,avatar);
                            break;
                    }
                    if(duration*5>fa){
                        tm.setVie(vit-((duration*5)-fa));
                        tm.setFaim(0);
                    }else{
                        tm.setFaim(fa-(duration*5));
                    }
                    
                    if(duration*5>hyg){
                        tm.setVie(vit-((duration*5)-hyg));
                        tm.setHygene(0);
                    }
                    if(e!=0){
                        if(toi+(duration*5)<100){
                            tm.setToilettes(toi+(duration*5));
                        }else{
                            tm.setVie(vit-(100-(toi+(duration*5))));
                            tm.setToilettes(100);
                        }
                    }else{
                        tm.setToilettes(0);
                    }
                    if(duration*2>bon){
                        tm.setVie(vit-((duration*5)-bon));
                        tm.setBonheur(bon);
                    }
                    tm.setDormir(0);
                    tm.setDateDeNaissance(date_de_naissance);
                    tm.setDateDerniereSession(derniere_session);
                    os.close();
                    return tm;
                }
            }
        }
        return null;
    }
    
    public String fileInfo(File f){
        String res="";
        if(f.exists() && f.isFile()) {
        try  (FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);) {
                //recuperations des attributs ici
                int dormir=ois.readInt();
                
                int lengthNom = ois.readInt();
                String nom="";
                for(int i=0;i<lengthNom;i++){
                    nom+=ois.readChar();
                }
                int vit=ois.readInt();
                
                Date date_de_naissance = new Date(ois.readLong());
                res=res+nom+" "+date_de_naissance.toString();
                ois.close();
            } catch(IOException ioex) {
                ioex.printStackTrace();
            }
          return res;
        }
        return null;
    }
}