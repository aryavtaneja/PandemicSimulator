package src;

import java.util.*;

public class Person {
   private static int numCases = 0;
   private static int numDeaths = 0;
   private static int totalPeople = 0;

   private int age;
   private boolean preCondition;
   private boolean susceptible;
   private boolean infected;
   private boolean dead;

   public Person() {
      this.age = (int) (Math.random() * 29199);
      this.preCondition = (Math.random() > 0.5);
      infected = false;
      totalPeople++;
   }

   public void infect() {
      int rand = (int) (Math.random() + 1);
      if (rand == 1) {
         this.infected = true;
      }
   }

   public void die() {
      this.age = 0;
      this.preCondition = false;
      this.infected = false;
      totalPeople--;
   }
}
