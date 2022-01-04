import static org.junit.Assert.*;
import org.junit.Test;

//class de test des actions du Tamagotchi (JUnit 4)
public class TestTamagotchi {

    //test de la méthode nourrir
    //à la création du tamagotchi, l'attribut faim est à 50 et l'attribut sommeil est à 100
    //la méthode nourrir() doit ajouter 25 à l'attribut faim et doit retirer 5 à l'attribut sommeil
    @Test
    public void testNourrir1() {
        Chien t1 = new Chien("Manan", "TamaImages/Chien/1.jpeg");
        t1.nourrir();
        assertTrue(75 == t1.getFaim() && 95 == t1.getSommeil());
    }

    //test de la méthode nourrir
    //à la création du tamagotchi, l'attribut vitalite est à 100
    //on met l'attribut sommeil à 0
    //la méthode nourrir() doit alors retirer 5 à l'attribut vitalite
    @Test
    public void testNourrir2() {
        Chien t1 = new Chien("Manan", "TamaImages/Chien/1.jpeg");
        t1.setSommeil(0);
        t1.nourrir();
        int expected = 95;
        assertTrue(expected == t1.getVie());
    }

    //test de la méthode dormir
    //à la création du tamagotchi, l'attribut sommeil est à 100
    //on met l'attribut sommeil à 10
    //la méthode dormir() doit mettre l'attribut sommeil à 100
    @Test
    public void testDormir1() {
        Chien t1 = new Chien("Manan", "TamaImages/Chien/1.jpeg");
        int expected = 100;
        t1.setSommeil(10);
        t1.dormir();
        assertTrue(expected == t1.getSommeil());
    }

    //test de la méthode laver
    //à la création du tamagotchi, l'attribut hygiene est à 100
    //on met l'attribut hygiene à 40 (<= 50)
    //la méthode laver() doit mettre l'attribut hygiene à 100
    @Test
    public void testLaver1() {
        Chien t1 = new Chien("Manan", "TamaImages/Chien/1.jpeg");
        int expected = 100;
        t1.setHygene(40);
        t1.laver();
        assertTrue(expected == t1.getHygene());
    }

    //test de la méthode laver
    //à la création du tamagotchi, l'attribut hygiene est à 100
    //on met l'attribut hygiene à 70 (> 50)
    //la méthode laver() ne doit pas modifier la valeur de l'attribut hygiene
    @Test
    public void testLaver2() {
        Chien t1 = new Chien("Manan", "TamaImages/Chien/1.jpeg");
        int expected = 70;
        t1.setHygene(70);
        t1.laver();
        assertTrue(expected == t1.getHygene());
    }

    //test de la méthode aller_aux_toilettes()
    //à la création du tamagotchi, l'attribut toilettes est à 100
    //on met l'attribut toilettes à 60 (>= 50)
    //la méthode aller_aux_toilettes() doit mettre l'attribut toilettes à 0
    @Test
    public void testToilettes1() {
        Chien t1 = new Chien("Manan", "TamaImages/Chien/1.jpeg");
        int expected = 0;
        t1.setToilettes(60);
        t1.aller_aux_toilettes();
        assertTrue(expected == t1.getToilettes());
    }

    //test de la méthode aller_aux_toilettes()
    //à la création du tamagotchi, l'attribut toilettes est à 100
    //on met l'attribut toilettes à 20 (<= 50)
    //la méthode aller_aux_toilettes() ne doit pas modifier la valeur de l'attribut toilettes
    @Test
    public void testToilettes2() {
        Chien t1 = new Chien("Manan", "TamaImages/Chien/1.jpeg");
        int expected = 20;
        t1.setToilettes(20);
        t1.aller_aux_toilettes();
        assertTrue(expected == t1.getToilettes());
    }

    //test de la méthode jouer()
    //on met l' attribut sommeil à 50 (>= 5), faim à 50 (>= 5), bonheur à 50 (<= 90)
    //la méthode jouer() doit retirer 5 à sommeil, retirer 5 à faim et ajouter 10 à bonheur
    @Test
    public void testJouer1() {
        Chien t1 = new Chien("Manan", "TamaImages/Chien/1.jpeg");
        t1.setSommeil(50);
        t1.setFaim(50);
        t1.setBonheur(50);
        t1.jouer();
        assertTrue(45 == t1.getSommeil() && 45 == t1.getFaim() && 60 == t1.getBonheur());
    }

    //test de la méthode jouer()
    //à la création du tamagotchi, l'attribut vitalite est à 100
    //on met l' attribut sommeil à 2 (< 5), bonheur à 50 (<= 90)
    //la méthode jouer() doit retirer 5 à vitalite et retirer 5 à bonheur
    @Test
    public void testJouer2() {
        Chien t1 = new Chien("Manan", "TamaImages/Chien/1.jpeg");
        t1.setSommeil(2);
        t1.setBonheur(50);
        t1.jouer();
        assertTrue(95 == t1.getVie() && 45 == t1.getBonheur());
    }

    //test de la méthode jouer()
    //à la création du tamagotchi, l'attribut vitalite est à 100
    //on met l' attribut sommeil à 20 (>= 5), bonheur à 91 (> 90)
    //la méthode jouer() doit retirer 5 à sommeil, retirer 5 à faim et mettre bonheur à 100
    @Test
    public void testJouer3() {
        Chien t1 = new Chien("Manan", "TamaImages/Chien/1.jpeg");
        t1.setSommeil(50);
        t1.setFaim(50);
        t1.setBonheur(91);
        t1.jouer();
        assertTrue(45 == t1.getSommeil() && 45 == t1.getFaim() && 100 == t1.getBonheur());
    }

}
