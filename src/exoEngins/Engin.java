package exoEngins;

public abstract class Engin {
    /**
     * Le dernier identifiant utilisé
     */
    private static int dernierId = 0;
    /**
     * L'identifiant de cet engin
     */
    private int id;
    /**
     * Le type de propulsion de cet engin
     */
    private Propulsion propulsion;

    public Engin() {
	id = ++dernierId;
    }

    /**
     * Permet de conduire cet engin
     */
    public abstract void conduire();

    public static int getDernierId() {
	return dernierId;
    }

    public Propulsion getPropulsion() {
	return propulsion;
    }

    public void setPropulsion(Propulsion propulsion) {
	this.propulsion = propulsion;
    }

    public int getId() {
	return id;
    }

    @Override
    public String toString() {
	return this.getClass().getSimpleName() + " [id=" + id + "]";
    }
}
