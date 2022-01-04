
/**
 * Class Lapin.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class Lapin extends TamaAnimal
{
    /**
     * Constructeur d'objets de classe Lapin
     */
    public Lapin(String n, String a)
    {
        super(n, a, 4);
    }

    public String interact(){
        return "clapi clapi.";
    }
}
