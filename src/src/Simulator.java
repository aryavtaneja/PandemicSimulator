/**
 * Simulator class that simulates a play of the game
 * @author Fiona
 * @author Daniel Welicki
 * @author Aryav Taneja 
 */

package src;
import java.util.*; 

public class Simulator {
    private Person[] population = new Person[6000]; // declares new array of Persons class with a population of 6000
    private ArrayList<Person> susceptiblePeople = new ArrayList<Person>(); // declares an arraylist of susceptible people
    private ArrayList<Person> infectedPeople = new ArrayList<Person>(); // declares an arraylist of infected people that will be added to when more infections occur
    private ArrayList<Person> deadPeople = new ArrayList<Person>(); // declares arraylist of dead people
	private ArrayList<Person> recoveredPeople = new ArrayList<Person>(); // declares arraylist of people who've recovered 
    private static ArrayList <Day> dayData = new ArrayList<>(); // declares arraylist of days 
    private Virus gameVirus = new Virus(); // initializes new virus for the game
    private int patientZeroIndex; // declares an int that has what index patient zero is in the array  
    
	// initializes the population array
    public Simulator() { 
		for (int i = 0; i < getPopulation().length; i++) {
			getPopulation()[i] = new Person();
		}

		refreshArrayLists();
    }
	
	// clears previous list of susceptible, infected, dead, and recovered people when a new simulator is declared and adds susceptible, infected, dead, and recovered people to the arraylists
	public void refreshArrayLists() {
		susceptiblePeople.clear();
		getInfectedPeople().clear();
		deadPeople.clear();
		recoveredPeople.clear();
		
		for(Person p : getPopulation()) {
			if (p.isSusceptible()) {
				susceptiblePeople.add(p);
			} else if (p.isInfected()) {
				getInfectedPeople().add(p);
			} else if (p.isDead()) {
				deadPeople.add(p);
			} else {
				recoveredPeople.add(p);
			}
		}
	}
	// generates a random patient zero and starts incubation period 
	public void initPatientZero() {
		this.patientZeroIndex = (int)(Math.random() * 500); 
		getPopulation()[patientZeroIndex].hardInfect(gameVirus);
		refreshArrayLists();
		dayData.add(new Day(dayData.size() + 1, 1, 0, 0, susceptiblePeople.size(), infectedPeople.size()));
		Day.addTotalCases(1);
	}
   
    public void updateInfectability(int infectability) { // updates infectability 
		gameVirus.setInfectability(infectability / 10.0);
	}
	public void updateMortality(int mortality) { // updates mortality 
	
		gameVirus.setMortality(mortality/ 10.0 );
	}
	public void updateSus(boolean[] sus) { // updates susceptibility
		gameVirus.setSusceptibilities(sus);
	}
	public void updateIncubation(int incubation) { // updates incubation 
		gameVirus.setIncubation(incubation);
	}
	public void updateResistance(int resistance) { // updates resistance
		gameVirus.setResistance(resistance);
	}
	public void updateName(String name) { // updates Name 
		gameVirus.setName(name);
	}

	public Day simulate() { 
		
		int deltaCases = 0;
		int deathsAtStart = deadPeople.size();
		int recoveriesAtStart = recoveredPeople.size();
		int numberToContact;

		
		if ((12 * getInfectedPeople().size()) > susceptiblePeople.size() || susceptiblePeople.size() < (12 * getInfectedPeople().size())) {
			numberToContact = susceptiblePeople.size(); // everyone that is susceptible is close contacted if 12 times the number of infected people is more than susceptible
		} else { // each infected person contacts 12 people if 12 times the amount of susceptible people is less than those susceptible 
			numberToContact = 12 * getInfectedPeople().size();
		}

		for (int i = 0; i < numberToContact; i++) { 
			// determines randomly if each person who is contacted gets the virus and adds that to list if they are
			int randomPersonIndex = (int) (Math.random() * susceptiblePeople.size());
			if (susceptiblePeople.get(randomPersonIndex).closeContact(gameVirus)) {
				deltaCases++;
			}
			refreshArrayLists();
		}

		for (Person person : getPopulation()) {
			person.update(); // updates sttatus for everyone in population 
		}
		
		refreshArrayLists();

		
		int deltaDeaths = deadPeople.size() - deathsAtStart;
		int deltaRecoveries = recoveredPeople.size() - recoveriesAtStart;

		Day.addTotalCases(deltaCases);
		Day.addTotalDeaths(deltaDeaths);
		Day.addTotalRecoveries(deltaRecoveries);
		dayData.add(new Day(dayData.size() + 1, deltaCases, deltaDeaths, deltaRecoveries, susceptiblePeople.size(), infectedPeople.size()));
		return dayData.get(dayData.size() - 2);
	}

	public ArrayList<Person> getInfectedPeople() {
		return infectedPeople;
	}

	public void setInfectedPeople(ArrayList<Person> infectedPeople) {
		this.infectedPeople = infectedPeople;
	}

	public Person[] getPopulation() {
		return population;
	}
	public static ArrayList<Day> getDayData(){
		return dayData;
	}
}
	
