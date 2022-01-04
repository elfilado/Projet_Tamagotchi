import java.io.File;
/**
 * Class Main.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class Main
{
    public static void main(String[] args){
        try{
               TamaFactory factory=new TamaFactory();
        
               File homeDir = new File(System.getProperty("user.home"));
        
               final SaveDirectoryTama dir = new SaveDirectoryTama(factory, new File(homeDir, "TamagotchiSaveFiles"));
        
               MenuAcceuil accceuil=new MenuAcceuil();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}