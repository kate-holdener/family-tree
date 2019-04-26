package layered.src.database;
import layered.src.data.NameInfo;
/*
 * Family member class
 */

public class FamilyMember
{
   private FamilyMember mother;
   private FamilyMember father;
   private NameInfo     nameInfo;

   public FamilyMember(String fName, String lName)
   {
      nameInfo = new NameInfo(fName, lName);
   }

   public NameInfo getName()
   {
      return nameInfo;
   }
   /*
    * Determines if this family member has the name passed in arguments
    * @return true if this FamilyMember has first name equal to fName and
    * last name equal to lName; false otherwise
    */
   public boolean is(String fName, String lName)
   {
      return nameInfo.firstName.equals(fName) && nameInfo.lastName.equals(lName);
   }

   public void setMother(FamilyMember mother)
   {
      this.mother = mother;
   }

   public void setFather(FamilyMember father)
   {
      this.father = father;
   }

   public FamilyMember getMother()
   {
      return this.mother;
   }

   public FamilyMember getFather()
   {
      return this.father;
   }

   public String toString()
   {
      return nameInfo.toString();
   }

}
