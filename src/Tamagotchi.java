import java.util.UUID;
import java.util.Date;
/**
 * Notion d'un Tamagotchi.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */

public interface Tamagotchi
{
    /**
     * Retourne le nom du Tamagotchi.
     * @return Le nom du Tamagotchi.
     */
    public String getNom();
    
    public void setNom(String x);
    
    /**
     * Retourne la valeur de vie du Tamagotchi.
     * @return La vie du Tamagotchi.
     */
    public int getVie();
    
    /**
     * Modifie la valeur du vie du Tamagotchi.
     * @param v La nouvelle valeur du vie du Tamagotchi.
     */
    public void setVie(int v);
    
    /**
     * Retourne la valeur de nourriture du Tamagotchi.
     * @return La nourriture.
     */
    public int getFaim();
    
    /**
     * Modifie la valeur du nourriture du Tamagotchi.
     * @param n La nouvelle valeur du nourriture du Tamagotchi.
     */
    public void setFaim(int f);
    
    /**
     * Retourne la valeur d'energie du Tamagotchi.
     * @return L'energie.
     */
    public int getSommeil();
    
    /**
     * Modifie la valeur d'energie du Tamagotchi.
     * @param e La nouvelle valeur d'energie du Tamagotchi.
     */
    public void setSommeil(int s);
    
    /**
     * Retourne la valeur de l'hygene du Tamagotchi.
     * @return L'hygene.
     */
    public int getHygene();
    
    /**
     * Modifie la valeur d'hygene du Tamagotchi.
     * @param hy La nouvelle valeur d'hygene du Tamagotchi.
     */
    public void setHygene(int hy);
    
    /**
     * Retourne la valeur de Toilettes du Tamagotchi.
     * @return L'hygene.
     */
    public int getToilettes();
    
    /**
     * Modifie la valeur de Toilettes du Tamagotchi.
     * @param hy La nouvelle valeur de Toilettes du Tamagotchi.
     */
    public void setToilettes(int to);
    
    /**
     * Retourne la valeur de l'espece du Tamagotchi.
     * @return L'espece.
     */
    public int getEspece();
    
    /**
     * Retourne la Date de naissance du Tamagotchi.
     * @return date_de_naissance.
     */
    public Date getDateDeNaissance();
    
    public void setDateDeNaissance(Date dn);
    
    /**
     * Retourne la Date de la derniere session du Tamagotchi.
     * @return derniere_session.
     */
    public Date getDateDerniereSession();
    
    public void setDateDerniereSession(Date ds);
    
    /**
     * Retourne le chemin d'acces a l'avatar du Tamagotchi.
     * @return avatar.
     */
    public String getAvatar();
    
    /**
     * Retourne la valeur du bonheur du Tamagotchi.
     * @return Le bonheur.
     */
    public int getBonheur();
    
    /**
     * Modifie la valeur du bonheur du Tamagotchi.
     * @param he La nouvelle valeur du bonheur du Tamagotchi.
     */
    public void setBonheur(int he);
    
    public int getDormir();
    
    public void setDormir(int d);
    
    public void deplacer();
    
    public void nourrir();
    
    public void dormir();
    
    public void laver();
    
    public void aller_aux_toilettes();
    
    public void jouer();
    
    public String interact();
    
    public UUID getUUID();
    
    public void setUUID(UUID id);
}
