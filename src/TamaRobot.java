import java.util.UUID;
import java.util.Date;
/**
 * Class TamaRobot.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class TamaRobot extends TamaCreature
{
    private int charge;
    /**
     * Constructeur d'objets de classe TamaRobot
     */
    public TamaRobot(String n, String s)
    {
        super(n,s,0);
        charge=50;
    }
    
    public int getFaim(){
        return charge;
    }
    
    public void setFaim(int c){
        charge=c;
    }
    
    public void nourrir() {
        if(charge<85){
            charge += 15;
        }else{
            charge=100;
        }
    }

    public void laver() {
        if(getHygene() >= 50){
            setHygene(100);
        }
    }
    
    public void dormir(){
        sommeil=100;
        charge=100;
    }
    
    public void jouer(){
        sommeil-=5;
        charge-=5;
        bonheur+=10;
    }
    
    public int getToilettes(){
        return 0;
    }
    
    public void setToilettes(int to){}
    
    public void aller_aux_toilettes(){}
    
    public String interact(){
        return "Bip Bip.";
    }
}
