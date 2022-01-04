
/**
 * class Chat.
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public class Chat extends TamaAnimal
{
    /**
     * Constructeur d'objets de classe chat
     */
    public Chat(String n, String a)
    {
        super(n, a, 1);
    }

    public String interact(){
        return "meaow meaow!";
    }
}
