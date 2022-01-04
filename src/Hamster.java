
/**
 * Class Hamster.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class Hamster extends TamaAnimal
{
    /**
     * Constructeur d'objets de classe Hamster
     */
    public Hamster(String n, String a)
    {
       super(n, a, 5);
    }

    public String interact(){
        return "ham ham!";
    }
}
