package exoEngins;

public abstract class Carburant {
    public static final int ID_GAZOLE = 0, ID_ESSENCE = 1, ID_ETHANOL = 2;
    /**
     * Nombre de carburants possibles
     */
    public static int nbCarburants = 3;
    private static double consoGazole = 49.3, consoEssence = 47.1, consoEthanol = 44.3, consoParDefaut = 3.14;

    public static String nom(int id) {
	switch (id) {
	case ID_GAZOLE:
	    return "gazole";
	case ID_ESSENCE:
	    return "essence";
	case ID_ETHANOL:
	    return "ethanol";
	default:
	    return "non-carburant";
	}
    }
 
    public static double getConso(int carbu) {
	switch (carbu % nbCarburants) {
	case ID_GAZOLE:
	    return consoGazole;
	case ID_ESSENCE:
	    return consoEssence;
	case ID_ETHANOL:
	    return consoEthanol;
	default:
	    return consoParDefaut;
	}
    }
}
