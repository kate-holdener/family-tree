package layered.src.data;

public class NameInfo
{
   public final String firstName;
   public final String lastName;
   public NameInfo(String fName, String lName)
   {
      firstName = fName;
      lastName = lName;
   }

   public String toString()
   {
      String descr = firstName + " " + lastName;
      return descr;
   }
}
