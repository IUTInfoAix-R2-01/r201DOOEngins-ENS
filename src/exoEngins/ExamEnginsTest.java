package exoEngins;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class ExamEnginsTest {

//    private Marker marker = new Marker();

    @Before
    public void reset() {
	Electrique.setBaseConso(3.14);
	Electrique.setPerte(62.0);
	Thermique.setPerte(64.0);
    }

    @Test
    public void testValeurGazole() {
	int val = Carburant.ID_GAZOLE;
	int valGazole = 0;
	assertEquals(valGazole, val);
//	marker.mark++;
//	System.out.println("Mark: " + marker.mark);
    }

    @Test
    public void testGetConsoGazole() {
	double val = Carburant.getConso(Carburant.ID_GAZOLE);
	double valAttendue = 49.3;
	assertEquals(valAttendue, val, 0);
    }

    @Test
    public void testNbCarburants() {
	int val = Carburant.nbCarburants;
	int valAttendue = 3;
	assertEquals(valAttendue, val);
    }

    @Test
    public void testNomGazole() {
	String val = Carburant.nom(Carburant.ID_GAZOLE);
	String valAttendue = "gazole";
	assertEquals(val, valAttendue);
    }

    @Test
    public void testMasseConstructionPropulsionThermiqueEssence() {
	double masseAttendue = 13.5;
	int carburantAttendu = Carburant.ID_ESSENCE;
	Propulsion propulsion = new Thermique(masseAttendue, carburantAttendu);
	// On vérifie que les attributs sont bien initialisés
	double masseObservee = ((Thermique) propulsion).getMasse();
	assertEquals(masseAttendue, masseObservee, 0.0);
    }

    @Test
    public void testEoliennetoString() {
        Eolienne propulsion = new Eolienne();
        String valAttendue = "Eolienne []";
        assertEquals(valAttendue, propulsion.toString());
    }

    @Test
    public void testElectriqueToString() {
        Electrique propulsion = new Electrique(49.3);
        String ValAttendue = "Electrique [masse=49.3]";
        assertEquals(ValAttendue, propulsion.toString());
    }

    @Test
    public void testThermiqueToString() {
        Thermique propulsion = new Thermique(49.3, Carburant.ID_GAZOLE);
        String ValAttendue = "Thermique [masse=49.3, carburant=gazole]";
        assertEquals(ValAttendue, propulsion.toString());
    }

    @Test
    public void testConstructionPropulsionThermiqueEssenceCarburant() {
	double masseAttendue = 13.5;
	int carburantAttendu = Carburant.ID_ESSENCE;
	Propulsion propulsion = new Thermique(masseAttendue, carburantAttendu);
	// On vérifie que les attributs sont bien initialisés
	int carburantObserve = ((Thermique) propulsion).getCarburant();
	assertEquals(carburantAttendu, carburantObserve);
    }

    @Test
    public void testConstructionPropulsionThermiqueCarburantIllegal() {
	double masseAttendue = 13.5;
	int carburantIllegal = 5;
	int carburantAttendu = carburantIllegal % Carburant.nbCarburants;
	// la construction avec un carburant illegal ne doit pas echouer mais ramener la
	// valeur dans l'intervalle autorise
	Thermique propulsion = new Thermique(masseAttendue, carburantIllegal);
	int carburantObserve = propulsion.getCarburant();
	assertEquals(carburantAttendu, carburantObserve);
    }

    @Test
    public void testThermiqueSetCarburantIllegal() {
	double masseAttendue = 13.5;
	int carburantIllegal = 5;
	int carburantAttendu = carburantIllegal % Carburant.nbCarburants;
	// la construction avec un carburant illegal ne doit pas echouer mais ramener la
	// valeur dans l'intervalle autorise
	Thermique propulsion = new Thermique(masseAttendue, Carburant.ID_GAZOLE);
	propulsion.setCarburant(carburantIllegal);
	int carburantObserve = propulsion.getCarburant();
	assertEquals(carburantAttendu, carburantObserve);
    }

    @Test
    public void testThermiquePerteStatic() {
	double valAttendue = 64.0;
	double valObservee = Thermique.getPerte();
	assertEquals(valAttendue, valObservee, 0.0);
    }

    @Test
    public void testThermiqueSetPerteStatic() {
	double valAttendue = 68.0;
	Thermique.setPerte(valAttendue);
	double valObservee = Thermique.getPerte();
	assertEquals(valAttendue, valObservee, 0.0);
    }

    @Test
    public void testThermiqueConsommerEnergie() {
	double masse = 49.3;
	double valAttendue = Carburant.getConso(Carburant.ID_GAZOLE) * masse + Thermique.getPerte();
	Thermique propulsion = new Thermique(masse, Carburant.ID_GAZOLE);
	double valObservee = propulsion.consommerEnergie();
	assertEquals(valAttendue, valObservee, 0.1);
    }

    @Test
    public void testElectriqueConsommerEnergie() {
	double masse = 49.3;
	double valAttendue = Electrique.getBaseConso() * masse + Electrique.getPerte();
	Electrique propulsion = new Electrique(masse);
	double valObservee = propulsion.consommerEnergie();
	assertEquals(valAttendue, valObservee, 0.1);
    }

    @Test
    public void testEolienneConsommerEnergie() {
	Eolienne propulsion = new Eolienne();
	double valAttendue = 0.0;
	double valObservee = propulsion.consommerEnergie();
	assertEquals(valAttendue, valObservee, 0.0);
    }

    /* Engin */
    @Test
    public void testIdEnginRoulantThermique() {
        int valObserveeDebut = Engin.getDernierId();
        Engin engin = new Roulant(new Thermique(42.0, Carburant.ID_ESSENCE));
        engin = new Roulant(new Thermique(42.0, Carburant.ID_ESSENCE));
        int valAttendue = 2;
        int valObservee = engin.getId() - valObserveeDebut;
        assertEquals(valAttendue, valObservee);
    }

    @Test
    public void testIdEnginFlottantElectrique() {
        int valObserveeDebut = Engin.getDernierId();
        Engin engin = new Roulant(new Thermique(42.0, Carburant.ID_ESSENCE));
        engin = new Roulant(new Thermique(42.0, Carburant.ID_ESSENCE));
        engin = new Flottant(new Electrique(42.0));
        int valAttendue = 3;
        int valObservee = engin.getId() - valObserveeDebut;
        assertEquals(valAttendue, valObservee);
    }

    @Test
    public void testDernierIdEngin() {
	int valObserveeDebut = Engin.getDernierId();
	Engin engin = new Roulant(new Thermique(42.0, Carburant.ID_ESSENCE));
	engin = new Roulant(new Electrique(42.0));
	engin = new Flottant(new Eolienne());
	int valObservee = Engin.getDernierId() - valObserveeDebut;
	int valAttendue = 3;
	assertEquals(valAttendue, valObservee);
    }

    @Test
    public void testEnginGetPropulsion() {
	Propulsion propulsionAttendue = new Thermique(42.0, Carburant.ID_ESSENCE);
	Engin engin = new Roulant(propulsionAttendue);
	Propulsion propulsionObservee = engin.getPropulsion();
	assertEquals(propulsionAttendue, propulsionObservee);
    }

    @Test
    public void testEnginToString() {
	Engin engin = new Roulant(new Thermique(42.0, Carburant.ID_ESSENCE));
	int id = engin.getId();
	String valAttendue = String.format("Roulant [id=%d]", id);
	String valObservee = engin.toString();
	assertEquals(valAttendue, valObservee);
    }

    @Test
    public void testFlottantConduire() {
	Propulsion propulsion = new Thermique(42.0, Carburant.ID_ESSENCE);
	Engin engin = new Flottant(propulsion);
	double valEnergie = propulsion.consommerEnergie();
	String valAttendue = String.format(
		"(SIMULATION) Cet engin flottant est propulsé par un moteur Thermique. Il consomme %.1f énergie.",
		valEnergie);
	label1: try {
	    File sortieFic = new File("tmpOut.txt");
	    PrintStream sortiePrintStream = new PrintStream(sortieFic);
	    System.setOut(sortiePrintStream);
	    engin.conduire();
	    FileReader sortieFileReader = new FileReader(sortieFic);
	    char[] cbuf = new char[valAttendue.length()];
	    sortieFileReader.read(cbuf);
	    sortieFileReader.close();
//	    System.err.println("Lu: " + String.copyValueOf(cbuf));
	    String valObservee = String.copyValueOf(cbuf);
	    assertEquals(valAttendue, valObservee);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    label2: e.printStackTrace();
	}
    }

    @Test
    public void testRoulantConduire() {
	Propulsion propulsion = new Electrique(42.0);
	Engin engin = new Roulant(propulsion);
	double valEnergie = propulsion.consommerEnergie();
	String valAttendue = String.format(
		"(SIMULATION) Conduire cet engin roulant nécessite un moteur à propulsion Electrique dont la consommation d'énergie est de %.2f.",
		valEnergie);
//	engin.conduire();
	try {
	    File sortieFic = new File("tmpOut.txt");
	    PrintStream sortiePrintStream = new PrintStream(sortieFic);
	    System.setOut(sortiePrintStream);
	    engin.conduire();
	    FileReader sortieFileReader = new FileReader(sortieFic);
	    char[] cbuf = new char[valAttendue.length()];
	    sortieFileReader.read(cbuf);
	    sortieFileReader.close();
//	    System.err.println("Lu: " + String.copyValueOf(cbuf));
	    String valObservee = String.copyValueOf(cbuf);
	    assertEquals(valAttendue, valObservee);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
