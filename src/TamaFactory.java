
/**
 * Class TamaFoctory .
 */
public class TamaFactory
{
    
    public Chien createTamaChien(String n, String s){
        return new Chien(n, s);
    }
    
    public Chat createTamaChat(String n, String s){
        return new Chat(n, s);
    }
    
    public Lapin createTamaLapin(String n, String s){
        return new Lapin(n, s);
    }
    
    public Hamster createTamaHamster(String n, String s){
        return new Hamster(n, s);
    }
    
    public Perroquet createTamaOiseau(String n, String s){
        return new Perroquet(n, s);
    }
    
    public TamaRobot createTamaRobot(String n, String s){
        return new TamaRobot(n, s);
    }
}
