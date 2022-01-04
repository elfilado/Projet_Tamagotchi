import java.util.UUID;
import java.util.WeakHashMap;
import java.io.IOException;

/**
 * Notion de DAO pour les Tamagotchis
 * Firas Fares, Manan Bhardwaj, Dorian El Filiali, Hugo de la Reberdiere.
 */
public abstract class DAOTama {
    private TamaFactory factory;
    private WeakHashMap<UUID,Tamagotchi> instanceMap;
    
    protected DAOTama(TamaFactory factory) {
        this.factory = factory;
        instanceMap = new WeakHashMap<UUID,Tamagotchi>();
    }
    
    public TamaFactory getFactory(){
        return factory;
    }
    /**
     * Enregistre le Tamagotchi en persistance.
     * Si le Tamagotchi n'était pas persistant, rend le Tamagotchi persistant.
     */
    public void save(Tamagotchi tama) throws IOException {
        if(tama == null) {
            throw new NullPointerException("Tamagotchi == null");
        }
        trueSave(tama);
        instanceMap.put(tama.getUUID(), tama);
    }
    
    protected abstract void trueSave(Tamagotchi tama) throws IOException;
    
    protected Tamagotchi getNumber(UUID id) {
        return instanceMap.get(id);
    }
    
    /**
     * Retrouve un Tamagotchi persisté à partir de son UUID
     * @param uuid identifiant
     * @return Le Tamagotchi correspondant à l'identifiant.
     */
    public Tamagotchi find(UUID uuid) throws IOException {
        Tamagotchi found = getNumber(uuid);
        if(found==null) {
            found = internalLoad(uuid);
            if(found != null) {
                instanceMap.put(found.getUUID(), found);
            }
        }
        return found;
    }
     
    protected abstract Tamagotchi internalLoad(UUID uuid) throws IOException;
    

    /**
     * Détruit la persistance du Tamagotchi.
     * Il restera en mémoire tant le ramasse-miette ne l'aura pas détruit
     * mais n'aura plus de persistance.
     * @param tama Le Tamagotchi ne devant plus être persistant.
     */
    public void delete(Tamagotchi tama) throws IOException {
        instanceMap.remove(tama.getUUID());
        internalDelete(tama);
    }

    protected abstract void internalDelete(Tamagotchi tama) throws IOException;
   
    /**
     * Détruit la persistance du Tamagotchi.
     * Il restera en mémoire tant le ramasse-miette ne l'aura pas détruit
     * mais n'aura plus de persistance.
     * @param id L'identifiant du Tamagotchi ne devant plus être persistant.
     */
    public void delete(UUID id) throws IOException{
        instanceMap.remove(id);
        internalDelete(id);
    }
    
    
    protected abstract void internalDelete(UUID id) throws IOException;
   
}