package exoEngins;

public class Electrique extends Propulsion {
    /**
     * La valeur de consommation énergétique de base propre à la propulsion
     * électrique
     */
    private static double baseConso = 3.14;

    /**
     * La valeur de perte énergétique propre à la propulsion thermique
     */
    private static double perte = 62.0;
    /**
     * La masse de cette instance
     */
    private double masse;

    public Electrique(double poids) {
	this.masse = poids;
    }

    public double consommerEnergie() {
	return baseConso * masse + perte;
    }

    public double getMasse() {
	return masse;
    }

    public void setMasse(double masse) {
	this.masse = masse;
    }

    public static double getBaseConso() {
	return baseConso;
    }

    public static void setBaseConso(double baseConso) {
	Electrique.baseConso = baseConso;
    }

    public static void setPerte(double perte) {
	Electrique.perte = perte;
    }

    public static double getPerte() {
	return perte;
    }

    @Override
    public String toString() {
	return "Electrique [masse=" + masse + "]";
    }
}
