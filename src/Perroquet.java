
/**
 * Class Oiseau.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class Perroquet extends TamaAnimal
{
    /**
     * Constructeur d'objets de classe Oiseau
     */
    public Perroquet(String n, String a)
    {
        super(n, a, 3);
    }

    public String interact(){
        return "chirp chirp.";
    }
}
