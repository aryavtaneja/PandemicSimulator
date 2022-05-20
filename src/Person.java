package src;

import java.util.*;

public class Person {
   private static int numCases = 0;
   private static int numDeaths = 0;
   private static int totalPeople = 0;
   private static int year = 0;
   private int age;
   private boolean preCondition;
   private boolean infected;

   public Person(int age, boolean preCondition) {
      this.age = age;
      this.preCondition = preCondition;
      infected = false;
      totalPeople++;
   }
   
   public void genCondition(){
      int personAge = this.age;
      if(personAge <= 18 && personAge > 0){
         int rand = ((int)(Math.random() * 99)) + 1;
         if(rand <= 5){
            this.preCondition = true;
         }
      }
      else if(personAge <= 24){
         int rand = ((int)(Math.random() * 99)) + 1;
         if(rand <= 9){
            this.preCondition = true;
         }
      }
      else if(personAge <= 34){
         int rand = ((int)(Math.random() * 99)) + 1;   
         if(rand <= 13){
            this.preCondition = true;
         } 
      }
      else if(personAge <= 44){
         int rand = ((int)(Math.random() * 99)) + 1;   
         if(rand <= 21){
            this.preCondition = true;
         } 
      }
      else if(personAge <= 54){
         int rand = ((int)(Math.random() * 99)) + 1;   
         if(rand <= 32){
            this.preCondition = true;
         } 
      }
      else{
         int rand = ((int)(Math.random() * 99)) + 1;   
         if(rand <= 48){
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
