/**
 * Simulator class that simulates a play of the game
 * @author Fiona
 * @author Daniel Welicki
 * @author Aryav Taneja 
 */

package src;
import java.util.*; 

public class Simulator {
    private Person[] population = new Person[6000];
    private ArrayList<Person> susceptiblePeople = new ArrayList<Person>(); 
    private ArrayList<Person> infectedPeople = new ArrayList<Person>(); 
    private ArrayList<Person> deadPeople = new ArrayList<Person>();
	private ArrayList<Person> recoveredPeople = new ArrayList<Person>();
    private ArrayList <Day> dayData = new ArrayList<>();
    private Virus gameVirus = new Virus();
    private int patientZeroIndex;
    
    public Simulator() {
		for (int i = 0; i < getPopulation().length; i++) {
			getPopulation()[i] = new Person();
		}

		refreshArrayLists();
    }

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
	
	public void initPatientZero() {
		this.patientZeroIndex = (int)(Math.random() * 500); 
		getPopulation()[patientZeroIndex].hardInfect(gameVirus);
		refreshArrayLists();
		dayData.add(new Day(dayData.size() + 1, 1, 0, 0, susceptiblePeople.size()));
		Day.addTotalCases(1);
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
		
		int deltaCases = 0;
		int deathsAtStart = deadPeople.size();
		int recoveriesAtStart = recoveredPeople.size();
		int numberToContact;

		if ((12 * getInfectedPeople().size()) > susceptiblePeople.size() || susceptiblePeople.size() < (12 * getInfectedPeople().size())) {
			numberToContact = susceptiblePeople.size();
		} else {
			numberToContact = 12 * getInfectedPeople().size();
		}

		for (int i = 0; i < numberToContact; i++) {
			int randomPersonIndex = (int) (Math.random() * susceptiblePeople.size());
			if (susceptiblePeople.get(randomPersonIndex).closeContact(gameVirus)) {
				deltaCases++;
			}
			refreshArrayLists();
		}

		for (Person person : getPopulation()) {
			person.update();
		}
		
		refreshArrayLists();

		
		int deltaDeaths = deadPeople.size() - deathsAtStart;
		int deltaRecoveries = recoveredPeople.size() - recoveriesAtStart;

		Day.addTotalCases(deltaCases);
		Day.addTotalDeaths(deltaDeaths);
		Day.addTotalRecoveries(deltaRecoveries);
		dayData.add(new Day(dayData.size() + 1, deltaCases, deltaDeaths, deltaRecoveries, susceptiblePeople.size()));
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
}

