package exoEngins;

public class Main {

    public static void main(String[] args) {
	Propulsion moteur = new Electrique(13);
	System.out.println(moteur);
	moteur = new Thermique(23, Carburant.ID_ETHANOL);
	System.out.println(moteur);
	Engin tractopelle = new Roulant(new Thermique(70, Carburant.ID_ETHANOL));
	System.out.println(tractopelle);
	tractopelle.conduire();
	Engin vaisseau = new Flottant(new Electrique(67));
	vaisseau.conduire();
    }

}
