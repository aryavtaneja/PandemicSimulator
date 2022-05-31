/**
 * Simulator class that simulates a play of the game
 * @author Fiona
 * @author Daniel Welicki
 * @author Aryav Taneja 
 */

package src;
import java.util.*; 

public class Simulator {
    private Person[] population = new Person[5000];
    private ArrayList<Person> susceptiblePeople = new ArrayList<Person>(); 
    private ArrayList<Person> infectedPeople = new ArrayList<Person>(); 
    private ArrayList<Person> deadPeople = new ArrayList<Person>();
	private ArrayList<Person> recoveredPeople = new ArrayList<Person>();
    private ArrayList <Day> dayData = new ArrayList<>();
    private Virus gameVirus = new Virus();
    
    public Simulator() {
		for (int i = 0; i < population.length; i++) {
			population[i] = new Person();
		}

		int patientZeroIndex = (int)(Math.random() * 500); 
		population[patientZeroIndex].hardInfect(gameVirus);

		refreshArrayLists();
		dayData.add(new Day(dayData.size() + 1, infectedPeople.size(), 0, 0, susceptiblePeople.size()));
    }

	public void refreshArrayLists() {
		susceptiblePeople.clear();
		infectedPeople.clear();
		deadPeople.clear();
		recoveredPeople.clear();
		
		for(Person p : population) {
			if (p.isSusceptible()) {
				susceptiblePeople.add(p);
			} else if (p.isInfected()) {
				infectedPeople.add(p);
			} else if (p.isDead()) {
				deadPeople.add(p);
			} else {
				recoveredPeople.add(p);
			}
		}
	}
   
    public void updateInfectability(int infectability) {
		gameVirus.setInfectability(infectability / 10.0);
	}
	public void updateMortality(int mortality) {
	
		gameVirus.setMortality(mortality/ 10.0 );
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
		System.out.println("Virus incubation: " + gameVirus.getIncubation() + ", Virus resistance: " + gameVirus.getResistance());
		int casesAtStart = infectedPeople.size();
		int deathsAtStart = deadPeople.size();
		int recoveriesAtStart = recoveredPeople.size();
		int numberToContact;

		if ((12 * infectedPeople.size()) > susceptiblePeople.size() || susceptiblePeople.size() < (12 * infectedPeople.size())) {
			numberToContact = susceptiblePeople.size();
		} else {
			numberToContact = 12 * infectedPeople.size();
		}

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
        if (deltaCases < 0) {
            deltaCases = 0;
        }
		int deltaDeaths = deadPeople.size() - deathsAtStart;
		int deltaRecoveries = recoveredPeople.size() - recoveriesAtStart;

		// Once the methods that do the math are coded, replace each parameter with it's corresponding operation. 
		dayData.add(new Day(dayData.size() + 1, deltaCases, deltaDeaths, deltaRecoveries, susceptiblePeople.size()));
		Day.addTotalCases(deltaCases);
		Day.addTotalDeaths(deltaDeaths);
		Day.addTotalRecoveries(deltaRecoveries);
		return dayData.get(dayData.size() - 2);
	}
}

