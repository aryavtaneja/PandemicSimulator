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
    private ArrayList<Person> infected = new ArrayList<Person>(); 
    private ArrayList<Person> dead = new ArrayList<Person>();
    private ArrayList <Day> dayData = new ArrayList<>();
    private Virus gameVirus = new Virus();
    
    public Simulator() {
    	 dayData.add(new Day(0, 1, 0, 0, 0));
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
		// Once the methods that do the math are coded, replace each parameter with it's corresponding operation. 
		dayData.add(new Day(dayData.size(), 0, 0,0, 0));
		Day currentDay = dayData.get(dayData.size() - 2);
		Day.addTotalCases(currentDay.cases());
		Day.addTotalDeaths(currentDay.deaths());
		Day.addTotalRecoveries(currentDay.recoveries());
		return dayData.get(dayData.size() - 2);
	}
}
