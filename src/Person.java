package src;

public class Person {

   /**
    * @param numCases     The number of people infected
    * @param numDeaths    The number of people who died
    * @param numRecovered The number of people who recovered
    * 
    * 
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
   private boolean preCondition;
   private boolean susceptible;
   private boolean infected;
   private boolean dead;
   private int incubation;
   private int resistance;
   private double mortality;

   public Person() {
      this.age = (int) (Math.random() * 29199);
      this.preCondition = (Math.random() > 0.95);
      this.infected = false;
      this.susceptible = true;
      this.infected = false;
      this.dead = false;
      this.incubation = 0;
      this.resistance = 0;
   }

   /**
    * This method is called when a person is in close contact with a carrier. It
    * will determine if them person will be affected.
    * 
    * @param virus The virus that the person is infected with
    */
   public void closeContact(Virus virus) {
      if (infected || dead || !susceptible) {
         return;
      }
      this.infected = true;
      int rand = (int) ((Math.random() * 99) + 1);
      if (rand <= virus.getInfectability() * 100) {
         incubation = virus.getIncubation();
         resistance = virus.getResistance();
         calcMortality(virus.getMortality());

      }
   }

   public void calcMortality(double rate) {
      mortality = preCondition ? rate * 2 : rate;
   }

   /**
    * update the person's status, called in the main loop every interation.
    */
   public void update() {
      if (incubation > 0) {
         incubation--;
      }
      if (incubation == 0) {
         int rand = (int) ((Math.random() * 99) + 1);
         if (rand <= mortality * 100) {
            die();
         }
         resistance--;
         if (resistance == 0) {
            this.susceptible = false;
         }
      }
   }

   public void die() {
      this.infected = false;
      this.dead = true;
   }
}
