package src;

import java.beans.PropertyEditor;
import java.util.*;

public class Person {

   /**
    * @param incubation the number of days before the person shows symptoms
    */
   private static int numCases = 0;
   private static int numDeaths = 0;
   private static int totalPeople = 0;

   private int age;
   private boolean preCondition;
   private boolean susceptible;
   private boolean infected;
   private boolean dead;
   private int incubation;
   private double mortality;

   public Person() {
      this.age = (int) (Math.random() * 29199);
      this.preCondition = (Math.random() > 0.5);
      infected = false;
      totalPeople++;
   }

   public void genCondition() {
      int personAge = this.age;
      if (personAge <= 18 && personAge > 0) {
         int rand = ((int) (Math.random() * 99)) + 1;
         if (rand <= 5) {
            this.preCondition = true;
         }
      } else if (personAge <= 24) {
         int rand = ((int) (Math.random() * 99)) + 1;
         if (rand <= 9) {
            this.preCondition = true;
         }
      } else if (personAge <= 34) {
         int rand = ((int) (Math.random() * 99)) + 1;
         if (rand <= 13) {
            this.preCondition = true;
         }
      } else if (personAge <= 44) {
         int rand = ((int) (Math.random() * 99)) + 1;
         if (rand <= 21) {
            this.preCondition = true;
         }
      } else if (personAge <= 54) {
         int rand = ((int) (Math.random() * 99)) + 1;
         if (rand <= 32) {
            this.preCondition = true;
         }
      } else {
         int rand = ((int) (Math.random() * 99)) + 1;
         if (rand <= 48) {
            this.preCondition = true;
         }
      }
   }

   public void generateAge() {
      if (age > 80) {
         die();
      }
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
      numCases++;
      this.infected = true;
      int rand = (int) ((Math.random() * 99) + 1);
      if (rand <= virus.getInfectability() * 100) {
         incubation = virus.getIncubation();
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
      }
   }

   public void die() {
      this.age = 0;
      this.preCondition = false;
      this.infected = false;
      totalPeople--;
   }
}
