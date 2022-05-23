package src;

import java.util.*;

public class Person {

   /**
    * @param incubation the number of days before the person shows symptoms
    */
   private static int numCases = 0;
   private static int numDeaths = 0;
   private static int totalPeople = 0;
   private static int year = 0;
   private int age;
   private boolean preCondition;
   private boolean infected;
   private int incubation;

   public Person(int age, boolean preCondition) {
      this.age = age;
      this.preCondition = preCondition;
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
      int rand = (int) ((Math.random() * 79) + 1);
      this.age = rand;
      if (year >= 1) {
         age += year;
      }
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
      if (infected) {
         return;
      }
      this.infected = true;
      int rand = (int) ((Math.random() * 99) + 1);
      if (rand <= virus.getInfectability() * 100) {
         startIncubation(virus.getIncubation());
      }
   }

   public void startIncubation(int time) {
      incubation = time;
   }

   public void update() {
      if (incubation > 0) {
         incubation--;
      }
      if (incubation == 0) {
         int rand = (int) ((Math.random() * 99) + 1);
         // TODO: to die or not to die, that is to be determined
      }
   }

   public void die() {
      this.age = 0;
      this.preCondition = false;
      this.infected = false;
      totalPeople--;
   }
}
