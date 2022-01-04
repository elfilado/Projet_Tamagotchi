
/**
 * Classe Chien.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class Chien extends TamaAnimal
{
    /**
     * Constructeur d'objets de classe Chien
     */
    public Chien(String n, String a)
    {
        super(n, a, 2);
    }

    public String interact(){
        return "Wooof woof.";
    }
}
