package exoEngins;

public class Flottant extends Engin {

    public Flottant(Propulsion propulsion) {
	this.setPropulsion(propulsion);
    }

    @Override
    public void conduire() {
	System.out.println(
		"(SIMULATION) Cet engin flottant est propulsé par un moteur " + getPropulsion().getClass().getSimpleName()
			+ ". Il consomme " + getPropulsion().consommerEnergie() + " énergie.");
    }

}
