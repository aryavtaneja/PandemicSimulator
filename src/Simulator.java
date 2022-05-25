package src;
import java.util.*; 

public class Simulator {
    private Person[] population = new Person[500];
    private ArrayList<Person> susceptiblePeople = new ArrayList<Person>(); 
    private ArrayList<Person> infected = new ArrayList<Person>(); 
    private ArrayList<Person> dead = new ArrayList<Person>();
    private Virus mainVirus = new Virus();

    public Simulator(double infectability, double lethality, int incubation, int resistance) {
        mainVirus.setIncubation(incubation); 
        mainVirus.setInfectability(infectability);
        mainVirus.setMortality(lethality);
        mainVirus.setResistance(resistance); 

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
}
