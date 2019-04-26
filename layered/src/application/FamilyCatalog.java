package layered.src.application;
import layered.src.database.IDatabase;
import layered.src.database.FamilyMember;
import layered.src.data.*;
import java.util.Iterator;

public class FamilyCatalog
{
   IDatabase database;

   public FamilyCatalog()
   {
      database = null;
   }

   public void connectToDatabase(IDatabase d)
   {
      this.database = d;
   }

   public String getMother(String fName, String lName)
   {
      FamilyMember mother = database.getMother(fName, lName);
      if (mother == null)
      {
         return null;
      }
      return mother.toString();
   }

   public String getFather(String fName, String lName)
   {
      FamilyMember father = database.getFather(fName, lName);
      if (father == null)
      {
         return null;
      }
      return father.toString();
   }

   public Iterator<NameInfo> getSiblings(String fName, String lName)
   {
      FamilyMember person = database.get(fName, lName);
      return new SiblingsIterator(database.list(), person);
   }

   public Iterator<NameInfo> getChildren(String fName, String lName)
   {
      FamilyMember person = database.get(fName, lName);
      return new ChildrenIterator(database.list(), person);
   }

   public Iterator<NameInfo> list()
   {
      return new FamilyMemberIterator(database.list());
   }
}
