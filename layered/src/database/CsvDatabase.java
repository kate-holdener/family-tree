package layered.src.database;

import layered.src.data.*;
/*
 * Represents a collection of FamilyMembers. Finds paretns, siblings, and children
 * of a given family member
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvDatabase implements IDatabase
{
   private ArrayList<FamilyMember> relatives;

   public CsvDatabase(String fileName)
   {
      relatives = new ArrayList<FamilyMember>();
      fromCSV(fileName);
   } 

   /*
    * Loads family members from CSV file
    */
   private void fromCSV(String fileName)
   {
      try
      {
         Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)));
         fromScanner(s);
      }
      catch(Exception e)
      {}
   }

   /*
    * Helper method used to load family members from CSV file
    */
   private void fromScanner(Scanner s)
   {
      while (s.hasNext())
      {
         String nextLine = s.nextLine();
         String[] names = nextLine.split(",");
         for (int i = 0; i < names.length; i++)
         {
            names[i] = names[i].trim();
         }
         FamilyMember r = get(names[0], names[1]);
         if (r == null)
         {
            r = new FamilyMember(names[0], names[1]);
            relatives.add(r);
         }
         FamilyMember father = get(names[2], names[3]);
         if (father == null)
         {
            father = new FamilyMember(names[2], names[3]);
            relatives.add(father);
         }
         r.setFather(father);
         FamilyMember mother = get(names[4], names[5]);
         if (mother == null)
         {
            mother = new FamilyMember(names[4], names[5]);
            relatives.add(mother);
         }
         r.setMother(mother);
      }
   }

   /*
    * Provides a list of existing family members in this collection
    * @return array of all FamilyMember(s)
    */
   public FamilyMember[] list()
   {
      FamilyMember []listOfPeople = {};
      return relatives.toArray(listOfPeople);   
   }

   /*
    * Adds a family member to this collection
    */
   public void add(FamilyMember r)
   {
      relatives.add(r);
   }

   /*
    * Finds a family member by first and last names
    */
   @Override
   public FamilyMember get(String firstName, String lastName)
   {
      FamilyMember found = null;
      for (int i = 0; i < relatives.size(); i++)
      {
         if (relatives.get(i).is(firstName, lastName))
         {
            found = relatives.get(i);
         }
      }
      return found;
   }

   /*
    * Finds and returns a given person's mother
    */
   @Override
   public FamilyMember getMother(String firstName, String lastName)
   {
      FamilyMember mother = null;
      FamilyMember person = get(firstName, lastName);
      if (person != null)
      {
         mother = person.getMother();
      }
      return mother;
   }

   /*
    * Finds and returns a given person's father
    */
   @Override
   public FamilyMember getFather(String firstName, String lastName)
   {
      FamilyMember father = null;
      FamilyMember person = get(firstName, lastName);
      if (person != null)
      {
         father = person.getFather();
      }
      return father;
   }
}
