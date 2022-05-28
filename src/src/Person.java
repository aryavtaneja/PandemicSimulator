/**
 * Defines class for a person in the simulator
 * @author Daniel Welicki
 * @author Aryav Taneja
 * @author Joshua Wu
 * @author Ronan Smith
 * @author Ethan Hu
 */ 


package src.src;


public class Person {

   /**
    * @param age          The age of the person
    * @param preCondition If the person have a preexisting condition that increases
    *                     rate of death
    * @param susceptible  If the person is susceptible to the disease
    * @param infected     If the person is infected
    * @param dead         If the person is dead
    * @param incubation   The number of days before the person shows symptoms
    * @param mortality    The rate of mortality for this person
    */
   
   private int age;
   //private int ageInYears;
   //private int ageCategory;
   //private boolean preCondition;

   public boolean isSusceptible() {
      return susceptible;
   }

   public boolean isInfected() {
      return infected;
   }

   public boolean isDead() {
      return dead;
   }

   public boolean isRecovered() {
      return recovered;
   }

   private boolean susceptible;
   private boolean infected;
   private boolean dead;
   private boolean recovered;
   private int incubation;
   private int resistance;
   private double mortality;

   public Person() {
      this.age = (int) (Math.random() * 29199);
      //this.ageInYears = (int) Math.floor(age / 365);
      //this.preCondition = (Math.random() > 0.95);
      this.infected = false;
      this.susceptible = true;
      this.recovered = false;
      this.dead = false;
      this.incubation = 0;
      this.resistance = 0;
       /** 
      if (0 <= ageInYears && ageInYears < 5) {
         this.ageCategory = 0;
      } else if (5 <= ageInYears && ageInYears < 18) {
         this.ageCategory = 1;
      } else if (18 <= ageInYears && ageInYears < 65) {
         this.ageCategory = 2;
      } else {
         this.ageCategory = 3;
      }
      */
   }

   /**
    * This method is called when a person is in close contact with a carrier. It
    * will determine if them person will be affected.
    * 
    * @param virus The virus that the person is infected with
    */
   public void closeContact(Virus virus) {
      int rand = (int) ((Math.random() * 99) + 1);
      if (rand <= virus.getInfectability() * 100) {
         incubation = virus.getIncubation();
         resistance = virus.getResistance();
         calcMortality(virus);
         this.susceptible = false;
         this.infected = true;
      }
   }

   public void hardInfect(Virus virus) {
      incubation = virus.getIncubation();
      resistance = virus.getResistance();
      calcMortality(virus);
      this.susceptible = false;
      this.infected = true;
   }

   public void calcMortality(Virus virus) {
      double rate = virus.getMortality();
      //mortality = preCondition ? rate * 2 : rate;
      mortality = rate;
   }

   /**
    * update the person's status, called in the main loop every interation.
    */
   public void update() {
      if (dead) {
         return;
      }

      if (age >= 29200) {
         die();
         return;
      }

      if (infected) {
         if (incubation > 0) {
            incubation--;
         }
         if (incubation == 0) {
            if ((Math.random())<= mortality) {
               die();
               return;
            }
            resistance--;
            if (resistance == 0) {
               this.susceptible = false;
               this.recovered = true;
            }
         }
      }

      age++;
   }

   public void die() {
      this.recovered = false;
      this.infected = false;
      this.dead = true;
      this.susceptible = false;
   }
}
