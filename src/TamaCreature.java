import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;
/**
 * classe abstraite TamaCreature.
 *
 */
public abstract class TamaCreature implements Tamagotchi
{
    protected UUID id;
    protected String nom, humeur, avatar, localisation;
    protected Date date_de_naissance, derniere_session;
    protected int vitalite, sommeil, hygene, bonheur, espece, dormir;
    public TamaCreature(String n, String a, int e){
        id = UUID.randomUUID();
        nom=n;
        date_de_naissance=new Date();
        derniere_session=date_de_naissance;
        vitalite = 100;
        hygene = 100;
        sommeil = 100;
        bonheur = 50;
        dormir=0;
        espece = e;
        humeur = "Normal";
        localisation = "Salon";
        avatar=a;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String n){
        nom=n;
    }
    
    public int getEspece(){
        return espece;
    }
    
    public Date getDateDeNaissance(){
        return date_de_naissance;
    }
    
    public void setDateDeNaissance(Date ds){
        date_de_naissance=ds;
    }

    public Date getDateDerniereSession(){
        return derniere_session;
    }
    
    public void setDateDerniereSession(Date ds){
        derniere_session = ds;
    }
    
    public int getVie() {
        return vitalite;
    }

    public void setVie(int v) {
        vitalite = v;
    }
    
    public int getSommeil() {
        return sommeil;
    }

    public void setSommeil(int s) {
        sommeil = s;
    }

    public int getHygene() {
        return hygene;
    }

    public void setHygene(int hy) {
        hygene = hy;
    }

    public int getBonheur() {
        return bonheur;
    }

    public void setBonheur(int he) {
        bonheur = he;
    }
    
    public String getAvatar(){
        return avatar;
    }

    public UUID getUUID() {
        return id;
    }
    
    public void setUUID(UUID idn){
        this.id=idn;
    }
    
    public void deplacer(){
        if(sommeil>=5){
            sommeil-=5;
        }
    }
    
    public int getDormir(){
        return dormir;
    }
    
    public void setDormir(int d){
        dormir=d;
    }
    
    public abstract void nourrir();

    public abstract void laver();

    public abstract String interact();
}
