package exoEngins;

public class Thermique extends Propulsion {
    /**
     * La valeur de perte énergétique propre à la propulsion thermique
     */
    private static double perte = 64.0;
    /**
     * La masse de ce moteur, i.e. cette instance de propulsion
     */
    private double masse;
    /**
     * Le carburant utilisé par ce moteur, i.e. cette instance de propulsion
     */
    private int carburant;

    public Thermique(double masse, int carburant) {
	this.masse = masse;
	this.carburant = carburant % (Carburant.nbCarburants);
    }

    /**
     * Calcule la consommation d'énergie selon la formule suivante :
     * 
     * soit B la valeur de base pour ce type de carburant, la consommation d'énergie
     * est égale à
     * 
     * <pre>
     * B * masse + perte
     * </pre>
     */
    public double consommerEnergie() {
	return Carburant.getConso(carburant) * masse + perte;
    }

    public double getMasse() {
	return masse;
    }

    public void setMasse(double masse) {
	this.masse = masse;
    }

    public static double getPerte() {
	return perte;
    }

    public static void setPerte(double perte) {
	Thermique.perte = perte;
    }

    public int getCarburant() {
	return carburant;
    }

    public void setCarburant(int carburant) {
	this.carburant = carburant % Carburant.nbCarburants;
    }

    @Override
    public String toString() {
	return "Thermique [masse=" + masse + ", carburant=" + Carburant.nom(carburant) + "]";
    }

}
