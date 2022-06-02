/**
 * Defines class for a person in the simulator
 * @author Daniel Welicki
 * @author Aryav Taneja
 * @author Joshua Wu
 * @author Ronan Smith
 * @author Ethan Hu
 */ 
package src;
public class Person {

   /**
    * @param susceptible  If the person is susceptible to the disease
    * @param infected     If the person is infected
    * @param dead         If the person is dead
    * @param incubation   The number of days before the person shows symptoms
    * @param mortality    The rate of mortality for this person
    */

   private boolean susceptible;
   private boolean infected;
   private boolean dead;
   private boolean recovered;
   private int incubation;
   private int resistance;
   private double mortality;

   public Person() {
      this.infected = false;
      this.susceptible = true;
      this.recovered = false;
      this.dead = false;
   }

   //Getter for susceptible
   public boolean isSusceptible() {
      return susceptible;
   }

   //Getter for infected
   public boolean isInfected() {
      return infected;
   }
   
   //Getter for dead
   public boolean isDead() {
      return dead;
   }

   //Getter for recovered
   public boolean isRecovered() {
      return recovered;
   }

   /**
    * This method is called when a person is in close contact with a carrier. It
    * will determine if them person will be affected.
    * 
    * @param virus The virus that the person is infected with.
    */
   public boolean closeContact(Virus virus) {
      int rand = (int) ((Math.random() * 99) + 1);
      if (rand <= virus.getInfectability() * 100) {
         this.mortality = virus.getMortality();
         this.susceptible = false;
         this.infected = true;
         this.incubation = virus.getIncubation();
         this.resistance = virus.getResistance();
         return true;
      } else {
         return false;
      }
   }

   /**
    * This method is used to infect patient zero at simulation start.
    * @param virus The virus that patient zero is infected with.
    */
   public void hardInfect(Virus virus) {
      this.incubation = virus.getIncubation();
      this.resistance = virus.getResistance();
      this.mortality = virus.getMortality();
      this.susceptible = false;
      this.infected = true;
   }

   /**
    * update the person's status, called in the main loop every interation.
    */
   public void update() {
      if (dead) {
         return;
      }


      if (infected && !dead) {
         if (incubation > 0) {
            incubation--;

         }
        
         if (incubation == 0) {
        	double rand = Math.random();
            if (rand <= mortality) {
               die();
               return;
            }
           
         }
         this.resistance--;
         
         if (resistance <= 0) {
            this.susceptible = false;
            this.recovered = true;
            this.infected = false;
         }
      }
   }

   /**
    * The method to set a person's status to dead.
    */
   public void die() {
      this.recovered = false;
      this.infected = false;
      this.dead = true;
      this.susceptible = false;
   }
}
