import java.util.Date;
import java.util.UUID;
/**
 * Class TamaAnimal.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public abstract class TamaAnimal extends TamaCreature
{
    private int faim;
    private int toilette;
    /**
     * Constructeur d'objets de classe TamaAnimal
     */
    public TamaAnimal(String n, String a, int e)
    {
        super(n,a,e);
        faim = 50;
        toilette=50;
    }
   
    public int getFaim(){
        return this.faim;
    }
    
    public void setFaim(int f){
        this.faim= f;
    }
    
    public int getToilettes(){
        return this.toilette;
    }
    
    public void setToilettes(int to){
        this.toilette=to;
    }
    
    public void nourrir() {
        if(faim <=75){
            faim+=25;
        }
        if(sommeil>=10){
            sommeil-=5;
        }else{
            sommeil=0;
            if(vitalite>=5){
                vitalite-=5;
            }else{
                vitalite=0;
            }
        }
    }

    public void dormir() {
        sommeil=100;
    }

    public void laver() {
        if(getHygene() <= 50){
            hygene=100;
        }
    }

    public void aller_aux_toilettes(){
        if(toilette >= 50){
            toilette = 0;
        }
    }
    
    public void jouer(){
        if((sommeil>=5)&&(faim>=5)){
            sommeil-=5;
            faim-=5;
            if(bonheur<=90){
                bonheur+=10;
            }else{
                bonheur=100;
            }
        }else{
            vitalite-=5;
            bonheur-=5;
        }
    }
    
    public abstract String interact();
}
