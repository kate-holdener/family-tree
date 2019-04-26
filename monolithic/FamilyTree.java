/*
 * Represents a collection of FamilyMembers. Finds paretns, siblings, and children
 * of a given family member
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FamilyTree
{
   private ArrayList<FamilyMember> relatives;

   public FamilyTree()
   {
      relatives = new ArrayList<FamilyMember>();
   } 

   /*
    * Loads family members from CSV file
    */
   public void fromCSV(String fileName)
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
         FamilyMember r = findFamilyMember(names[0], names[1]);
         if (r == null)
         {
            r = new FamilyMember(names[0], names[1]);
            relatives.add(r);
         }
         FamilyMember father = findFamilyMember(names[2], names[3]);
         if (father == null)
         {
            father = new FamilyMember(names[2], names[3]);
            relatives.add(father);
         }
         r.setFather(father);
         FamilyMember mother = findFamilyMember(names[4], names[5]);
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
   private FamilyMember findFamilyMember(String firstName, String lastName)
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
    * Finds siblings of the person provided in the arguments
    * @param firstName - first name of the person whose siblings we need to find
    * @param lastName - last name of the person whose siblings we need to find
    * @return array of siblings
    */
   public FamilyMember[] getSiblings(String firstName, String lastName)
   {
      ArrayList<FamilyMember> siblings = new ArrayList<FamilyMember>();
      FamilyMember person = findFamilyMember(firstName, lastName);
      // if the person exists, iterate over the list and find that person's siblings
      if (person != null)
      {
         FamilyMember mother = person.getMother();
         FamilyMember father = person.getFather();
         if (mother != null && father != null)
         {
            for (int i = 0; i < relatives.size(); i++)
            {
               FamilyMember nextPerson = relatives.get(i);
               if (nextPerson != person && 
                   nextPerson.getMother() == mother && 
                   nextPerson.getFather() == father)
               {
                  siblings.add(nextPerson);
               } 
            }
         }
      }
      FamilyMember []sib = {};
      return siblings.toArray(sib);
   }


   /*
    * Finds siblings of the person provided in the arguments
    * @param firstName - first name of the person whose siblings we need to find
    * @param lastName - last name of the person whose siblings we need to find
    * @return array of siblings
    */

   public FamilyMember[] getChildren(String firstName, String lastName)
   {
      ArrayList<FamilyMember>children = new ArrayList<FamilyMember>();
      FamilyMember person = findFamilyMember(firstName, lastName);

      if (person != null)
      {
         for (int i = 0; i < relatives.size(); i++)
         {
            FamilyMember nextPerson = relatives.get(i);
            if (nextPerson != person && 
                nextPerson.getMother() == person || 
                nextPerson.getFather() == person)
            {
               children.add(nextPerson);
            } 
         }
      }
      FamilyMember []kids = {};
      return children.toArray(kids);
   }

   /*
    * Finds and returns a given person's mother
    */
   public FamilyMember getMother(String firstName, String lastName)
   {
      FamilyMember mother = null;
      FamilyMember person = findFamilyMember(firstName, lastName);
      if (person != null)
      {
         mother = person.getMother();
      }
      return mother;
   }

   /*
    * Finds and returns a given person's father
    */
   public FamilyMember getFather(String firstName, String lastName)
   {
      FamilyMember father = null;
      FamilyMember person = findFamilyMember(firstName, lastName);
      if (person != null)
      {
         father = person.getFather();
      }
      return father;
   }
}
