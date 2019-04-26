package layered.src.database;
import layered.src.data.*;

public interface IDatabase
{
   public FamilyMember get(String fName, String lName);

   public FamilyMember getFather(String fName, String lName);

   public FamilyMember getMother(String fName, String lName);
   
   public FamilyMember[] list();
}
