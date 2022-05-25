package src;
import java.util.*; 

public class Simulator {
    private Person[] population = new Person[500];
    private ArrayList<Person> susceptiblePeople = new ArrayList<Person>(); 
    private ArrayList<Person> infected = new ArrayList<Person>(); 
    private ArrayList<Person> dead = new ArrayList<Person>();
    private static Virus gameVirus = new Virus();
    
    public Simulator() {
    	 for (int i = 0; i < population.length; i++) {
             population[i] = new Person();
         }

         int patientZero = (int)(Math.random() * 500); 
         infected.add(population[patientZero]); 

         for (int i = 0; i < population.length; i++) {
             if (i != patientZero) {
                 susceptiblePeople.add(population[i]); 
             }
         }	
    }
   
    public static void updateInfectability(double infectability) {
		gameVirus.setInfectability(infectability / 10);
	}
	public static void updateMortality(double mortality) {
		gameVirus.setMortality(mortality / 10 );
	}
	public static void updateSusceptibilities(boolean sus) {
		gameVirus.setSusceptibilities(null);
	}
	public static void updateIncubation(int incubation) {
		gameVirus.setIncubation(incubation);
	}
	public static void updateResistance(int resistance) {
		gameVirus.setResistance(resistance);
	}
	public static void updateName(String name) {
		gameVirus.setName(name);
	}
}
