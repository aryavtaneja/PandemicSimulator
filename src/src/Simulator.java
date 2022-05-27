/**
 * Simulator class that simulates a play of the game
 * @author Fiona
 * @author Daniel Welicki
 * @author Aryav Taneja 
 */

package src.src;
import java.util.*; 

public class Simulator {
    private Person[] population = new Person[500];
    private ArrayList<Person> susceptiblePeople = new ArrayList<Person>(); 
    private ArrayList<Person> infectedPeople = new ArrayList<Person>(); 
    private ArrayList<Person> deadPeople = new ArrayList<Person>();
	private ArrayList<Person> recoveredPeople = new ArrayList<Person>();
    private ArrayList <Day> dayData = new ArrayList<>();
    private Virus gameVirus = new Virus();
    
    public Simulator() {
    	 dayData.add(new Day(0, 1, 0, 0, 0));
    	 for (int i = 0; i < population.length; i++) {
             population[i] = new Person();
         }

         int patientZero = (int)(Math.random() * 500); 
         infectedPeople.add(population[patientZero]); 

         for (int i = 0; i < population.length; i++) {
             if (i != patientZero) {
                 susceptiblePeople.add(population[i]); 
             }
         }	
    }

	public void refreshArrayLists() {
		susceptiblePeople.clear();
		infectedPeople.clear();
		deadPeople.clear();
		recoveredPeople.clear();

		for (int i = 0; i < population.length; i++) {
			if (population[i].isSusceptible()) {
				susceptiblePeople.add(population[i]);
			} else if (population[i].isInfected()) {
				infectedPeople.add(population[i]);
			} else if (population[i].isDead()) {
				deadPeople.add(population[i]);
			} else {
				recoveredPeople.add(population[i]);
			}
		}
	}
   
    public void updateInfectability(int infectability) {
		gameVirus.setInfectability(infectability / 10.0);
	}
	public void updateMortality(int mortality) {
		gameVirus.setMortality(mortality / 10.0 );
	}
	public void updateSus(boolean[] sus) {
		gameVirus.setSusceptibilities(sus);
	}
	public void updateIncubation(int incubation) {
		gameVirus.setIncubation(incubation);
	}
	public void updateResistance(int resistance) {
		gameVirus.setResistance(resistance);
	}
	public void updateName(String name) {
		gameVirus.setName(name);
	}

	public Day simulate() {
		int casesAtStart = infectedPeople.size();
		int deathsAtStart = deadPeople.size();
		int recoveriesAtStart = recoveredPeople.size();
		
		int numberToContact = (12 * infectedPeople.size()) > infectedPeople.size() ? 12 * infectedPeople.size() : infectedPeople.size();

		for (int i = 0; i < numberToContact; i++) {
			int randomPersonIndex = (int) (Math.random() * susceptiblePeople.size());
			susceptiblePeople.get(randomPersonIndex).closeContact(gameVirus);
			refreshArrayLists();
		}

		for (Person person : population) {
			person.update();
		}

		refreshArrayLists();

		int deltaCases = infectedPeople.size() - casesAtStart;
		int deltaDeaths = deadPeople.size() - deathsAtStart;
		int deltaRecoveries = recoveredPeople.size() - recoveriesAtStart;

		// Once the methods that do the math are coded, replace each parameter with it's corresponding operation. 
		dayData.add(new Day(dayData.size(), deltaCases, deltaDeaths, deltaRecoveries, susceptiblePeople.size()));
		return dayData.get(dayData.size() - 2);
	}
}

